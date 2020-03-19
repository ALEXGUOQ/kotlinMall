package com.example.disklrucachedemo

import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import com.jakewharton.disklrucache.DiskLruCache
import java.io.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class DiskLruCacheUtil(mContext: Context, fileName: String) {
    private var mDiskLurCache: DiskLruCache? = null

    /**
     * DiskLruCache.open(directory,  appVersion,  valueCount, maxSize)
     * directory 数据缓存地址
     * appVersion 版本号，当版本号改变数据会被清除
     * valueCount：同一个key可以对应多少文件
     * maxSize：最大可以缓存的数据量
     */
    init {
        try {
            if (mDiskLurCache != null) {
                mDiskLurCache!!.close()
                mDiskLurCache = null
            }
            val appVersionCode = getAppVersionCode(mContext)
            val cacheFile = getCacheFile(mContext, fileName)

            val cacheMaxsize = Runtime.getRuntime().maxMemory() / 8

            mDiskLurCache = DiskLruCache.open(cacheFile, appVersionCode, 1, cacheMaxsize)
        } catch (e: IOException) {
        }
    }

    /**
     * 获取edit
     *
     * @param key key需要加密
     * @return
     */
    private fun editor(key: String): DiskLruCache.Editor? {
        if (mDiskLurCache != null) {
            try {
                val md5Key = getMD5(key)
                return mDiskLurCache!!.edit(md5Key)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    /**
     * 获取缓存策略 通过snapshot得到输入流
     *
     * @return
     */
    private fun snapshot(key: String): DiskLruCache.Snapshot? {
        val md5Key = getMD5(key)
        if (mDiskLurCache != null) {
            try {
                return mDiskLurCache!!.get(md5Key)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun put(key: String, value: String?) {
        var editor: DiskLruCache.Editor? = null
        var writer: BufferedWriter? = null
        var os: OutputStream? = null
        try {
            editor = editor(key)
            if (editor == null) {
                return
            }
            os = editor.newOutputStream(0)
            writer = BufferedWriter(OutputStreamWriter(os))
            writer.write(value)
            writer.flush()
            editor.commit()
        } catch (e: IOException) {
            e.printStackTrace()
            try {
                editor?.abort()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        } finally {
            try {
                writer?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                os?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun put(key: String, bytes: ByteArray?) {
        var out: OutputStream? = null
        var editor: DiskLruCache.Editor? = null
        try {
            editor = editor(key)
            if (editor == null) {
                return
            }
            out = editor.newOutputStream(0)
            out.write(bytes)
            out.flush()
            editor.commit()
        } catch (e: IOException) {
            e.printStackTrace()
            try {
                editor?.abort()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        } finally {
            try {
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun put(key: String, value: Any?) {
        var oos: ObjectOutputStream? = null
        var editor: DiskLruCache.Editor? = null
        var outputStream: OutputStream? = null
        try {
            editor = editor(key)
            if (editor == null) {
                return
            }
            outputStream = editor.newOutputStream(0)
            oos = ObjectOutputStream(outputStream)
            oos.writeObject(value)
            oos.flush()
            editor.commit()
        } catch (e: IOException) {
            e.printStackTrace()
            try {
                editor?.abort()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        } finally {
            try {
                oos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                outputStream?.close()
            } catch (e: IOException) {
            }
        }
    }

    fun <T> getObjectCache(key: String): T? {
        val inputStream = getInputStream(key)
        var ois: ObjectInputStream? = null
        var value: T? = null
        if (inputStream == null) {
            return null
        }
        try {
            ois = ObjectInputStream(inputStream)
            value = ois.readObject() as T
            return value
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                ois?.close()
            } catch (e: IOException) {
            }
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun getStringCache(key: String): String? {
        val inputStream = getInputStream(key)
        var inputStreamReader: InputStreamReader? = null
        var reader: BufferedReader? = null
        if (inputStream == null) {
            return null
        }
        try {
            inputStreamReader = InputStreamReader(inputStream, "UTF-8")
            reader = BufferedReader(inputStreamReader)
            val buffer = StringBuilder()
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    buffer.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return buffer.toString()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                inputStreamReader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun getBytesCache(key: String): ByteArray? {
        var bytes: ByteArray? = null
        val inputStream = getInputStream(key) ?: return null
        val bos = ByteArrayOutputStream()
        val buf = ByteArray(256)
        var len = 0
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                bos.write(buf, 0, len)
            }
            bytes = bos.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bytes
    }

    private fun getInputStream(key: String): InputStream? {
        val snapshot = snapshot(key) ?: return null
        return snapshot.getInputStream(0)
    }

    /**
     * 获取缓存目录
     *
     * @param context
     * @return
     */
    private fun getCacheFile(
        context: Context,
        fileName: String
    ): File {
        var cachePath = ""
        //判读sd卡是否可用
        cachePath =
            if ((Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable())
                && context.externalCacheDir != null
            ) { //获取有sd卡时的路径，该路径在程序卸载时会被删除
                context.externalCacheDir!!.path
            } else { //获取无sd卡时的路径，该路径在程序卸载时会被删除
                context.cacheDir.path
            }
        //File.separator 分隔符
        return File(cachePath + File.separator + fileName)
    }

    /**
     * 传入字符串参数，返回MD5加密结果
     *
     * @return 加密结果
     */
    private fun getMD5(str: String): String {
        var messageDigest: MessageDigest? = null
        try { //设置哪种算法
            messageDigest = MessageDigest.getInstance("MD5")
            //对算法进行重置以免影响下次计算
            messageDigest.reset()
            //使用指定的字段进行摘要
            messageDigest.update(str.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        //完成Hash值的计算
        val byteArray = messageDigest!!.digest()
        val md5StrBuff = StringBuffer()
        for (i in byteArray.indices) {
            if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1) md5StrBuff.append(
                "0"
            ).append(Integer.toHexString(0xFF and byteArray[i].toInt())) else md5StrBuff.append(
                Integer.toHexString(0xFF and byteArray[i].toInt())
            )
        }
        return md5StrBuff.toString()
    }

    private fun getAppVersionCode(context: Context): Int {
        try {
            val info =
                context.packageManager.getPackageInfo(context.packageName, 0)
            return info.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 1
    }
}
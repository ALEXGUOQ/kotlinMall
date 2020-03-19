package com.gryphon.baselibrary.data.net

import com.gryphon.baselibrary.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){
    companion object{
        val instance :RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit:Retrofit
    private val interceptor:Interceptor

    init {
        interceptor = Interceptor{ chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-type","application/json")
                .addHeader("charset","utf-8")
                .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(initClent())
            .build()
    }

    fun <T> create(service: Class<T>):T{
        return retrofit.create(service)
    }

    private fun initClent(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(initLogInterceptor())
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build()
     }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
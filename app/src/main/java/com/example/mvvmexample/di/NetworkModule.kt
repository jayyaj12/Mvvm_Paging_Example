package com.example.mvvmexample.di

import com.example.mvvmexample.BuildConfig
import com.example.mvvmexample.data.network.BookServiceApi
import com.example.mvvmexample.util.network.CustomCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor
    ) = run {
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = kotlin.run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
        chain.run {
            proceed(
                request().newBuilder()
                    .addHeader("Authorization", "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}").build()
            )
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = run {
        Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.KAKAO_BASE_URL)
            .addCallAdapterFactory(CustomCallAdapterFactory()).addConverterFactory(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true // 지정되지 않은 key 값은 무시
                    coerceInputValues = true // default 값 설정
                    explicitNulls = false // 없는 필드는 null로 설정
                }.asConverterFactory("application/json".toMediaType())
            ).build()
    }

    @Singleton
    @Provides
    fun provideBookService(retrofit: Retrofit) = retrofit.create(BookServiceApi::class.java)

}
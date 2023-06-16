package lib.kg.youtubeparccer.core.network

import lib.kg.youtubeparccer.BuildConfig.BASE_URL
import lib.kg.youtubeparccer.data.remote.ApiService
import lib.kg.youtubeparccer.data.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideRetrofit(okHttpClient = get()) }
    factory { provideApi(retrofit = get()) }
    factory { RemoteDataSource(apiService = get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

private fun provideApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
package net.sharetrip.gnrads.network


import net.sharetrip.gnrads.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    var BASE_URL = "https://dev-panel.green-red.com/"

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val original: Request = chain.request()

            val requestBuilder = original
                .newBuilder()
                .method(original.method, original.body)
                .addHeader("Content-Type", "application/json")
                .addHeader("origin", "https://dev-panel.green-red.com")
                .addHeader("Host", "dev-engine.green-red.com")

            return chain.proceed(requestBuilder.build())
        }
    }

    private val logger: HttpLoggingInterceptor = if (BuildConfig.DEBUG) {
        val data = HttpLoggingInterceptor()
        data.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        val data = HttpLoggingInterceptor()
        data.setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(logger)
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}

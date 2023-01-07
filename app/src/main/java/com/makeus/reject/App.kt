package com.makeus.reject

import android.app.Application
import android.content.SharedPreferences
import com.makeus.reject.common.Consts
import com.makeus.reject.exception.ResultCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        //싱글톤 패턴으로 사용 할 객체
        lateinit var sharedPreferences: SharedPreferences
        lateinit var retrofit: Retrofit
        var statusHeight: Int = 0

        const val API_URL = "https://port-0-cloudtype-test-1luhct24lck5opet.gksl2.cloudtype.app/"
    }

    override fun onCreate() {
        super.onCreate()

        //sharedPreference 인스턴스 생성
        sharedPreferences = applicationContext.getSharedPreferences(Consts.SHARED_PREFERENCES_NAME, MODE_PRIVATE)

        getStatusBarHeight()
        initRetrofitInstance()
    }


    private fun getStatusBarHeight() {
        //statusbar 높이 구하는 방법
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        if (resourceId > 0) {
            statusHeight = resources.getDimensionPixelSize(resourceId)
        }
    }

    /**
     * Retrofit 인스턴스 생성 메소드
     */
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    /**
     *  헤더에 JWT 자동으로 전송하기 위한 Interceptor 클래스
     */
    inner class XAccessTokenInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain.request().newBuilder()
            val jwtToken: String? = sharedPreferences.getString(Consts.X_ACCESS_TOKEN, null)
            if (jwtToken != null) {
                builder.addHeader(Consts.X_ACCESS_TOKEN, jwtToken)
            }
            return chain.proceed(builder.build())
        }
    }
}
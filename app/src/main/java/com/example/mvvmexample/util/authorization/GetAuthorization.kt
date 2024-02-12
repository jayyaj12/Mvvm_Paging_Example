package com.example.mvvmexample.util.authorization

import com.example.mvvmexample.BuildConfig

object GetAuthorization {

    const val kakaoRestApi = BuildConfig.KAKAO_REST_API_KEY

    fun getAuthorizationToken() = "KakaoAK $kakaoRestApi"

}
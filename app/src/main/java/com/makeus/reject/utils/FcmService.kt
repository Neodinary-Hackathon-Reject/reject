package com.makeus.reject.utils

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.makeus.reject.App

class FcmService : FirebaseMessagingService() {
    companion object {
        const val FCM_TOKEN_KEY = "fcmToken"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        //발급 받은 fcm 토큰 sharedPreference에 저장
        App.sharedPreferences.edit().apply {
            this.putString(FCM_TOKEN_KEY, token)
            apply()
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //TODO 추후 fcm 메세지 수신 부 구현
    }
}
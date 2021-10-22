package com.example.testvom

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted) {
            // アクセス権限
            val intent = Intent(
                Settings
                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
            )
            startActivity(intent)
        }

        // AudioManagerを取得する
        var am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var mainVom = am.getStreamVolume(AudioManager.STREAM_RING)
        var noVom = am.getStreamVolume(AudioManager.STREAM_NOTIFICATION)
        var mediaVom = am.getStreamVolume(AudioManager.STREAM_MUSIC)

        // すべての音量を確認しサイレントにする
        if(mainVom>0||noVom>0||mediaVom>0){
            // am.ringerMode = AudioManager.RINGER_MODE_SILENT
            am.ringerMode = 0
            am.setStreamVolume(AudioManager.STREAM_MUSIC,0, AudioManager.FLAG_SHOW_UI)
        }
        // activityを終了する。
        finish()
    }
}

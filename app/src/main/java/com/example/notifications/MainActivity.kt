package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID ="1"

    lateinit var mainBinding: ActivityMainBinding
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.sendNotification.setOnClickListener {

            counter++
            mainBinding.sendNotification.text = counter.toString()
            if (counter % 5 == 0){
                startNotification()
            }

        }
    }

    fun startNotification()
    {
        val builder = NotificationCompat.Builder(this@MainActivity,CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"1",NotificationManager.IMPORTANCE_DEFAULT)
            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.small_icon)
                .setContentTitle("Title")
                .setContentText("Notification Text")
        }
        else{
            builder.setSmallIcon(R.drawable.small_icon)
                .setContentTitle("Notification Title")
                .setContentText("This is the notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        val notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
        notificationManagerCompat.notify(1,builder.build())
    }
}
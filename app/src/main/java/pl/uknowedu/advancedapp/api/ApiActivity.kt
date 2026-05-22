package pl.uknowedu.advancedapp.api

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.uknowedu.advancedapp.R
import java.security.AccessController.getContext

class ApiActivity : AppCompatActivity() {
    lateinit var ctx: Context
    val channel = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ctx = this
        createNotificationChannel()
        val retrofit = RetrofitInstance
            .getRetrofitInstance()
            .create(JsonPlaceholderService::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val posts = retrofit.getPosts()
            withContext(Dispatchers.Main) @androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS) {
                Toast
                    .makeText(ctx,"Liczba postów: ${posts.size}", Toast.LENGTH_SHORT)
                    .show()
                if (ActivityCompat.checkSelfPermission(
                        ctx,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast
                        .makeText(ctx,"Brak zgody na powiadomienia", Toast.LENGTH_SHORT)
                        .show()
                }
                sendNotification("Post", "Zakończono pobieranie ${posts.size}")
            }
        }

    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel";
            val description = "api operation ";
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelObj = NotificationChannel(
                channel, name, importance
            )
            channelObj.setDescription(description);
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channelObj)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun sendNotification(title: String, content: String){
        val notification: Notification = NotificationCompat
            .Builder(this, channel)
            .setContentTitle("Alert")
            .setSmallIcon(R.drawable.baseline_flare_24)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat
            .from(ctx)
            .notify(123, notification)

    }
}
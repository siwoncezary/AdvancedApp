package pl.uknowedu.advancedapp.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.uknowedu.advancedapp.R

class SensorActivity : AppCompatActivity() {
    lateinit var tempView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sensor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        var sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        tempView = findViewById(R.id.tempView)
        sensorManager.registerListener(object: SensorEventListener{
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            }
            override fun onSensorChanged(event: SensorEvent?) {
                tempView.text = "${event?.values[0]} C"
            }
        },sensor, 1000)


    }
}
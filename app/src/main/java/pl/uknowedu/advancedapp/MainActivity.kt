package pl.uknowedu.advancedapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.uknowedu.advancedapp.base.DaoActivity
import pl.uknowedu.advancedapp.recycler.RecyclerActivity
import pl.uknowedu.advancedapp.sensor.SensorActivity

class MainActivity : AppCompatActivity() {
    lateinit var recyclerBtn: Button
    lateinit var sensorBtn: Button

    lateinit var daoBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    fun init(){
        recyclerBtn = findViewById(R.id.recycleBtn)
        sensorBtn = findViewById(R.id.sensorBtn)
        daoBtn = findViewById(R.id.daoBtn)

        recyclerBtn.setOnClickListener { g->
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        daoBtn.setOnClickListener {
            val intent = Intent(this, DaoActivity::class.java)
            startActivity(intent)
        }

        sensorBtn.setOnClickListener {
            val intent = Intent(this, SensorActivity::class.java)
            startActivity(intent)
        }

    }


}
package pl.uknowedu.advancedapp.base

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import pl.uknowedu.advancedapp.R

class DaoActivity : AppCompatActivity() {
    lateinit var console: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dao)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        console = findViewById(R.id.console)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "users_database")
            .build()

        val dao = database.getDao()

        lifecycleScope.launch(Dispatchers.IO) {
            if (dao.getById(1) == null) {
                dao.save(User(1, "adam", "adam@op.pl", "1234!"))
            }
            withContext(Dispatchers.Main) {
                console.text = console.text.toString() + "User {} saved\n"
            }
            val users = dao.getAll()
            withContext(Dispatchers.Main) {
                console.text = console.text.toString() + "Users read ${users.size}\n"
                console.text = console.text.toString() + "${users[0]}"
            }
        }
    }
}
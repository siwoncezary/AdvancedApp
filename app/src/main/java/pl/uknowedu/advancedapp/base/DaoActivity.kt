package pl.uknowedu.advancedapp.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
    lateinit var database: AppDatabase
    lateinit var dao: UserDao
    // Zadanie 1
    // zdefiniuj poniżej zmienne dla pól formualrza
    // nameEdit, emailEdit, passwordEdit i confirmPasswordEdit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dao)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Zadanie 2
        // przypisz do zmiennych z zadania 1 obiekty zwrócone przez findViewById
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "users_database")
            .build()
        dao = database.getDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val count = dao.getAll().size
            // aktualizacja w AppBar liczby użytkowników
            withContext(Dispatchers.Main){
                supportActionBar?.title = "Formularz (${count})"
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.dao_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.daoMenuSave -> {
                // Zadanie 3
                // odczytaj dane z formularza
                // utówrz na podstawie tych danych obiekt klasy User
                // zapisz obiekt do bazy odpowiednią metodą dao
                // pamiętaj o umieszczeniu kodu zapisu w lifeCycle.launch(Dispatchers.IO)
                // Zadanie 4
                // dodaj w interfejsie UserDao metodę countAll, która zwraca liczbę osób w bazie
                // Zdanie 5
                // Wywołaj metodę z zadania 4, aby zaktualizować liczbę rekordów w AppBar
            }
            R.id.daoMenuReturn -> {
                // Zadanie 6
                // wywołaj metodę finish, aby wyjść z aktywności, gdy użytkownik wybierze tak
                AlertDialog.Builder(this)
                    .setMessage("Czy na pewno chcesz wyjść z aplikacji?")
                    .setPositiveButton("Tak", { d, i ->
                        // kod gdy użytkownik wybierze Tak
                    })
                    .setNeutralButton("Nie", { d,i ->
                        // kod gdy użytkownik wybierze Nie
                    })
                    .create()
                    .show()
            }
        }
        return true
    }
}

// Zadanie 7
// Napisz funkcję, która sprawdzi poprawność danych w formularzu
// nazwa z nameEdit nie może być krótsza do 2 znaków i dłuższe niż 20 znaków
// adres z emailEdit musi zawierać znak '@' a po tym znaku musi wystąpić choć jedna kropka
// hasła w passwordEdit i z confirmPasswordEdit muszą być identyczne
// hasło nie może być krótsze od 5 znaków, musi zawierać cyfrę, dużą literę i znak specjalny
// jeśli spełnione są wszystkie warunki funkcja zwraca true w przeciwnym wypadku false

// Zadanie 8
// Wywołaj funkcję z zadania 7 przed zapisaniem danej do bazy
// Zapisz użytkownika, gdy wszystkie jego dane są poprawne

// Zadanie 9
// W funkcji z zadania 7 w sytuacji wystąpienia błędu wyświetl okno dialogowe z informacją o błędach
// skorzystaj ze przykłądu tworzenia okna w zadaniu 6, bez dodawania negatywnego przycisku
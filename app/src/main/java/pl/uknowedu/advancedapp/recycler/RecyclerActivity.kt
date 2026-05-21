package pl.uknowedu.advancedapp.recycler

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.uknowedu.advancedapp.R

// Zadanie 10
// Zmodyfikuj RecyclerActivity, aby wyświetlał użytkowników z bazy.
//
// Skopiuj układ recycler_item.xml do pliku user_item.xml,
// i zmodyfikuj go, aby zmiast RatingBar zawierał TextView
// Oba TextView będa służyć do wyświetlania name i email użytkownika.
// Pozostaw ImageView w tym samym miejscu, aby wyświetlać tam jedną z dwóch ikon
// (samodzielnie poszukaj ikon) dla mężczyzny lub kobiety.

// Zdanie 11
// Utwórz nowy adapter UsersAdapter (na wzór obecnego MovieAdapter'a), aby wyświetlał dane wszystkich użytkowników z bazy
// W ImageView wyświetl losowo jedną z dwóch ikon, a name i email w TextView

// Zdanie 12
// Zmień kod w RecyclerActivity, aby obecny RecyclerView wyświetał dane z bazy

class RecyclerActivity : AppCompatActivity() {
    val movies = listOf(
        Movie(Uri.parse("https://fwcdn.pl/cwpo/00/03/3/3_1.3.jpg"), "Nowa Nadzieja", 10),
        Movie(Uri.parse("https://fwcdn.pl/fpo/05/25/525/8020805_1.10.webp"), "Imperium kontratakuje", 9),
        Movie(Uri.parse("https://cdn1.epicgames.com/offer/5a2ea5980ac147c195775039195a3081/EGS_STARWARSJediSurvivorStandardEdition_RespawnEntertainment_S1_2560x1440-f9e8bb0209bb9ec26636838fbc3dcd85"), "Powrót Jedi", 8),
        Movie(Uri.parse("https://supercarclub.pl/wp-content/uploads/2020/01/Aston-Martin-DBX.jpg"), "Star Wars: New Hope", 9)
        )
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recycler = findViewById(R.id.moviesRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = MovieAdapter(movies)
        recycler.adapter = adapter
    }
}
package pl.uknowedu.advancedapp

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.google.android.material.snackbar.Snackbar

class MovieAdapter(val movies: List<Movie>): Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val row: View): RecyclerView.ViewHolder(row){
        val title = row.findViewById<TextView>(R.id.movieTitle)
        val rating = row.findViewById<RatingBar>(R.id.movieRating)
        val icon = row.findViewById<ImageView>(R.id.movieIcon)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MovieViewHolder(layout)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        i: Int
    ) {
        Log.i("RECYCLER", "Movie title: ${movies[i].title}")
        holder.title.text = movies[i].title
        holder.rating.rating = movies[i].rating.toFloat()
        holder.icon.load(movies[i].url){
            placeholder(R.drawable.loading)
            error(R.drawable.error)
        }
        holder.rating.setOnRatingBarChangeListener(object: RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(ratingBar: RatingBar?, r: Float, fromUser: Boolean) {
                movies[i].rating = r.toInt()
                Snackbar.make(holder.rating,"Changed to ${movies[i].rating}", Snackbar.LENGTH_SHORT).show()
            }
        })

    }

    override fun getItemCount(): Int {
        Log.i("RECYCLER", "${movies.size}")
        return movies.size
    }
}
package pl.uknowedu.advancedapp.api

import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface JsonPlaceholderService {
    /**
     * Funkcja zwraca wszystkie posty
     * @return lista postów klasy Post
     */
    @Headers("Accept: application/json")
    @GET("posts")
    suspend fun getPosts(): List<Post>

    /**
     * Funkcja zwraca post o podanym w argumencie identyfikatorze
     * @param id identyfikator posta typu całkowitego
     * @return - obiekt klasy Post jeśli znaleziono lub null gdy brak
     */
    @GET("posts/{id}")
    @Headers("Accept: application/json")
    suspend fun getPost(@Path("id")id: Int): Post?
}
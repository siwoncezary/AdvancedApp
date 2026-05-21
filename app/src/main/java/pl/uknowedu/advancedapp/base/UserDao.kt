package pl.uknowedu.advancedapp.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("Select * from user")
    fun getAll(): List<User>

    @Query("Select * from user where id =:id" )
    fun getById(id: Int): User?

    @Query("Select * from user where email =:email LIMIT 1")
    fun getByEmail(email: String): User?

    @Insert
    fun save(user:User)

    @Delete
    fun deleteById(user: User)
}
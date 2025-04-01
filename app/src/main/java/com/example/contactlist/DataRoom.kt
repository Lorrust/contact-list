package com.example.contactlist

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val uuid: Int,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(vararg user: User)
}

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
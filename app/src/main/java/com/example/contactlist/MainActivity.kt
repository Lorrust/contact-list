package com.example.contactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.contactlist.ui.theme.ContactListTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      Init Database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "com.example.contactlist.CONTACT_LIST"
        )
            .allowMainThreadQueries()
            .build()

//        Init User Dao
        val userDao = db.userDao()

        val u1 = User(0, "Joe", "Montana")
        val u2 = User(0, "Jack", "Sparrow")
        val u3 = User(0, "Edward", "Ricks")

        userDao.insertAll(u1)
        userDao.insertAll(u2)
        userDao.insertAll(u3)

        val users = userDao.getAll()

        setContent {
            ContactApp(users) {
                userDao.insertAll(it)
            }
        }
    }
}
package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AgendaActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_agenda)

  }

  fun goToExploreActivity(v: View) {
    val intentProfile = Intent(this, MainActivity::class.java)
    startActivity(intentProfile)
  }

  fun goToFavoritesActivity(v: View) {
    val intentFavorites = Intent(this, FavoritesActivity::class.java)
    startActivity(intentFavorites)
  }

  fun goToShineActivity(v: View) {
    val intentShine = Intent(this, ShineActivity::class.java)
    startActivity(intentShine)
  }

  fun goToAgendaActivity(v: View) {
    val intentAgenda = Intent(this, AgendaActivity::class.java)
    startActivity(intentAgenda)
  }

  fun goToProfileActivity(v: View) {
    val intentProfile = Intent(this, ProfileActivity::class.java)
    startActivity(intentProfile)
  }

}
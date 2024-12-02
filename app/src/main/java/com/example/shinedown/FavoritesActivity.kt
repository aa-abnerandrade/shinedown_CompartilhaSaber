package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.ShineLessonModel
import service.FavoritesAdapter
import service.FavoritesManager

class FavoritesActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_favorites)

    loadFavorites()

  }

  fun loadFavorites() {
    val favoritesList = FavoritesManager.getInstance().getFavorites()

    // Configurando o RecyclerView
    val favoritesRecyclerView: RecyclerView = findViewById(R.id.favorites_recycler_view)
    favoritesRecyclerView.layoutManager = LinearLayoutManager(this)

    // Passando uma função lambda que remove o item clicado longo
    val adapter = FavoritesAdapter(this, favoritesList) { shine ->
      delShineFromFavorites(shine)
    }

    favoritesRecyclerView.adapter = adapter
  }

  private fun delShineFromFavorites(shine: ShineLessonModel) {
    FavoritesManager.getInstance().delFavorite(shine)
    Toast.makeText(this, "${shine.title} removido dos favoritos!", Toast.LENGTH_SHORT).show()

    // Atualiza a lista do RecyclerView
    loadFavorites()
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
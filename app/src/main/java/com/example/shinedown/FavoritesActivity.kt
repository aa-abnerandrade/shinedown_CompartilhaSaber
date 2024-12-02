package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import model.ShineLessonModel
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

    // Exemplo de como exibir a lista - você pode adaptar para usar um RecyclerView, TableLayout, etc.
    val favoritesTable: TableLayout = findViewById(R.id.favorites_sectionb_favorites)

    for (shine in favoritesList) {
      val newRow = TableRow(this)
      newRow.layoutParams = TableRow.LayoutParams(
        TableRow.LayoutParams.MATCH_PARENT,
        TableRow.LayoutParams.WRAP_CONTENT
      )

      val titleTextView = TextView(this)
      titleTextView.text = shine.title
      titleTextView.setPadding(16, 16, 16, 16)

      newRow.addView(titleTextView)
      favoritesTable.addView(newRow)
    }
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
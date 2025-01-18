package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import model.ShineLessonModel
import repository.ShineLessonRepository



class ShineActivity : ComponentActivity() {
  private lateinit var shineLessonRepository: ShineLessonRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    shineLessonRepository = ShineLessonRepository(this)
    enableEdgeToEdge()
    setContentView(R.layout.activity_shine)
    loadMyShineSession()

  }

  fun loadMyShineSession() {
    val allShineLesson: ArrayList<ShineLessonModel> = shineLessonRepository.getAllShineLessonByUserIdFromDB(1)

    for (shine in allShineLesson) {
      createRowOnMyShineTable(shine)
      println("Título: ${shine.title}")
      println("Valor: ${shine.value}")
      println("Imagem: ${shine.image}")
      println("ID do Usuário: ${shine.userId}")
      println("---------------")
    }
  }

  fun createRowOnMyShineTable(shine: ShineLessonModel) {
    val myShineTable: TableLayout = findViewById(R.id.shine_sectionb_myshines)

    val newRow = TableRow(this)
    newRow.layoutParams = TableRow.LayoutParams(
      TableRow.LayoutParams.MATCH_PARENT,
      TableRow.LayoutParams.WRAP_CONTENT
    )
    newRow.setPadding(8, 8, 8, 8)

    // prev para a imagem do ShineLesson
    val shineImageView = ImageView(this)
    // width e weight
    shineImageView.layoutParams = TableRow.LayoutParams(
      0,
      LinearLayout.LayoutParams.WRAP_CONTENT,
      1.0f
    )
    shineImageView.scaleType = ImageView.ScaleType.CENTER_CROP
    shineImageView.adjustViewBounds = true
    shineImageView.setPadding(16, 16, 16, 16)

    // GLIDE
    if (shine.image.isNotEmpty()) {
      Glide.with(this)
        .load(shine.image) // Carrega
        .placeholder(android.R.drawable.ic_menu_gallery) // Placeholder
        .error(android.R.drawable.ic_menu_report_image)
        .into(shineImageView) // exibe
    }


    val shineLayout = LinearLayout(this)
    shineLayout.orientation = LinearLayout.VERTICAL
    shineLayout.layoutParams = TableRow.LayoutParams(
      0, // d
      LinearLayout.LayoutParams.MATCH_PARENT,
      2.0f // g
    )
    shineLayout.setPadding(16, 16, 16, 16)


    val titleTextView = TextView(this)
    titleTextView.text = shine.title
    titleTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    titleTextView.textSize = 18f
    titleTextView.setTypeface(null, android.graphics.Typeface.BOLD)


    val valueTextView = TextView(this)
    valueTextView.text = "R$ ${shine.value}"
    valueTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    valueTextView.textSize = 14f


    shineLayout.addView(titleTextView)
    shineLayout.addView(valueTextView)


    newRow.addView(shineImageView)
    newRow.addView(shineLayout)

    newRow.setOnLongClickListener {
      val success = shineLessonRepository.deleteShineLessonFromDB(shine.id)
      if (success) {
        Toast.makeText(this, "${shine.title} removido do seu catálogo.", Toast.LENGTH_SHORT).show()
        myShineTable.removeView(newRow) // !!! já remover diretamente da tbl
      } else {
        Toast.makeText(this, "Erro ao remover ${shine.title}.", Toast.LENGTH_SHORT).show()
      }
      true
    }

    myShineTable.addView(newRow)
  }

  private fun removeShineFromMyShines(shine: ShineLessonModel) {
    val success = shineLessonRepository.deleteShineLessonFromDB(shine.id)
    if (success) {
      Toast.makeText(this, "${shine.title} removido de seu catálogo.", Toast.LENGTH_SHORT).show()
      // Remover a linha correspondente da tabela
      loadMyShineSession() // Atualizar a exibição da tabela
    } else {
      Toast.makeText(this, "Erro ao remover ${shine.title}.", Toast.LENGTH_SHORT).show()
    }
  }

  fun goToNewShineActivity(v: View) {
    val intentProfile = Intent(this, NewShineActivity::class.java)
    startActivity(intentProfile)
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

  override fun onStart() {
    super.onStart();
    Log.v("LifeCycle", "onStart")
  }

  override fun onResume() {
    super.onResume()
    Log.w("LifeCycle", "onResume")
  }

  override fun onPause() {
    super.onPause()
    Log.e("LifeCycle", "onPause")
  }

  override fun onStop() {
    super.onStop()
    Log.wtf("LifeCycle", "onStop")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.v("LifeCycle", "onDestroy")
  }
}
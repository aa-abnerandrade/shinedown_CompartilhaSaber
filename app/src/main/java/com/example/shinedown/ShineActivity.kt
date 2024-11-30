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
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import model.ShineLessonModel
import repository.ShineLessonRepository
import util.MediaConverter


class ShineActivity : ComponentActivity() {
  private lateinit var shineLessonRepository: ShineLessonRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_shine)
    shineLessonRepository = ShineLessonRepository(this)

    loadMyShineSession()
  }

  fun loadMyShineSession() {
    val allShineLesson: ArrayList<ShineLessonModel> = shineLessonRepository.getAllShineLessonFromDB()

    // Iterando e imprimindo os Shines para verificação
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
    // Primeiro, vamos obter a referência da TableLayout do layout XML
    val myShineTable: TableLayout = findViewById(R.id.shine_sectionb_myshines)

    // Criar uma nova TableRow programaticamente
    val newRow = TableRow(this)
    newRow.layoutParams = TableRow.LayoutParams(
      TableRow.LayoutParams.MATCH_PARENT,
      TableRow.LayoutParams.WRAP_CONTENT
    )
    newRow.setPadding(8, 8, 8, 8)

    // Criar um ImageView para a imagem do ShineLesson
    val shineImageView = ImageView(this)
    shineImageView.layoutParams = TableRow.LayoutParams(
      0, // width 0 para suportar layout_weight
      LinearLayout.LayoutParams.WRAP_CONTENT,
      1.0f // weight para ocupar 1 parte da largura da linha
    )
    shineImageView.scaleType = ImageView.ScaleType.CENTER_CROP
    shineImageView.adjustViewBounds = true
    shineImageView.setPadding(16, 16, 16, 16)

    // Usar Glide para carregar a imagem da URL
    if (shine.image.isNotEmpty()) {
      Glide.with(this)
        .load(shine.image) // Carrega a URL da imagem
        .placeholder(android.R.drawable.ic_menu_gallery) // Placeholder enquanto carrega
        .error(android.R.drawable.ic_menu_report_image) // Imagem de erro caso falhe ao carregar
        .into(shineImageView) // Carrega a imagem no ImageView
    }

    // Criar um LinearLayout para conter os elementos, como título e valor
    val shineLayout = LinearLayout(this)
    shineLayout.orientation = LinearLayout.VERTICAL
    shineLayout.layoutParams = TableRow.LayoutParams(
      0, // width 0 para suportar layout_weight
      LinearLayout.LayoutParams.MATCH_PARENT,
      2.0f // weight para ocupar 2 partes da largura da linha
    )
    shineLayout.setPadding(16, 16, 16, 16)

    // Criar um TextView para o título do Shine
    val titleTextView = TextView(this)
    titleTextView.text = shine.title
    titleTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    titleTextView.textSize = 18f
    titleTextView.setTypeface(null, android.graphics.Typeface.BOLD)

    // Criar um TextView para o valor do Shine
    val valueTextView = TextView(this)
    valueTextView.text = "R$ ${shine.value}"
    valueTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    valueTextView.textSize = 14f

    // Adiciona os TextViews ao LinearLayout
    shineLayout.addView(titleTextView)
    shineLayout.addView(valueTextView)

    // Adiciona o ImageView e o LinearLayout à TableRow
    newRow.addView(shineImageView)
    newRow.addView(shineLayout)

    // Adiciona a TableRow ao TableLayout
    myShineTable.addView(newRow)
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
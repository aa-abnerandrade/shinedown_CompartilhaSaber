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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.shinedown.ui.theme.ShinedownTheme
import model.ShineLessonModel
import repository.ShineLessonRepository
import service.FavoritesManager
import util.ShineLessonMock


class MainActivity : ComponentActivity() {
  private lateinit var shineLessonRepository: ShineLessonRepository
  //private val favoritesList = ArrayList<ShineLessonModel>() // Lista para armazenar aulas favoritas

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    shineLessonRepository = ShineLessonRepository(this)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    loadExploreSession()

    Log.v("MainACtivity", "Passou 1")

    loadExploreSession()
  }

  fun loadExploreSession() {
    val allShineLesson: ArrayList<ShineLessonModel> = shineLessonRepository.getAllShineLessonFromDB()

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
    val myShineTable: TableLayout = findViewById(R.id.main_sectionb_classes)

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

    // Adiciona o clique longo para adicionar a aula à lista de favoritos
    newRow.setOnLongClickListener {
      addShineToFavorites(shine)
      true // Retorna true para indicar que o clique longo foi tratado
    }

    // Adiciona a TableRow ao TableLayout
    myShineTable.addView(newRow)
  }

  // Função para adicionar um ShineLessonModel à lista de favoritos
  private fun addShineToFavorites(shine: ShineLessonModel) {
    FavoritesManager.getInstance().addFavorite(shine)
    Toast.makeText(this, "${shine.title} adicionado aos favoritos!", Toast.LENGTH_SHORT).show()
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


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  ShinedownTheme {
    Greeting("Android")
  }
}
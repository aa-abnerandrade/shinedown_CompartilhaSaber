package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shinedown.ui.theme.ShinedownTheme
import util.ShineLessonMock


class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    loadExploreSession()
  }

  fun loadExploreSession() {
    val shineLessons = ShineLessonMock.getMockShineLessons()

    for (lesson in shineLessons) {
      println("Título: " + lesson.title)
      println("Valor: " + lesson.value)
      println("Imagem: " + lesson.image)
      println("ID do Usuário: " + lesson.userId)
      println("---------------")
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
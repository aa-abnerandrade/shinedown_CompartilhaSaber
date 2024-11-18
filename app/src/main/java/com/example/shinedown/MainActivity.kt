package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.example.shinedown.ui.theme.ShinedownTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ShinedownTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
      setContentView(R.layout.activity_main)
    }
  }

  fun goToProfileActivity(v: View) {
    val intentProfile = Intent(this, ProfileActivity::class.java)
    startActivity(intentProfile)
  }

  fun goToShineActivity(v: View) {
    val intentShine = Intent(this, ShineActivity::class.java)
    startActivity(intentShine)
  }

  fun goToFavoritesActivity(v: View) {
    val intentFavorites = Intent(this, FavoritesActivity::class.java)
    startActivity(intentFavorites)
  }

  fun goToAgendaActivity(v: View) {
    val intentAgenda = Intent(this, AgendaActivity::class.java)
    startActivity(intentAgenda)
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
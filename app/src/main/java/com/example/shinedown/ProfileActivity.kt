package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : ComponentActivity() {
  private lateinit var editDocuments: EditText
  private lateinit var editEmails: EditText

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_profile)

    editDocuments = findViewById(R.id.profile_sectionb_settings_edit_documents)
    editEmails = findViewById(R.id.profile_sectionb_settings_edit_emails)

    // Ler o arquivo JSON e preencher os campos
    readJsonAndFillFields()
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

  private fun readJsonAndFillFields() {
    try {
      // Abrir o arquivo JSON na pasta res/raw
      val inputStream = resources.openRawResource(R.raw.profile_data)
      val bufferedReader = BufferedReader(InputStreamReader(inputStream))
      val stringBuilder = StringBuilder()
      var line: String?

      // Ler o arquivo linha por linha
      while (bufferedReader.readLine().also { line = it } != null) {
        stringBuilder.append(line)
      }

      // Fechar o BufferedReader
      bufferedReader.close()

      // Converter o texto em um objeto JSON
      val jsonObject = JSONObject(stringBuilder.toString())

      // Obter os valores dos campos do JSON
      val documents = jsonObject.getString("documents")
      val emails = jsonObject.getString("emails")

      // Preencher os campos EditText
      editDocuments.setText(documents)
      editEmails.setText(emails)

    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

}
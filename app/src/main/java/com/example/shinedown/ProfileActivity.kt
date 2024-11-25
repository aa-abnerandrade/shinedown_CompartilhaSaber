package com.example.shinedown

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

class ProfileActivity : ComponentActivity() {
  private lateinit var editName: EditText
  private lateinit var editDocuments: EditText
  private lateinit var editEmails: EditText
  private lateinit var editPhones: EditText
  private lateinit var editPassword: EditText
  private lateinit var editPayment: EditText
  private lateinit var switchNotifications: Switch
  private lateinit var switchPrivacy: Switch


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_profile)

    editName = findViewById(R.id.profile_sectiona_edit_name)
    editDocuments = findViewById(R.id.profile_sectionb_settings_edit_documents)
    editEmails = findViewById(R.id.profile_sectionb_settings_edit_emails)
    editPhones = findViewById(R.id.profile_sectionb_settings_edit_phone)
    editPassword = findViewById(R.id.profile_sectionb_settings_edit_password)
    editPayment = findViewById(R.id.profile_sectionb_settings_edit_payment)
    switchNotifications = findViewById(R.id.profile_sectionb_settings_selector_notifications)
    switchPrivacy = findViewById(R.id.profile_sectionb_settings_selector_privacy)

    // Copiar o JSON de recursos para armazenamento interno se ainda não houver
    copyJsonToInternalStorage()
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
    val fileName = "profile_data.json"
    val file = File(filesDir, fileName)

    if (file.exists()) {
      try {
        val bufferedReader = BufferedReader(FileReader(file))
        val stringBuilder = StringBuilder()
        var line: String?

        while (bufferedReader.readLine().also { line = it } != null) {
          stringBuilder.append(line)
        }

        bufferedReader.close()

        val jsonObject = JSONObject(stringBuilder.toString())

        // Obter os valores dos campos do JSON
        val name = jsonObject.optString("name", "")
        val documents = jsonObject.optString("documents", "")
        val emails = jsonObject.optString("emails", "")
        val phones = jsonObject.optString("phones", "")
        val password = jsonObject.optString("password", "")
        val payment = jsonObject.optString("payment", "")
        val notifications = jsonObject.optBoolean("notifications", false)
        val privacy = jsonObject.optBoolean("privacy", false)

        // Preencher os campos EditText
        editName.setText(name)
        editDocuments.setText(documents)
        editEmails.setText(emails)
        editPhones.setText(phones)
        editPassword.setText(password)
        editPayment.setText(payment)
        switchNotifications.isChecked = notifications
        switchPrivacy.isChecked = privacy

      } catch (e: IOException) {
        e.printStackTrace()
      }
    }
  }

  private fun copyJsonToInternalStorage() {
    val fileName = "profile_data.json"
    val file = File(filesDir, fileName)

    // Verificar se o arquivo já existe no armazenamento interno
    if (!file.exists()) {
      try {
        val inputStream = resources.openRawResource(R.raw.profile_data)
        val outputStream = FileOutputStream(file)

        val buffer = ByteArray(1024)
        var length: Int

        while (inputStream.read(buffer).also { length = it } > 0) {
          outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()

      } catch (e: IOException) {
        e.printStackTrace()
      }
    }
  }

  fun saveProfileData(v: View) {
    try {
      // Criar um objeto JSON com os valores dos campos preenchidos
      val jsonObject = JSONObject()
      jsonObject.put("name", editName.text.toString())
      jsonObject.put("documents", editDocuments.text.toString())
      jsonObject.put("emails", editEmails.text.toString())
      jsonObject.put("phones", editPhones.text.toString())
      jsonObject.put("password", editPassword.text.toString())
      jsonObject.put("payment", editPayment.text.toString())
      jsonObject.put("notifications", switchNotifications.isChecked)
      jsonObject.put("privacy", switchPrivacy.isChecked)

      // Salvar o JSON no armazenamento interno
      val fileName = "profile_data.json"
      val file = File(filesDir, fileName)
      val fileOutputStream = FileOutputStream(file)
      fileOutputStream.write(jsonObject.toString().toByteArray())
      fileOutputStream.close()

      // Mostrar uma mensagem de confirmação
      showToast("Dados salvos com sucesso!")

    } catch (e: Exception) {
      e.printStackTrace()
      showToast("Erro ao salvar os dados.")
    }
  }

  private fun showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

}
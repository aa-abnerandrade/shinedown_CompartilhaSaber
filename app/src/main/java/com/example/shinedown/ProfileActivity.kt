package com.example.shinedown

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import repository.ProfileDataRepository


class ProfileActivity : ComponentActivity() {
  private lateinit var editId: EditText
  private lateinit var editName: EditText
  private lateinit var editDocuments: EditText
  private lateinit var editEmails: EditText
  private lateinit var editPhones: EditText
  private lateinit var editPassword: EditText
  private lateinit var editPayment: EditText
  private lateinit var switchNotifications: Switch
  private lateinit var switchPrivacy: Switch

  private lateinit var dbHelper: ProfileDataRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_profile)

    dbHelper = ProfileDataRepository(this)

    editId = findViewById(R.id.profile_sectionb_settings_edit_userid)
    editName = findViewById(R.id.profile_sectiona_edit_name)
    editDocuments = findViewById(R.id.profile_sectionb_settings_edit_documents)
    editEmails = findViewById(R.id.profile_sectionb_settings_edit_emails)
    editPhones = findViewById(R.id.profile_sectionb_settings_edit_phone)
    editPassword = findViewById(R.id.profile_sectionb_settings_edit_password)
    editPayment = findViewById(R.id.profile_sectionb_settings_edit_payment)
    switchNotifications = findViewById(R.id.profile_sectionb_settings_selector_notifications)
    switchPrivacy = findViewById(R.id.profile_sectionb_settings_selector_privacy)

    editId.isEnabled = false
    Log.v("ProfileActivity", "Passou 1")
    readDataAndFillFields()
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

  @SuppressLint("Range")
  private fun readDataAndFillFields() {
    Log.v("ProfileActivity", "Passou 2")
    val database = dbHelper.readableDatabase
    Log.v("ProfileActivity", "Passou 3")
    val cursor: Cursor = database.query(
      ProfileDataRepository.DB_TABLE,
      null,
      null,
      null,
      null,
      null,
      null
    )

    if (cursor.moveToFirst()) {
      Log.v("ProfileActivity", "Passou 4")
      val id = cursor.getLong(cursor.getColumnIndex(ProfileDataRepository.COL_ID))
      val name = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_NAME))
      val cpf = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_CPF))
      val email = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_EMAIL))
      val phone = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_PHONE))
      val password = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_PASSWORD))
      val payment = cursor.getString(cursor.getColumnIndex(ProfileDataRepository.COL_PAYMENT))
      val notificationEmail = cursor.getInt(cursor.getColumnIndex(ProfileDataRepository.COL_NOTIFICATIONEMAIL))
      val privacyData = cursor.getInt(cursor.getColumnIndex(ProfileDataRepository.COL_PRIVACYDATA))

      Log.v("ProfileActivity", "Passou 5")
      // Preencher cada campo
      editId.setText(id.toString())
      editName.setText(name)
      editDocuments.setText(cpf)
      editEmails.setText(email)
      editPhones.setText(phone)
      editPassword.setText(password)
      editPayment.setText(payment)
      // Configurando o estado dos Switches
      switchNotifications.isChecked = (notificationEmail == 1)
      switchPrivacy.isChecked = (privacyData == 1)

    }

  }


  fun editProfileData(v: View) {
    try {
      val id = editId.text.toString().toLongOrNull() ?: throw IllegalArgumentException("ProfileActivity ---> editProfileData ---> ID inválido")
      val name = editName.text.toString()
      val cpf = editDocuments.text.toString()
      val email = editEmails.text.toString()
      val phone = editPhones.text.toString()
      val password = editPassword.text.toString()
      val payment = editPayment.text.toString()
      val notificationEmail = switchNotifications.isChecked
      val privacyData = switchPrivacy.isChecked

      dbHelper.editProfileDataOnDB(id, name, cpf, email, phone, password, payment, notificationEmail, privacyData)

      showToast("Dados salvos com sucesso!")

    } catch (e: Exception) {
      e.printStackTrace()
      showToast("Erro ao salvar os dados.")
    }
  }

  private fun showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun onDestroy() {
    super.onDestroy()
    dbHelper.close()
  }

}
package com.example.shinedown

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import model.ShineLessonModel
import repository.ShineLessonRepository
import java.io.InputStream


class NewShineActivity: ComponentActivity() {

  private lateinit var editTitle: EditText
  private lateinit var editValue: EditText
  private lateinit var editImage: EditText
  private lateinit var imagePreview: ImageView
  private lateinit var choosePhotoButton: Button
  private lateinit var dbHelper: ShineLessonRepository
  private var selectedImageUri: Uri? = null
  private final var USER_ID: Int = 1;

  companion object {
    const val PICK_IMAGE_REQUEST = 1
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_newshine)

    dbHelper = ShineLessonRepository(this)

    editTitle = findViewById(R.id.newshine_sectionb_sharemyshine_edit_title)
    editValue = findViewById(R.id.newshine_sectionb_sharemyshine_edit_value)
    editImage = findViewById(R.id.newshine_sectionb_sharemyshine_edit_attachment)

  }


  fun goToShineActivity(v: View) {
    val intentShine = Intent(this, ShineActivity::class.java)
    startActivity(intentShine)
  }

  fun createNewShine(v: View) {
    val title = editTitle.text.toString()
    val value = editValue.text.toString()
    val image = editImage.text.toString()

    if (title.isEmpty() || value.isEmpty() || image.isEmpty()) {
      showToast("Preencha todos os campos.")
      return
    }


    val shineLesson = ShineLessonModel(title, value, image, 1)

    try {
      dbHelper.createShineLessonOnDB(shineLesson, USER_ID)
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
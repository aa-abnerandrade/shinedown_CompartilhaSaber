package com.example.shinedown

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import model.ShineLessonModel
import repository.ShineLessonRepository
import java.io.InputStream


class NewShineActivity: ComponentActivity() {

  private lateinit var editTitle: EditText
  private lateinit var editValue: EditText
  private lateinit var imagePreview: ImageView
  private lateinit var choosePhotoButton: Button
  private lateinit var dbHelper: ShineLessonRepository
  private var selectedImageUri: Uri? = null

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
    imagePreview = findViewById(R.id.newshine_sectionb_sharemyshine_image_previewphoto)
    choosePhotoButton = findViewById(R.id.newshine_sectionb_sharemyshine_button_choosephoto)

    choosePhotoButton.setOnClickListener {
      openGallery()
    }

  }

  private fun openGallery() {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    intent.type = "image/*"
    startActivityForResult(intent, PICK_IMAGE_REQUEST)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
      data?.data?.let { uri ->
        selectedImageUri = uri
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        imagePreview.setImageBitmap(bitmap)
        imagePreview.visibility = View.VISIBLE
      }
    }
  }



  fun goToShineActivity(v: View) {
    val intentShine = Intent(this, ShineActivity::class.java)
    startActivity(intentShine)
  }

  fun createNewShine(v: View) {
    val title = editTitle.text.toString()
    val value = editValue.text.toString()

    if (title.isEmpty() || value.isEmpty()) {
      // Adicione uma verificação para garantir que os campos não estejam vazios
      return
    }

    // Converte a URI para uma string (pode salvar no banco de dados como string)
    val imageUriString = selectedImageUri?.toString() ?: ""

    // Cria o objeto ShineLessonModel com os dados coletados
    val shineLesson = ShineLessonModel(title, value, imageUriString)

    // Salva no banco de dados
    dbHelper.createShineLessonOnDB(shineLesson)
  }

}
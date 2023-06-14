package com.example.diagnosaallapps

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.diagnosaallapps.Api.ApiConfig
import com.example.diagnosaallapps.DataStore.MainViewModel
import com.example.diagnosaallapps.DataStore.UserPreferences
import com.example.diagnosaallapps.DataStore.ViewModelFactory
import com.example.diagnosaallapps.Response.ResponseUpload
import com.example.diagnosaallapps.Result
import com.example.diagnosaallapps.databinding.ActivityUploadBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class Upload : AppCompatActivity() {
    private lateinit var binding : ActivityUploadBinding
    private lateinit var mainViewModel: MainViewModel
    private var imgFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]

        binding.imageView9.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val chooser = Intent.createChooser(intent, "Choose a Picture")
            launcherIntentGallery.launch(chooser)



        }
        binding.button.setOnClickListener{
            val patientname = binding.editText.text.toString()
            uploadIMG(patientname)
        }



    }

    private fun uploadIMG(patientname: String) {
        mainViewModel.getuser().observe(this){
            if (imgFile!=null){
                val MaxSizeIMG = CompressFileImage(imgFile as File)
                val Img = MaxSizeIMG.asRequestBody("image/jpeg".toMediaType())
                val patient = patientname.toRequestBody("text/plain".toMediaType())
                val MultiPartIMG: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    MaxSizeIMG.name,
                    Img
                )

                val apiService = ApiConfig.getApiService()
                val uploadImage =
                    apiService.Forupload(MultiPartIMG,patient , "Bearer ${it.token}")
                uploadImage.enqueue(object : Callback<ResponseUpload> {
                    override fun onResponse(
                        call: Call<ResponseUpload>,
                        response: Response<ResponseUpload>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            val intent = Intent(this@Upload,Result::class.java)
                            intent.putExtra("patientname",patientname)
                            if (responseBody != null) {
                                intent.putExtra("result",responseBody.result)
                            }
                            intent.putExtra("imageDiagnose",imgFile)
                            startActivity(intent)


                        } else {

                        }
                    }

                    override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {

                    }

                })
            }
            else{
                Toast.makeText(this, "Masukkan Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@Upload)
                imgFile = myFile
                binding.imageView7.setImageURI(uri)
            }
        }
    }

    fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createCustomTempFile(context)

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private fun CompressFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressFileQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressFileQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressFileQuality -= 5
        } while (streamLength > FILE_SIZE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressFileQuality, FileOutputStream(file))
        return file
    }

    fun createCustomTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }
    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())

    companion object {
        const val CAMERA_X_RESULT = 200
        private const val FILENAME_FORMAT = "dd-MMM-yyyy"
        private const val FILE_SIZE = 1000000
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

    }
}
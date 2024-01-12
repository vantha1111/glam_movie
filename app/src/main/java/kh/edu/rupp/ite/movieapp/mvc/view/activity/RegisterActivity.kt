package kh.edu.rupp.ite.movieapp.mvc.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kh.edu.rupp.ite.movieapp.databinding.RegisterScreenBinding
import kh.edu.rupp.ite.movieapp.mvc.model.RegisterModel
import kh.edu.rupp.ite.movieapp.service.ApiServices
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.movieapp.MainActivity

class RegisterActivity : AppCompatActivity() {
    private val registerUrl = "https://ebooks-store-cambo.000webhostapp.com/api/auth/register"
    private lateinit var registerBinding: RegisterScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = RegisterScreenBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.btSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerBinding.btnRegister.setOnClickListener {
            val email = registerBinding.editTextEmail.text.toString().trim()
            val name = registerBinding.editTextFullName.text.toString().trim()
            val password = registerBinding.editTextPassword.text.toString().trim()

            // Perform validation here (e.g., check if fields are not empty, passwords match, etc.)


            val registerModel = RegisterModel(name, email, password)
            val httpClient = Retrofit.Builder()
                .baseUrl("http://192.168.100.13:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiServices: ApiServices = httpClient.create(ApiServices::class.java)
            apiServices.register(registerModel).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // Handle successful registration
                        Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Handle unsuccessful registration
                        Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Handle failure
                    Toast.makeText(this@RegisterActivity, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}

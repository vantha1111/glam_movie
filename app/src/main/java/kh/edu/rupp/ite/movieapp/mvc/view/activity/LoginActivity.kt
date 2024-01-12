package kh.edu.rupp.ite.movieapp.mvc.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kh.edu.rupp.ite.movieapp.MainActivity
import kh.edu.rupp.ite.movieapp.databinding.LogInScreenBinding
import kh.edu.rupp.ite.movieapp.mvc.model.LoginModel
import kh.edu.rupp.ite.movieapp.service.ApiServices
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: LogInScreenBinding
    companion object {
        var accessToken = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = LogInScreenBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

//        loginBinding.btSignup.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }

        // Handle login button click
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.editTextEmail.text.toString().trim()
            val password = loginBinding.editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val loginModel = LoginModel(email, password)

                val httpClient = Retrofit.Builder()
                    .baseUrl("http://192.168.100.13:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiServices: ApiServices = httpClient.create(ApiServices::class.java)

                apiServices.login(loginModel).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()?.string()
                            // Get response body as a string
                            Log.d("LoginActivity", "Response body: ${responseBody.toString()}")
                            try {
                                val jsonResponse = JSONObject(responseBody)
                                val status = jsonResponse.getBoolean("status")

                                if (status == true) {
                                    // Login successful
//                                    val access_Token = jsonResponse.getString("access_token")
//                                    accessToken = access_Token
                                    // Save the access token or perform necessary actions upon successful login
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                   // Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                                    startActivity(intent)
                                } else {
                                    // Login failed
                                    val message = jsonResponse.getString("message")
                                    Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                                }

                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Toast.makeText(this@LoginActivity, "Error parsing response", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            // Handle other HTTP status codes
                            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("LoginActivity", "Login failed. Error: ${t.message}", t)
                        Toast.makeText(this@LoginActivity, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
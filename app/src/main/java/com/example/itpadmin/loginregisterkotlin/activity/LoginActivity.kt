package com.example.itpadmin.loginregisterkotlin.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.itpadmin.loginregisterkotlin.R
import com.example.itpadmin.loginregisterkotlin.model.ResponseApi
import com.example.itpadmin.loginregisterkotlin.retrofit.ApiClient
import com.example.itpadmin.loginregisterkotlin.retrofit.ApiInterface
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var mApiInterface: ApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //mApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        mApiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)
        btnLogin.setOnClickListener { validForm(inputEmail.text.toString(), inputPassword.text.toString()) }
    }

    private fun validForm(email: String, password: String) {
        if (email.isEmpty()) {
            inputEmail.setError("Email is required!")
        } else if (password.isEmpty()) {
            inputPassword.setError("Password is required!")
        } else {
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {

        val progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setInverseBackgroundForced(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setMessage("Loading..")
        progressDialog.show()

        val loginCall = mApiInterface?.login(email, password)
        loginCall?.enqueue(object : Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                progressDialog.dismiss()
                val error = response.body()?.error
                val message = response.body()?.message
                if (error!!.not()) {
                    val user = response.body()?.data
                    Log.i("email", user?.user_email)
                    /*print(user?.user_name)
                    print(user?.user_phone)*/
                   /* val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()*/
                } else {
                    Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@LoginActivity, "Connection Error, please try again.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

package edu.scranton.ctleweb.droidtrack

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private var mEmailView: TextView? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var focusView: View? = null
    private var email: String? = null
    private var password: String? = null

    private val interfaceService: ProjtrackService
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ProjtrackService::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mEmailView = findViewById<View>(R.id.email) as TextView
        mPasswordView = findViewById<View>(R.id.password) as EditText
        mPasswordView!!.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == R.id.email || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        val mEmailSignInButton = findViewById<View>(R.id.email_sign_in_button) as Button
        mEmailSignInButton.setOnClickListener { attemptLogin() }
        mLoginFormView = findViewById(R.id.login_form)
        mProgressView = findViewById(R.id.login_progress)
    }

    private fun attemptLogin() {
        val mCancel = this.loginValidation()
        if (mCancel) {
            focusView!!.requestFocus()
        } else {
            loginProcessWithRetrofit(email, password)
        }
    }

    private fun loginValidation(): Boolean {
        mEmailView!!.error = null
        mPasswordView!!.error = null
        email = mEmailView!!.text.toString()
        password = mPasswordView!!.text.toString()
        var cancel = false
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        }
        return cancel
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= 23) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
            mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    private fun loginProcessWithRetrofit(email: String?, password: String?) {
        val mApiService = this.interfaceService
        Log.d("LoginService", email)
        val mService = mApiService.authenticate(email, password)
        mService.enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                val mLoginObject = response.body()
                if (mLoginObject == null) {
                    Log.d("LoginService", "Login failed. Login server returned null response.")
                    Log.d("LoginService", "Server returned response code " + response.code())
                    Log.d("LoginService", response.raw().request().url().toString())
                    call.cancel()
                    Toast.makeText(this@LoginActivity, "Connecting to $BASE_URL failed.", Toast.LENGTH_LONG).show()
                } else {
                    var returnedResponse = mLoginObject.token
                    returnedResponse = "Token " + returnedResponse
                    Toast.makeText(this@LoginActivity, "Retrieved login token.", Toast.LENGTH_LONG).show()
                    val loginIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    loginIntent.putExtra("TOKEN", returnedResponse)
                    startActivity(loginIntent)
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                Log.d("LoginService", "Connection failed.")
                Log.d("LoginService", t.toString())
                call.cancel()
                Toast.makeText(this@LoginActivity, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show()
            }
        })
        showProgress(true)
    }

    companion object {
        val BASE_URL = "http://10.31.227.164:8080/"
    }
}
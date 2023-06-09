package com.example.capstoneprojectngirit.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.InputDevice
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityLoginBinding
import com.example.capstoneprojectngirit.main.MainActivity
import com.example.capstoneprojectngirit.register.RegisterActivity
import com.example.capstoneprojectngirit.user.UserModel
import com.example.capstoneprojectngirit.user.UserPreference

class LoginActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var loginViewModel:LoginViewModel
    private lateinit var preferences:SharedPreferences
    private lateinit var userPreference:UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViewModel()
        setupPreference()
        setupButton()
        buttonAction()
        registerAction()
        playAnimation()
    }

    private fun setupViewModel(){
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.isLoading.observe(this){showLoading(it)}
        loginViewModel.toastMessage.observe(this){toast(it)}
    }

    private fun setupPreference(){
        preferences = getSharedPreferences(EXTRA_PREF,Context.MODE_PRIVATE)
        userPreference = UserPreference(this)
    }

    private fun login(){
        val email = binding.edEmail.text.toString().trim()
        val password = binding.edPassword.text.toString().trim()

        when{
            email.isEmpty()->{
                binding.edLayoutEmail.error = getString(R.string.email_alert)
            }
            password.isEmpty()->{
                binding.edLayoutPassword.error = getString(R.string.password_alert)
            }
            else->{
                if (binding.edLayoutPassword.error.isNullOrEmpty()){
                    loginViewModel.login(email, password)
                }
                loginViewModel.login.observe(this@LoginActivity){
                    binding.pbLogin.visibility = View.VISIBLE
                    if (it!=null){
                        AlertDialog.Builder(this).apply {
                            setTitle(getString(R.string.login_success))
                            setPositiveButton(getString(R.string.next)){_,_->
                                val intent = Intent(context,MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                finish()
                            }
                            create()
                            show()

                        }
                        saveUser(UserModel(it.userName,it.userId,true))
                    }
                }
            }
        }
    }
    private fun setupButton(){
        val passwordEditText=binding.edPassword
        val loginButton = binding.btLogin
        loginButton.isEnabled = false

        passwordEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString().trim()
                loginButton.isCheckable = password.length>=8
            }

        })
    }

    private fun buttonAction(){
        binding.btLogin.setOnClickListener {
            login()
            val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.hideSoftInputFromWindow(it.windowToken,0)
        }
    }

    private fun saveUser(user:UserModel){
        userPreference.setUser(user)
    }
    private fun registerAction(){
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun toast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading:Boolean){
        binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.ivLogo,View.TRANSLATION_X,-30f,30f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        val logo = ObjectAnimator.ofFloat(binding.ivLogo,View.ALPHA,1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.tvWarning,View.ALPHA,1f).setDuration(500)
        val tvEmail = ObjectAnimator.ofFloat(binding.tvEmail,View.ALPHA,1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.edLayoutEmail,View.ALPHA,1f).setDuration(500)
        val tvPassword = ObjectAnimator.ofFloat(binding.tvPassword,View.ALPHA,1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.edLayoutPassword,View.ALPHA,1f).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.btLogin,View.ALPHA,1f).setDuration(500)
        val tvQuestion = ObjectAnimator.ofFloat(binding.tvQuetion,View.ALPHA,1f).setDuration(500)
        val tvRegister = ObjectAnimator.ofFloat(binding.tvRegister,View.ALPHA,1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                logo,title,tvEmail,edEmail,tvPassword,edPassword,button
            )
            playTogether(tvQuestion,tvRegister)
            startDelay = 500
        }.start()
    }

    companion object{
        const val EXTRA_PREF = "extra_pref"
    }
}
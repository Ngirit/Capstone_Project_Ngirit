package com.example.capstoneprojectngirit.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.capstoneprojectngirit.databinding.ActivityLoginBinding
import com.example.capstoneprojectngirit.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupButton()
        registerAction()
        playAnimation()
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
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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
}
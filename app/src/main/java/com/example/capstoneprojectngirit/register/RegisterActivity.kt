package com.example.capstoneprojectngirit.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityRegisterBinding
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupRegisterButton()
        playAnimation()

    }

    private fun setupRegisterButton(){
        val passwordEditText = binding.edPassword
        val registerButton = binding.btRegister
        registerButton.isEnabled = false

        passwordEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString().trim()
                registerButton.isEnabled = password.length >= 8
            }

        })
    }

    private fun showLoading(isLoading:Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X,-30f,30f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        val logo = ObjectAnimator.ofFloat(binding.ivLogo,View.ALPHA,1f).setDuration(500)
        val tvWelcome = ObjectAnimator.ofFloat(binding.tvWelcomeRegister,View.ALPHA,1f).setDuration(500)
        val tvWarning = ObjectAnimator.ofFloat(binding.tvWarning,View.ALPHA,1f).setDuration(500)
        val tvName = ObjectAnimator.ofFloat(binding.tvName,View.ALPHA,1f).setDuration(500)
        val edName = ObjectAnimator.ofFloat(binding.edLayoutName,View.ALPHA,1f).setDuration(500)
        val tvEmail = ObjectAnimator.ofFloat(binding.tvEmail,View.ALPHA,1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.edLayoutEmail,View.ALPHA,1f).setDuration(500)
        val tvPassword = ObjectAnimator.ofFloat(binding.tvPassword,View.ALPHA,1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.edLayoutPassword,View.ALPHA,1f).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.btRegister,View.ALPHA,1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                logo,tvWelcome,tvWarning,tvName,edName,tvEmail,edEmail,tvPassword,edPassword,button
            )
            startDelay = 500
        }.start()
    }
}
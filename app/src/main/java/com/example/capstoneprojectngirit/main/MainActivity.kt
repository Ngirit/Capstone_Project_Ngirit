package com.example.capstoneprojectngirit.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityMainBinding
import com.example.capstoneprojectngirit.login.LoginActivity
import com.example.capstoneprojectngirit.ml.ModelCapstone
import com.example.capstoneprojectngirit.recomendation.RecomendationActivity
import com.example.capstoneprojectngirit.user.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer


class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.PUBLICATION){
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userPreference = UserPreference(this)



        //setupViewModel()
        userValidation()
        buttonAction()
        buttonSetup()

    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getUser(userPreference.getUser().userId)
        mainViewModel.isLoading.observe(this){showLoading(it)}
    }

    private fun userValidation(){
        if (!userPreference.getUser().isLogin){
            val userLogin = userPreference.getUser().isLogin
            Log.d(TAG,userLogin.toString())
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun buttonSetup(){
        binding.rgKategori.setOnCheckedChangeListener{group,checkedId->
            binding.btSearch.isEnabled=checkedId!=-1
        }
    }

    private fun buttonAction(){

        binding.btSearch.setOnClickListener {

            val intent = Intent(this,RecomendationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun uploadRecomendation(){
        val budget = binding.edBiaya.text.toString()//.toRequestBody("text/plain".toMediaType())
        val location = binding.edLokasi.text.toString()//.toRequestBody("text/plain".toMediaType())
        //val outputList = modelCapstone.inference(budget, location, selectedId)
        val selectedId=binding.rgKategori.checkedRadioButtonId
        if (selectedId!=-1){
            val radioButton:RadioButton=findViewById(selectedId)
            val selectedText = radioButton.text.toString().toRequestBody("text/plain".toMediaType())
            //Toast.makeText(this,"$selectedText",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading:Boolean){
        binding.pbMain.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout_action->{
                userPreference.logout()
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
                true
            }else ->{return super.onOptionsItemSelected(item)}
        }
    }

    companion object{
        const val TAG = "extra_tag"
    }
}
package com.example.androidproject01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.androidproject01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.idBtnSave.setOnClickListener {
            Log.d("MainActivity", "Save button pressed")

            if(!binding.idName.text.isEmpty()){
                binding.idTxtName.text = binding.idName.text
                binding.idTxtCode.text = binding.idCode.text
                binding.idTxtDescription.text = binding.idDescription.text
                binding.idTxtPrice.text = binding.idPrice.text
            } else {
                Toast.makeText(this,
                "Please enter the name",
                Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainActivity", "onSaveInstanceState")

        val product = Product(
            name = binding.idName.text.toString(),
            description = binding.idDescription.text.toString(),
            code = binding.idCode.text.toString(),
            price = if(binding.idPrice.text.isNotEmpty()) binding.idPrice.text.toString().toDouble() else 0.0
        )

        outState.putSerializable("product", product)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MainActivity", "onRestoreInstanceState")

        val product = savedInstanceState.getSerializable("product") as Product
        binding.idTxtName.text = product.name
        binding.idTxtDescription.text = product.description
        binding.idTxtCode.text = product.code
        binding.idTxtPrice.text = product.price.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "OnDestroy")
    }
}
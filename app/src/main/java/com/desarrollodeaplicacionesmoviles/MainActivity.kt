package com.desarrollodeaplicacionesmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.desarrollodeaplicacionesmoviles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Me hizo perder un montón de tiempo
        setContentView(binding.root)

        mainViewModel.strings.observe(this) {
            binding.textInputLayout.placeholderText = "${it.txt1}"
            binding.textInputLayout2.placeholderText = "${it.txt2}"
            binding.textView.text = "${it.result}"
        }

        binding.btnComparar.setOnClickListener {
            println("boton")
            mainViewModel.comparar()
        }

        binding.textInputLayout.addOnEditTextAttachedListener {
            val txt = it.editText
            txt?.addTextChangedListener(afterTextChanged = {
                // Actualizar el valor de txt1 en el viewmodel
                mainViewModel.updateTxt1(it.toString())
            })
        }

        binding.textInputLayout2.addOnEditTextAttachedListener {
            val txt = it.editText
            txt?.addTextChangedListener(afterTextChanged = {
                // Actualizar el valor de txt1 en el viewmodel
                mainViewModel.updateTxt2(it.toString())
            })
        }

    }
}
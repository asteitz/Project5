package com.example.project5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project5.databinding.ActivityMainBinding
import com.example.project5.databinding.Fragment1Binding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    private  lateinit var bindingFrag: Fragment1Binding
    private  lateinit var viewModel: TheViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TheViewModel::class.java)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)
        var source = ""
        var trans = ""
        bindingMain.Source.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                bindingMain.englishSource.id -> {
                    source = "English"
                }
                bindingMain.spanishSource.id -> {
                    source = "Spanish"
                }
                bindingMain.germanSource.id -> {
                    source = "German"
                }
            }
        }

        bindingMain.Trans.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                bindingMain.englishTrans.id -> {
                    trans = "English"
                }
                bindingMain.spanishTrans.id -> {
                    trans = "Spanish"
                }
                bindingMain.germanTrans.id -> {
                    trans = "German"
                }
            }
        }
        viewModel.bindingFrag.observe(this, Observer { bindingFrag ->
        var options = TranslatorOptions.Builder()
        if(source != "") {
            if (source == "English") {
                options
                    .setSourceLanguage(TranslateLanguage.ENGLISH)

            } else if (source == "Spanish") {
                options
                    .setSourceLanguage(TranslateLanguage.SPANISH)

            } else {
                options
                    .setSourceLanguage(TranslateLanguage.GERMAN)
            }
        }
        if(trans != ""){
            if (trans == "English") {
                options
                    .setTargetLanguage(TranslateLanguage.ENGLISH)
            }
            else if (trans == "Spanish") {
                options
                    .setTargetLanguage(TranslateLanguage.SPANISH)
            }
            else {
                options
                    .setTargetLanguage(TranslateLanguage.GERMAN)
            }

        }
        var finalOptions = options.build()
        bindingFrag.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var curText = s.toString()
                val translator = Translation.getClient(finalOptions)
                var finalText = translator.translate(curText).toString()
                bindingMain.traslatedTextView.text = finalText


            }
        })

        })
    }

    
}
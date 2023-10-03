package com.example.project5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
            Log.d("MyActivity", "Observed a change in bindingFrag: $bindingFrag")
            var options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build();
            var count = 0
        bindingFrag.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (source != "") {
                    if (source == "English") {
                        if (trans == "Spanish") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.ENGLISH)
                                .setTargetLanguage(TranslateLanguage.SPANISH)
                                .build();
                        }
                        if (trans == "German") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.ENGLISH)
                                .setTargetLanguage(TranslateLanguage.GERMAN)
                                .build();
                        }
                    } else if (source == "Spanish") {
                        if (trans == "English") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.SPANISH)
                                .setTargetLanguage(TranslateLanguage.ENGLISH)
                                .build();
                        }
                        if (trans == "German") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.SPANISH)
                                .setTargetLanguage(TranslateLanguage.GERMAN)
                                .build();
                        }
                    } else {
                        if (trans == "English") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.GERMAN)
                                .setTargetLanguage(TranslateLanguage.ENGLISH)
                                .build();
                        }
                        if (trans == "Spanish") {
                            options = TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.GERMAN)
                                .setTargetLanguage(TranslateLanguage.SPANISH)
                                .build();
                        }
                    }
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                var curText = s.toString()
                val translator = Translation.getClient(options)
                translator.downloadModelIfNeeded().addOnFailureListener { exception ->
                    Log.e("IfNeededDownload", "Download failed: ${exception.message}")
                }
                var task = translator.translate(curText)

                task.addOnSuccessListener { translatedText ->
                    if(count == 3) {
                        options
                        var finalText = translatedText
                        bindingMain.traslatedTextView.text = finalText
                    }
                    else{
                        options
                        var finalText = translatedText
                        bindingMain.traslatedTextView.text = finalText
                        count++
                    }
                }
                task.addOnFailureListener { exception ->
                    Log.e("Translate","Translation failed: ${exception.message}")
                }



            }
        })

        })
    }

    
}
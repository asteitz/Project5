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
    /**
     * Creating the constructor and initializing the variables that are used in the view grouping throughout the file
     *
     * @variable bindingMain
     * @variable bindingFrag
     * @variable viewModel
     *
     */
    private lateinit var bindingMain: ActivityMainBinding
    private  lateinit var bindingFrag: Fragment1Binding
    private  lateinit var viewModel: TheViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Overriding the onCreate() to accommodate the view groups
         * takes the view groups, binds them, and the then uses them to pull in the text and identify which values have been assigned.
         *
         * Uses the binding to inflate the view
         * Sets global variables to their set languages selected by user (using view groups)
         * @param Bundle of safeargs passed in
         * @return View
         */
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TheViewModel::class.java)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)
        var source = ""
        var trans = ""
        // checks which has been clicked using the on listener
        bindingMain.Source.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                // if the binding id is set to English as the source
                bindingMain.englishSource.id -> {
                    source = "English"
                }
                // if the binding id is set to Spanish as the source
                bindingMain.spanishSource.id -> {
                    source = "Spanish"
                }
                // if the binding id is set to German as the source
                bindingMain.germanSource.id -> {
                    source = "German"
                }
            }
        }
        // same process but for setting the language ot translate to
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

        // used when observing the text entered by the user pulling from textview in the fragment.kt file
        viewModel.bindingFrag.observe(this, Observer { bindingFrag ->
            Log.d("MyActivity", "Observed a change in bindingFrag: $bindingFrag")
            var options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build();
            var count = 0


        bindingFrag.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /**
                 * Overriding the beforeTextChanged() to accommodate the accomidate words being entered in real time pulling from the TextView in Fragment
                 *
                 *
                 * p0 is a char sequence of characters to help define where the word is and what is should be translated to
                 * deploys according source and translated languages
                 * @param p0
                 * @return builds up the string to be translated to
                 */
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
                /**
                 * Overriding the afterTextChanged() to check that as the words were entered they were translated correctly
                 *
                 * s is like a string where we can change the text - great for in this case "proof reading" the translated text
                 * if something is not translatable (charecters instead of letters, etc.) there is an error message displayed instead of the app crashing
                 * @param s
                 * @return translated text in the text view
                 */

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
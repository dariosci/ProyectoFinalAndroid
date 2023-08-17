package com.curso.proyectofinal.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.LocaleListCompat
import com.curso.proyectofinal.R
import com.curso.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val btnComparar = findViewById<AppCompatButton>(R.id.btnComparar)
        val texto1 = findViewById<AppCompatEditText>(R.id.texto1)
        val texto2 = findViewById<AppCompatEditText>(R.id.texto2)
        val resultado = findViewById<AppCompatTextView>(R.id.resultComp)

        mainViewModel.modelo.observe(this){
//          binding.texto1.text = "${it.texto1}"
//          binding.texto2.text = ${it.texto2}
            binding.resultComp.text = it.resultado
        }



        //IDIOMA
        val sharedPref = application.getSharedPreferences("idioma",Context.MODE_PRIVATE) ?:return
        val defaultValue = 0
        val idiomaGuardado = sharedPref.getInt("idioma",defaultValue)

        when(idiomaGuardado) {
            0 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("es-ES")
                AppCompatDelegate.setApplicationLocales(appLocale)
                //selectoridioma.setSelection(idiomaGuardado)
            }

            1 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en-EN")
                AppCompatDelegate.setApplicationLocales(appLocale)
                //selectoridioma.setSelection(idiomaGuardado)
            }
        }

        //MODO CLARO/OSCURO
        val sharedPreferences = application.getSharedPreferences("modo", Context.MODE_PRIVATE) ?:return
        val nightMode = sharedPreferences.getBoolean("night",false)
        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        //COMPARACION DE LOS TEXTOS
        binding.btnComparar.setOnClickListener{
            mainViewModel.comparar(texto1.text.toString(), texto2.text.toString())
            //TRADUCCIÓN SEGÚN EL IDIOMA
            if (resultado.text == "iguales") resultado.text=getString(R.string.textoigual) else resultado.text=getString(R.string.textodiferente)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.configurar -> abrir_config()
            R.id.accion_salir -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)


    }
    fun abrir_config(){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }



}
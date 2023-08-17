package com.curso.proyectofinal.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.LocaleListCompat
import com.curso.proyectofinal.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        val selectoridioma = findViewById<AppCompatSpinner>(R.id.selectoridioma)
        val btn_Guardar: Button = findViewById(R.id.guardarIdioma)

        //IDIOMA
        val sharedPref = application.getSharedPreferences("idioma",Context.MODE_PRIVATE) ?:return //prueba
        val defaultValue = 0
        val idiomaGuardado = sharedPref.getInt("idioma",defaultValue)

        when(idiomaGuardado) {
            0 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("es-ES")
                AppCompatDelegate.setApplicationLocales(appLocale)
                selectoridioma.setSelection(idiomaGuardado)
            }

            1 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en-EN")
                AppCompatDelegate.setApplicationLocales(appLocale)
                selectoridioma.setSelection(idiomaGuardado)
            }
        }

        //MODO CLARO/OSCURO
        val switch = findViewById<SwitchCompat>(R.id.clarooscuro)
        val sharedPreferences = application.getSharedPreferences("modo", Context.MODE_PRIVATE) ?:return
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night",false)

        //SI MODO OSCURO ESTÁ ACTIVADO... MOSTRAR EN EL SWITCH
        if (nightMode){
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        //AL PRESIONAR EL SWITCH ACTIVA EL CAMBIO Y GUARDA EL MODO SELECCIONADO
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked){ //MODO CLARO
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()
            }else{          //MODO OSCURO
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night",true)
                editor.apply()
            }
            Toast.makeText(this,"El Modo Claro/Oscuro se guarda automáticamente",Toast.LENGTH_SHORT).show()
        }
        //GUARDAR/APLICAR CONFIGURACIÓN DEL IDIOMA
        btn_Guardar.setOnClickListener{
            guardar(selectoridioma)
            Toast.makeText(this,"Se ha guardado la Configuración",Toast.LENGTH_SHORT).show()
            when(selectoridioma?.selectedItem){
                //APLICANDO IDIOMA EN INGLÉS
                "English" -> {
                    val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags("en-EN")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
                "Inglés" -> {
                    val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags("en-EN")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
                //APLICANDO IDIOMA EN ESPAÑOL
                "Español" -> {
                    val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags("es-ES")
                   AppCompatDelegate.setApplicationLocales(appLocale)
                }
                "Spanish" -> {
                    val appLocale : LocaleListCompat = LocaleListCompat.forLanguageTags("es-ES")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun guardar(selectoridioma:AppCompatSpinner){ //GUARDAR EL IDIOMA SELECCIONADO PARA LA PROXIMA EJECUCIÓN
        val sharedPref = application.getSharedPreferences("idioma", Context.MODE_PRIVATE) ?: return
        val ididioma = selectoridioma.selectedItemId.toString().toInt()
            with(sharedPref.edit()) {
                putInt("idioma", ididioma)
                apply()
            }
    }
}

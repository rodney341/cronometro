package com.example.cronometro

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnStartStop: Button
    private lateinit var btnReset: Button
    private lateinit var tvElapsedTime: TextView
    private var isRunning = false
    private var startTimeMillis: Long = 0
    private val handler = Handler()
    private lateinit var databaseHelper: MyDatabaseHelper
    private lateinit var repositorioLugares: RepositorioLugares

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartStop = findViewById(R.id.btnStartStop)
        btnReset = findViewById(R.id.btnReset)
        tvElapsedTime = findViewById(R.id.tvElapsedTime)

        repositorioLugares = MyDatabaseHelper()

        btnStartStop.setOnClickListener(View.OnClickListener {
            if (isRunning) {
                resetTimer()
                stopTimer()
                for (i in 0 until repositorioLugares.tamaño()) {
                    println(repositorioLugares.elemento(i).toString())
                }
            } else {
                startTimer()
            }
        })

        btnReset.setOnClickListener(View.OnClickListener {
            resetTimer()
            saveTimeToDatabase()
        })
    }

    private fun startTimer() {
        if (!isRunning) {
            startTimeMillis = SystemClock.elapsedRealtime()
            handler.postDelayed(timerRunnable, 0)
            btnStartStop.text = "Detener"
            isRunning = true

            btnStartStop.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        }

    }

    private fun stopTimer() {
        handler.removeCallbacks(timerRunnable)
        btnStartStop.text = "Iniciar"
        isRunning = false
        btnStartStop.backgroundTintList = ColorStateList.valueOf(Color.RED)
    }

    private fun resetTimer() {
        startTimeMillis = SystemClock.elapsedRealtime()
        tvElapsedTime.text = "Tiempo transcurrido: 00:00"
    }

    private val timerRunnable: Runnable = object : Runnable {
        override fun run() {
            val elapsedTimeMillis = SystemClock.elapsedRealtime() - startTimeMillis
            val minutes = (elapsedTimeMillis / 60000).toInt()
            val seconds = ((elapsedTimeMillis % 60000) / 1000).toInt()
            val elapsedTime = String.format("%02d:%02d", minutes, seconds)
            tvElapsedTime.text = "Tiempo transcurrido: $elapsedTime"
            handler.postDelayed(this, 1000)
        }
    }

    private fun saveTimeToDatabase() {
        val currentTimeMillis = System.currentTimeMillis()
        val locations: RepositorioLugares = MyDatabaseHelper()
        repositorioLugares.guardar(GeoPunto(-0.166093, 38.995656))
        println("se agregó un elemnto")

    }
}

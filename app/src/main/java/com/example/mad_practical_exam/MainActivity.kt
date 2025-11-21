package com.example.mad_practical_exam

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var scheduleCards: List<Pair<View, String>>
    private lateinit var filterButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val root = findViewById<ConstraintLayout>(R.id.main)

        // Filter buttons
        val btnAll = findViewById<Button>(R.id.btnFilterAll)
        val btnKeynote = findViewById<Button>(R.id.btnFilterKeynote)
        val btnWorkshop = findViewById<Button>(R.id.btnFilterWorkshop)
        val btnNetworking = findViewById<Button>(R.id.btnFilterNetworking)

        filterButtons = listOf(btnAll, btnKeynote, btnWorkshop, btnNetworking)

        // Schedule cards (views)
        val cardOpening = findViewById<View>(R.id.cardOpening)      // type: ALL
        val cardFutureAI = findViewById<View>(R.id.cardFutureAI)    // type: KEYNOTE
        val cardKotlin = findViewById<View>(R.id.cardKotlinMulti)   // type: WORKSHOP
        val cardLunch = findViewById<View>(R.id.cardLunch)          // type: NETWORKING
        val cardCloud = findViewById<View>(R.id.cardCloud)          // type: WORKSHOP

        scheduleCards = listOf(
            cardOpening to "ALL",
            cardFutureAI to "KEYNOTE",
            cardKotlin to "WORKSHOP",
            cardLunch to "NETWORKING",
            cardCloud to "WORKSHOP"
        )

        // Default: ALL selected
        setActiveFilter(btnAll, "ALL")

        btnAll.setOnClickListener { setActiveFilter(btnAll, "ALL") }
        btnKeynote.setOnClickListener { setActiveFilter(btnKeynote, "KEYNOTE") }
        btnWorkshop.setOnClickListener { setActiveFilter(btnWorkshop, "WORKSHOP") }
        btnNetworking.setOnClickListener { setActiveFilter(btnNetworking, "NETWORKING") }

        // Theme switch -> simple background change (light/dark)
        val switchTheme = findViewById<SwitchCompat>(R.id.switchTheme)
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                root.setBackgroundColor(Color.parseColor("#050510")) // dark
            } else {
                root.setBackgroundColor(Color.parseColor("#F5F5FF")) // light
            }
        }

        // Bottom buttons just show Toast (you can replace with real logic)
        val btnBuyTickets = findViewById<Button>(R.id.btnBuyTickets)
        val btnCalendar = findViewById<Button>(R.id.btnCalendar)

        btnBuyTickets.setOnClickListener {
            Toast.makeText(this, "Buy Tickets clicked", Toast.LENGTH_SHORT).show()
        }

        btnCalendar.setOnClickListener {
            Toast.makeText(this, "Calendar clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setActiveFilter(activeButton: Button, type: String) {
        // Button states
        for (btn in filterButtons) {
            if (btn == activeButton) {
                btn.setBackgroundColor(Color.parseColor("#3F51B5"))
                btn.setTextColor(Color.WHITE)
            } else {
                btn.setBackgroundColor(Color.parseColor("#E0E0E0"))
                btn.setTextColor(Color.BLACK)
            }
        }

        // Show/hide schedule cards
        for ((view, sessionType) in scheduleCards) {
            view.visibility =
                if (type == "ALL" || type == sessionType) View.VISIBLE else View.GONE
        }
    }
}

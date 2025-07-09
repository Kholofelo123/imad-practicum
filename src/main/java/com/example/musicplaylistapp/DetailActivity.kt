// DetailActivity.kt
package com.example.musicplaylistapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val songListTextView = findViewById<TextView>(R.id.songListTextView)
        val averageTextView = findViewById<TextView>(R.id.averageTextView)
        val showSongsButton = findViewById<Button>(R.id.showSongsButton)
        val showAverageButton = findViewById<Button>(R.id.showAverageButton)
        val returnButton = findViewById<Button>(R.id.returnButton)

        showSongsButton.setOnClickListener {
            var output = ""
            for (i in MainActivity.songTitles.indices) {
                output += """
                    Song: ${MainActivity.songTitles[i]}
                    Artist: ${MainActivity.artistNames[i]}
                    Rating: ${MainActivity.ratings[i]}
                    Comment: ${MainActivity.comments[i]}

                """.trimIndent() + "\n"
            }
            songListTextView.text = output.ifEmpty { "No songs in playlist" }
        }

        showAverageButton.setOnClickListener {
            val totalRatings = MainActivity.ratings.sum()
            val count = MainActivity.ratings.size
            if (count > 0) {
                val average = totalRatings.toDouble() / count
                averageTextView.text = "Average Rating: %.2f".format(average)
            } else {
                averageTextView.text = "No ratings to calculate average"
            }
        }

        returnButton.setOnClickListener {
            finish()
        }
    }
}

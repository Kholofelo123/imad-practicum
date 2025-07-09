// MainActivity.kt
package com.example.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val songTitles = ArrayList<String>()
        val artistNames = ArrayList<String>()
        val ratings = ArrayList<Int>()
        val comments = ArrayList<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleInput = findViewById<EditText>(R.id.songTitleInput)
        val artistInput = findViewById<EditText>(R.id.artistNameInput)
        val ratingInput = findViewById<EditText>(R.id.ratingInput)
        val commentInput = findViewById<EditText>(R.id.commentInput)

        val addButton = findViewById<Button>(R.id.addSongButton)
        val viewButton = findViewById<Button>(R.id.viewPlaylistButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val artist = artistInput.text.toString()
            val rating = ratingInput.text.toString().toIntOrNull()
            val comment = commentInput.text.toString()

            if (title.isEmpty() || artist.isEmpty() || rating == null || comment.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
            } else if (rating !in 1..5) {
                Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
            } else if (songTitles.size >= 4) {
                Toast.makeText(this, "Maximum 4 songs allowed", Toast.LENGTH_SHORT).show()
            } else {
                songTitles.add(title)
                artistNames.add(artist)
                ratings.add(rating)
                comments.add(comment)
                Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()

                titleInput.text.clear()
                artistInput.text.clear()
                ratingInput.text.clear()
                commentInput.text.clear()
            }
        }

        viewButton.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        exitButton.setOnClickListener {
            finishAffinity() // Closes app
        }
    }
}

package com.example.eyeqtest.focusshift

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.eyeqtest.EyewarmupFragmnet
import com.example.eyeqtest.R
import java.util.*
import kotlin.concurrent.schedule


class HiddenWordActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var congrats_layout:LinearLayout
    private lateinit var words_grid:GridLayout
    private lateinit var greenCheck:ImageView
    private lateinit var redX:ImageView

    private var xInitial = -1f
    private var yInitial = -1f

    private var xDiff = -1f
    private var yDiff = -1f

    private var prevXDiff = -1f
    private var prevYDiff = -1f

    enum class SwipeState { Undefined, Vertical, Horizontal }
    private var swipeState = SwipeState.Undefined

    private var cellWidth = 0

    private lateinit var showGeneratedWordsButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hidden_word)

        words_grid = findViewById(R.id.words_grid)
        greenCheck = findViewById(R.id.greenCheck)
        redX = findViewById(R.id.redX)

        cellWidth = resources.displayMetrics.widthPixels/10

        showGeneratedWordsButton = findViewById(R.id.showGeneratedWordsButton)
        showGeneratedWordsButton.setOnClickListener {
            // Display the generated words in a dialog
            showGeneratedWordsDialog()
        }


        val linearLayout: LinearLayout


        for (i in 0 until numWords){
            wordArray[i] = Word(words[i])
        }

        val childCount = words_grid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = words_grid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                linearLayout.getChildAt(t).setOnTouchListener(this)
            }
        }

        // Adjusting height fo the grid
        val params = words_grid.layoutParams as ConstraintLayout.LayoutParams
        params.height = resources.displayMetrics.widthPixels
        words_grid.layoutParams = params

        generateRandomLetters()
    }


    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // User started selecting cells
                v.background = ContextCompat.getDrawable(this, R.drawable.selected_cell_background)
                xInitial = event.x
                yInitial = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                // User's still selecting cells
                if (xInitial != -1f && yInitial != -1f) {
                    val tag = v.tag.toString()
                    val tagInt = tag.toInt()

                    xDiff = xInitial - event.x
                    yDiff = yInitial - event.y

                    if (swipeState == SwipeState.Undefined || swipeState == SwipeState.Horizontal) {
                        when {
                            xDiff > cellWidth -> {
                                // moving left
                                if (prevXDiff == -1f || prevXDiff != -1f && prevXDiff < xDiff) {
                                    selectSingleCell((tagInt - (xDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Horizontal
                                } else if (prevXDiff != -1f && prevXDiff > xDiff) {
                                    unselectSingleCell((tagInt - (prevXDiff / cellWidth).toInt()).toString())
                                }
                            }
                            (-1) * xDiff > cellWidth -> {
                                // moving right
                                if (prevXDiff == -1f || prevXDiff != -1f && prevXDiff > xDiff) {
                                    selectSingleCell((tagInt + -1 * (xDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Horizontal
                                } else if (prevXDiff != -1f && prevXDiff < xDiff) {
                                    unselectSingleCell((tagInt - (prevXDiff / cellWidth).toInt()).toString())
                                }
                            }
                        }
                    }

                    if (swipeState == SwipeState.Undefined || swipeState == SwipeState.Vertical) {
                        when {
                            yDiff > cellWidth -> {
                                // moving up
                                if (prevYDiff == -1f || prevYDiff != -1f && prevYDiff < yDiff) {
                                    selectSingleCell((tagInt - 10 * (yDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Vertical
                                } else if (prevYDiff != -1f && prevYDiff > yDiff) {
                                    unselectSingleCell((tagInt - 10 * (yDiff / cellWidth).toInt()).toString())
                                }
                            }
                            (-1) * yDiff > cellWidth -> {
                                // moving down
                                if (prevYDiff == -1f || prevYDiff != -1f && prevYDiff > yDiff) {
                                    selectSingleCell((tagInt + -10 * (yDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Vertical
                                } else if (prevYDiff != -1f && prevYDiff < yDiff) {
                                    unselectSingleCell((tagInt - 10 * (yDiff / cellWidth).toInt()).toString())
                                }
                            }
                        }
                    }
                    prevXDiff = xDiff
                    prevYDiff = yDiff
                }
            }

            MotionEvent.ACTION_UP -> {
                // User's done selecting cells
                val tag = v.tag.toString()
                val tagInt = tag.toInt()
                var finalTag = tag

                if (swipeState == SwipeState.Horizontal) {
                    finalTag = when {
                        xDiff > cellWidth -> {
                            (tagInt - (xDiff / cellWidth).toInt()).toString()
                        }
                        -1 * xDiff > cellWidth -> {
                            (tagInt + -1 * (xDiff / cellWidth).toInt()).toString()
                        }
                        else -> tag
                    }
                } else if (swipeState == SwipeState.Vertical) {
                    finalTag = when {
                        yDiff > cellWidth -> {
                            (tagInt - 10 * (yDiff / cellWidth).toInt()).toString()
                        }
                        -1 * yDiff > cellWidth -> {
                            (tagInt + -10 * (yDiff / cellWidth).toInt()).toString()
                        }
                        else -> tag
                    }
                }
                checkIfRangeIsValid(v.tag.toString(), finalTag)
            }
        }
        return true
    }

    private fun showGeneratedWordsDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_generated_words, null)
        dialogBuilder.setView(dialogView)

        val generatedWordsTextView = dialogView.findViewById<TextView>(R.id.generatedWordsTextView)
        val wordsTextView = dialogView.findViewById<TextView>(R.id.wordsTextView)
        val closeButton = dialogView.findViewById<Button>(R.id.closeButton)

        val generatedWords = mutableListOf<String>()
        val foundWords = mutableListOf<String>() // Keep track of found words

        for (word in words) {
            for (i in 0 until gridSize) {
                for (j in 0 until gridSize) {
                    if (wordArray[words.indexOf(word)].checkLoc(i * 10 + j, i * 10 + j + word.length - 1, true) ||
                        wordArray[words.indexOf(word)].checkLoc(i * 10 + j, i * 10 + j + (word.length - 1) * 10, false)
                    ) {
                        generatedWords.add(word)
                        if (wordArray[words.indexOf(word)].found) {
                            foundWords.add(word)
                        }
                    }
                }
            }
        }

        if (generatedWords.isNotEmpty()) {
            val wordsToShow = generatedWords.joinToString("\n\n")
            wordsTextView.text = wordsToShow

            // Create a SpannableString to highlight found words in green
            val spannableText = SpannableString(wordsToShow)
            for (foundWord in foundWords) {
                val startIndex = wordsToShow.indexOf(foundWord)
                val endIndex = startIndex + foundWord.length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.green)) // Replace with your green color resource
                spannableText.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }

            wordsTextView.text = spannableText
        } else {
            wordsTextView.text = "No words found."
        }

        val dialog = dialogBuilder.create()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

        wordsTextView.gravity = Gravity.CENTER

        wordsTextView.textSize = resources.getDimension(R.dimen.generated_words_text_size)
    }




    private fun checkIfRangeIsValid(initTag: String, endTag: String){

        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.congrats_layout, null)
        val congratsDialog = Dialog(this)

        // Set click listeners for the buttons
        dialogView.findViewById<Button>(R.id.restartButton).setOnClickListener {
            // Restart the game
            congratsDialog.dismiss()
            reStart()

        }

        dialogView.findViewById<Button>(R.id.okButton).setOnClickListener {
            congratsDialog.dismiss()
            val eyewarmupFragment = EyewarmupFragmnet()
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView3, eyewarmupFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        congratsDialog.setContentView(dialogView)
        congratsDialog.setCancelable(false)

        congrats_layout = dialogView as LinearLayout // for visibility control
        congratsDialog.setOnDismissListener {
            congrats_layout.visibility = View.GONE
        }

        var found = false
        for(wordObj in wordArray){
            if(wordObj.checkLoc(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)){
                // we got a match
                if(wordObj.found){
                    // the word has been already found
                    xInitial = -1f
                    yInitial = -1f
                    xDiff = -1f
                    yDiff = -1f
                    prevXDiff = -1f
                    prevYDiff = -1f
                    swipeState = SwipeState.Undefined
                    return
                }

                markCellsAsFound(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)
                wordObj.found = true
                found = true
                break
            }
        }

        if (found){
            greenCheck.visibility = View.VISIBLE
            Timer("delay", false).schedule(500) {
                runOnUiThread{
                    greenCheck.visibility = View.GONE
                }
            }

            var foundWordCount = 0
            for (wordObj in wordArray) {
                if (wordObj.found) {
                    foundWordCount++
                    if (foundWordCount >= 5) {
                        congratsDialog.show()
                        break
                    }
                }
            }


        } else {
            redX.visibility = View.VISIBLE
            Timer("delay", false).schedule(500) {
                runOnUiThread{
                    redX.visibility = View.GONE
                }
            }
            unselectCellRange(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)
        }

        // resetting values
        xInitial = -1f
        yInitial = -1f
        xDiff = -1f
        yDiff = -1f
        swipeState = SwipeState.Undefined
    }

    private fun unselectCellRange(initTag: Int, endTag: Int, isHorizontal: Boolean){
        var start = initTag
        var end = endTag
        if (endTag < initTag){
            start = endTag
            end = initTag
        }
        if(isHorizontal){
            for (i in start..end){
                unselectSingleCell(i.toString())
            }
        } else {
            for (i in start..end step 10){
                unselectSingleCell(i.toString())
            }
        }
    }

    private fun selectSingleCell(tag: String){
        val childCount = words_grid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = words_grid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                if(linearLayout.getChildAt(t).tag == tag){
                    linearLayout.getChildAt(t).background = ContextCompat.getDrawable(this, R.drawable.selected_cell_background)
                    return
                }
            }
        }
    }


    private fun unselectSingleCell(tag: String){
        var tagInt = tag.toInt()
        val childCount = words_grid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = words_grid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                if(linearLayout.getChildAt(t).tag == tag){
                    if(!foundWordsFlags[tagInt/10][tagInt%10]){
                        linearLayout.getChildAt(t).background = ContextCompat.getDrawable(this,
                            R.drawable.congrats_background
                        )
                    }
                    return
                }
            }
        }
    }

    private fun markCellsAsFound(initTag: Int, endTag: Int, isHorizontal: Boolean){
        var start = initTag
        var end = endTag
        if (endTag < initTag){
            start = endTag
            end = initTag
        }
        if(isHorizontal){
            for (i in start..end){
                foundWordsFlags[i/10][i%10] = true
            }
        } else {
            for (i in start..end step 10){
                foundWordsFlags[i/10][i%10] = true
            }
        }
    }

    private fun unslectAllCells(){
        val childCount = words_grid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = words_grid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                linearLayout.getChildAt(t).background = ContextCompat.getDrawable(this,
                    R.drawable.congrats_background
                )
            }
        }
    }

    private fun generateRandomLetters(){

        gridFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        foundWordsFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        val rnd = Random()
        var toggle: Boolean = rnd.nextInt(2) != 0

        for(r in 0 until gridSize){
            for (c in 0 until gridSize){
                gridLetters[r][c] = vocabulary[rnd.nextInt(vocabulary.length)].toString()
            }
        }

        // Select 5 words randomly from the 'words' array
        val selectedWords = mutableListOf<String>()
        while (selectedWords.size < 5) {
            val randomIndex = rnd.nextInt(words.size)
            val randomWord = words[randomIndex]
            if (randomWord !in selectedWords) {
                selectedWords.add(randomWord)
            }
        }

        // Positioning randomly selected words in the grid
        for (word in selectedWords) {
            var found = false

            while (!found){
                var r = 0
                if(word.length < gridSize){
                    r = rnd.nextInt(gridSize - word.length)
                } else if (word.length > gridSize){
                    // maybe throw an exception?
                    break
                }

                // checking all rows or columns depending on the toggle value
                var start = rnd.nextInt(gridSize - 1)

                for (n in 0 until gridSize){
                    var _n = (n + start) % gridSize
                    // checking if the row or column is empty enough to place the word
                    for (i in r until r + word.length ) {
                        if(toggle){
                            // looking along the row
                            if(gridFlags[_n][i] && gridLetters[_n][i] != word[i-r].toString()) {
                                break
                            } else if (i == r + word.length - 1) {
                                // we've reached the end
                                found = true
                            }
                        } else {
                            // looking at along the column
                            if(gridFlags[i][_n]&& gridLetters[i][_n] != word[i-r].toString()) {
                                break
                            } else if (i == r + word.length - 1) {
                                // we've reached the end
                                found = true
                            }
                        }
                    }
                    if(found) {
                        // Registering location in Word object
                        if(toggle){
                            // Use the index of the word in the original 'words' array
                            val wordIndex = words.indexOf(word)
                            wordArray[wordIndex].setLoc(_n*10 + r, toggle)
                        } else {
                            // Use the index of the word in the original 'words' array
                            val wordIndex = words.indexOf(word)
                            wordArray[wordIndex].setLoc(r*10 + _n, toggle)
                        }

                        for (i in r until r + word.length ) {
                            if(toggle){
                                // filling along the row
                                gridLetters[_n][i] = word[i-r].toString()
                                gridFlags[_n][i] = true
                            } else {
                                // filling at along the column
                                gridLetters[i][_n] = word[i-r].toString()
                                gridFlags[i][_n] = true
                            }
                        }
                        break
                    }
                }
                toggle = !toggle
            }
        }

        // displaying letters
        val childCount = words_grid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = words_grid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                (linearLayout.getChildAt(t) as TextView).text = gridLetters[i][t]
            }
        }
    }



    fun reStart(view: View? = null){

        for (i in 0 until numWords){
            wordArray[i] = Word(words[i])
        }
        unslectAllCells()
        generateRandomLetters()
    }


    companion object {
        const val vocabulary = "ABCDEFGHIJKLMOPQRSTUVWSYZ"
        const val numWords = 10
        const val gridSize = 10

        var gridLetters = Array(gridSize) { Array<String>(gridSize) { "A" } }
        var gridFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        var foundWordsFlags = Array(gridSize) { BooleanArray(gridSize) { false } }

        val wordArray = Array<Word>(numWords) { Word("")}
        val words = arrayOf("OBJECTIVE", "VARIABLE", "MOBILE", "KOTLIN", "SWIFT", "JAVA", "ANDROID", "XML", "ACTIVITY", "FRAGMENT")
    }
}
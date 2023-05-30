package com.example.wordle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.text.HtmlCompat
import com.example.wordle.Constants.words
import java.util.Arrays
import java.util.Locale
import java.util.Random

class MainActivity : AppCompatActivity() {
    var editTextsArray: ArrayList<EditText>? = null
    var currentEditTextIndex = 0
    var allFiveLetterWords = ArrayList(listOf(*words))
    lateinit var currentWord: CharArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        editTextsArray = ArrayList()
        addAllEditTextsToArrayList()
        setClicksOnEditTexts()
        setUpKeyboard()
        startGame()
    }

    private fun startGame() {
        val randomObject = Random()
        currentWord =
            allFiveLetterWords[randomObject.nextInt(allFiveLetterWords.size)].toCharArray()
        for (i in 0..29) {
            editTextsArray!![i].isEnabled = i < 5
            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_not_focused)
            editTextsArray!![i].isCursorVisible = false
            editTextsArray!![i].setText("")
        }
        editTextsArray!![0].setBackgroundResource(R.drawable.edit_text_focused)
        currentEditTextIndex = 0
    }

    private fun setUpKeyboard() {
        val keyboardKeysArray = arrayOf(
            findViewById(R.id.keyQ),
            findViewById(R.id.keyW),
            findViewById(R.id.keyE),
            findViewById(R.id.keyR),
            findViewById(R.id.keyT),
            findViewById(R.id.keyY),
            findViewById(R.id.keyU),
            findViewById(R.id.keyI),
            findViewById(R.id.keyO),
            findViewById(R.id.keyP),
            findViewById(R.id.keyA),
            findViewById(R.id.keyS),
            findViewById(R.id.keyD),
            findViewById(R.id.keyF),
            findViewById(R.id.keyG),
            findViewById(R.id.keyH),
            findViewById(R.id.keyJ),
            findViewById(R.id.keyK),
            findViewById(R.id.keyL),
            findViewById(R.id.keyZ),
            findViewById(R.id.keyX),
            findViewById(R.id.keyC),
            findViewById(R.id.keyV),
            findViewById(R.id.keyB),
            findViewById(R.id.keyN),
            findViewById<Button>(R.id.keyM)
        )
        setTextOnKeyboardKeyClick(keyboardKeysArray[0], "Q")
        setTextOnKeyboardKeyClick(keyboardKeysArray[1], "W")
        setTextOnKeyboardKeyClick(keyboardKeysArray[2], "E")
        setTextOnKeyboardKeyClick(keyboardKeysArray[3], "R")
        setTextOnKeyboardKeyClick(keyboardKeysArray[4], "T")
        setTextOnKeyboardKeyClick(keyboardKeysArray[5], "Y")
        setTextOnKeyboardKeyClick(keyboardKeysArray[6], "U")
        setTextOnKeyboardKeyClick(keyboardKeysArray[7], "I")
        setTextOnKeyboardKeyClick(keyboardKeysArray[8], "O")
        setTextOnKeyboardKeyClick(keyboardKeysArray[9], "P")
        setTextOnKeyboardKeyClick(keyboardKeysArray[10], "A")
        setTextOnKeyboardKeyClick(keyboardKeysArray[11], "S")
        setTextOnKeyboardKeyClick(keyboardKeysArray[12], "D")
        setTextOnKeyboardKeyClick(keyboardKeysArray[13], "F")
        setTextOnKeyboardKeyClick(keyboardKeysArray[14], "G")
        setTextOnKeyboardKeyClick(keyboardKeysArray[15], "H")
        setTextOnKeyboardKeyClick(keyboardKeysArray[16], "J")
        setTextOnKeyboardKeyClick(keyboardKeysArray[17], "K")
        setTextOnKeyboardKeyClick(keyboardKeysArray[18], "L")
        setTextOnKeyboardKeyClick(keyboardKeysArray[19], "Z")
        setTextOnKeyboardKeyClick(keyboardKeysArray[20], "X")
        setTextOnKeyboardKeyClick(keyboardKeysArray[21], "C")
        setTextOnKeyboardKeyClick(keyboardKeysArray[22], "V")
        setTextOnKeyboardKeyClick(keyboardKeysArray[23], "B")
        setTextOnKeyboardKeyClick(keyboardKeysArray[24], "N")
        setTextOnKeyboardKeyClick(keyboardKeysArray[25], "M")
        findViewById<View>(R.id.keyRight).setOnClickListener { forwardEditTextFocus() }
        findViewById<View>(R.id.keyLeft).setOnClickListener { backwardEditTextFocus() }
        findViewById<View>(R.id.keyEnter).setOnClickListener { validateWord() }
        findViewById<View>(R.id.keyDelete).setOnClickListener {
            if (editTextsArray!![currentEditTextIndex].text.toString().isEmpty()) {
                backwardEditTextFocus()
            }
            editTextsArray!![currentEditTextIndex].setText("")
        }
    }

    private fun addAllEditTextsToArrayList() {
        editTextsArray!!.add(findViewById(R.id.rowOneEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowOneEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowOneEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowOneEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowOneEditTextFive))
        editTextsArray!!.add(findViewById(R.id.rowTwoEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowTwoEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowTwoEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowTwoEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowTwoEditTextFive))
        editTextsArray!!.add(findViewById(R.id.rowThreeEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowThreeEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowThreeEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowThreeEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowThreeEditTextFive))
        editTextsArray!!.add(findViewById(R.id.rowFourEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowFourEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowFourEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowFourEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowFourEditTextFive))
        editTextsArray!!.add(findViewById(R.id.rowFiveEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowFiveEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowFiveEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowFiveEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowFiveEditTextFive))
        editTextsArray!!.add(findViewById(R.id.rowSixEditTextOne))
        editTextsArray!!.add(findViewById(R.id.rowSixEditTextTwo))
        editTextsArray!!.add(findViewById(R.id.rowSixEditTextThree))
        editTextsArray!!.add(findViewById(R.id.rowSixEditTextFour))
        editTextsArray!!.add(findViewById(R.id.rowSixEditTextFive))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setClicksOnEditTexts() {
        for (i in 0..29) {
            editTextsArray!![i].setOnTouchListener { v, event ->
                editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_not_focused)
                currentEditTextIndex = i
                editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                true
            }
        }
    }

    private fun setTextOnKeyboardKeyClick(button: Button, str: String) {
        button.setOnClickListener {
            editTextsArray!![currentEditTextIndex].setText(str)
            forwardEditTextFocus()
        }
    }

    private fun forwardEditTextFocus() {
        if (currentEditTextIndex != 4 && currentEditTextIndex != 9 && currentEditTextIndex != 14 && currentEditTextIndex != 19 && currentEditTextIndex != 24 && currentEditTextIndex != 29) {
            if (editTextsArray!![currentEditTextIndex].text.toString().isEmpty()) {
                editTextsArray!![currentEditTextIndex].setText("-")
            }
            editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_not_focused)
            currentEditTextIndex++
            editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
        }
    }

    private fun backwardEditTextFocus() {
        if (currentEditTextIndex != 0 && currentEditTextIndex != 5 && currentEditTextIndex != 10 && currentEditTextIndex != 15 && currentEditTextIndex != 20 && currentEditTextIndex != 25) {
            editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_not_focused)
            currentEditTextIndex--
            editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
        }
    }

    private fun validateWord() {
        var word: String
        var numberOfExactLetters = 0
        val currentRow = currentEditTextIndex / 5 + 1
        when (currentRow) {
            1 -> {
                word = ""
                var i = 0
                while (i < 5) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 0
                    while (i < 5) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                    currentEditTextIndex++
                    editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                    disableEnableRows(1)
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            2 -> {
                word = ""
                var i = 5
                while (i < 10) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 5
                    while (i < 10) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i - 5] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        i++
                    }
                    currentEditTextIndex++
                    editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                    disableEnableRows(2)
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            3 -> {
                word = ""
                var i = 10
                while (i < 15) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 10
                    while (i < 15) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i - 10] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        i++
                    }
                    currentEditTextIndex++
                    editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                    disableEnableRows(3)
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            4 -> {
                word = ""
                var i = 15
                while (i < 20) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 15
                    while (i < 20) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i - 15] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        i++
                    }
                    currentEditTextIndex++
                    editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                    disableEnableRows(4)
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            5 -> {
                word = ""
                var i = 20
                while (i < 25) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 20
                    while (i < 25) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i - 20] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        i++
                    }
                    currentEditTextIndex++
                    editTextsArray!![currentEditTextIndex].setBackgroundResource(R.drawable.edit_text_focused)
                    disableEnableRows(5)
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            6 -> {
                word = ""
                var i = 25
                while (i < 30) {
                    word += editTextsArray!![i].text.toString()
                    i++
                }
                if (allFiveLetterWords.contains(word.lowercase(Locale.getDefault()))) {
                    var i = 25
                    while (i < 30) {
                        val chr = editTextsArray!![i].text.toString().lowercase(Locale.getDefault())
                            .trim { it <= ' ' }[0]
                        if (currentWord[i - 25] == chr) {
                            editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_green)
                            numberOfExactLetters++
                        } else {
                            var letterExists = false
                            var j = 0
                            while (j < 5) {
                                if (chr == currentWord[j]) {
                                    editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_orange)
                                    letterExists = true
                                    break
                                }
                                j++
                            }
                            if (!letterExists) {
                                editTextsArray!![i].setBackgroundResource(R.drawable.edit_text_grey)
                            }
                        }
                        i++
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Invalid Word", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            else -> {
                Toast.makeText(this@MainActivity, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                return
            }
        }
        if (currentRow < 6) {
            disableEnableRows(currentRow)
        }
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
            .setPositiveButton(
                HtmlCompat.fromHtml(
                    "<font color='#A8B1C8'>Yes</font>",
                    0
                )
            ) { dialog, id -> startGame() }
            .setNegativeButton(
                HtmlCompat.fromHtml(
                    "<font color='#A8B1C8'>No</font>",
                    0
                )
            ) { dialog, id -> finishAffinity() }
        if (numberOfExactLetters == 5) {
            builder.setMessage("You won").setTitle("Victory")
            builder.setMessage("Do you want to restart?")
            val alert = builder.create()
            alert.show()
        } else if (currentRow == 6) {
            builder.setMessage("You Lost").setTitle("Defeat!")
            builder.setMessage(
                """The word was: ${String(currentWord)}
 Do you want to restart?"""
            )
            val alert = builder.create()
            alert.show()
        }
    }

    private fun disableEnableRows(rowToDisable: Int) {
        when (rowToDisable) {
            1 -> {
                run {
                    var i = 0
                    while (i < 5) {
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                }
                var i = 5
                while (i < 10) {
                    editTextsArray!![i].isEnabled = true
                    i++
                }
            }

            2 -> {
                run {
                    var i = 5
                    while (i < 10) {
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                }
                var i = 10
                while (i < 15) {
                    editTextsArray!![i].isEnabled = true
                    i++
                }
            }

            3 -> {
                run {
                    var i = 10
                    while (i < 15) {
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                }
                var i = 15
                while (i < 20) {
                    editTextsArray!![i].isEnabled = true
                    i++
                }
            }

            4 -> {
                run {
                    var i = 15
                    while (i < 20) {
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                }
                var i = 20
                while (i < 25) {
                    editTextsArray!![i].isEnabled = true
                    i++
                }
            }

            5 -> {
                run {
                    var i = 20
                    while (i < 25) {
                        editTextsArray!![i].isEnabled = false
                        i++
                    }
                }
                var i = 25
                while (i < 30) {
                    editTextsArray!![i].isEnabled = true
                    i++
                }
            }

            else -> {}
        }
    }
}
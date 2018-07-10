package Core

import Core.Interface.Screen
import util.Constant
import util.Tool

/**
 * Class ScreenLCD is which represent the seven segment Screen
 */
class ScreenLCD(private var Screen: Array<Array<String>>? = null, var numberColumns: Int = 0, var numberRows: Int = 0, private var space: Int = 0) : Screen {

    private var values: ArrayList<Int> = arrayListOf(2)
    private var number: String = ""


    /**
     * Seven segments representation
     */
    companion object {
        val ONE: IntArray = intArrayOf(1, 1, 0, 0, 0, 0, 0)
        val TWO: IntArray = intArrayOf(1, 0, 1, 1, 0, 1, 1)
        val THREE: IntArray = intArrayOf(1, 1, 1, 0, 0, 1, 1)
        val FOUR: IntArray = intArrayOf(1, 1, 0, 0, 1, 0, 1)
        val FIVE: IntArray = intArrayOf(0, 1, 1, 0, 1, 1, 1)
        val SIX: IntArray = intArrayOf(0, 1, 1, 1, 1, 1, 1)
        val SEVEN: IntArray = intArrayOf(1, 1, 0, 0, 0, 1, 0)
        val EIGHT: IntArray = intArrayOf(1, 1, 1, 1, 1, 1, 1)
        val NINE: IntArray = intArrayOf(1, 1, 1, 0, 1, 1, 1)
        val ZERO: IntArray = intArrayOf(1, 1, 1, 1, 1, 1, 0)
        val VERTICAL_SEGMENTS: IntArray = intArrayOf(1, 2, 4, 5)
        val HORIZONTAL_SEGMENTS: IntArray = intArrayOf(3, 6, 7)
    }


    /**
     * Method that initialize vars of the Screen as total Dimension, numberColumns and numberRows
     * @param input String that contains size of screen and number to show
     * @param space space between numbers
     */
    override fun init(input: String, space: Int) {
        this.values = Tool.getSizeAndNumber(input)
        this.space = space
        if (Tool.validateRange(values[0], Constant.MIN_VALUE_SIZE, Constant.MAX_VALUE_SIZE, Constant.OUT_SIZE_RANGE_MESSAGE)) {
            this.numberRows = values[0].times(2).plus(3)
            this.numberColumns = values[0].plus(2)
            this.number = values[1].toString()
            this.Screen = Array(this.numberRows) { Array((this.numberColumns.times(number.length)).plus(space.times(number.length))) { " " } }
        }
    }

    /**
     * Method that print the number on screen
     */
    override fun print() {
        if (this.Screen != null)
            for (i in 0 until this.Screen!!.size) {
                for (j in 0 until Screen!![0].size) {
                    print(Screen!![i][j])
                }
                println("")
            }
    }

    /**
     * Returns the respective number as seven segments form
     * @param number number to return
     * @return IntArray
     */
    private fun getNumberSegments(number: Char) =
            when (number) {
                '1' -> ONE
                '2' -> TWO
                '3' -> THREE
                '4' -> FOUR
                '5' -> FIVE
                '6' -> SIX
                '7' -> SEVEN
                '8' -> EIGHT
                '9' -> NINE
                '0' -> ZERO
                else -> {
                    intArrayOf(0, 0, 0, 0, 0, 0, 0)
                }
            }

    /**
     * Method that fills the screen with all chars for the segments
     */
    override fun fillScreen() {
        for (index in 0 until number.length) {
            val digit: Char = number[index]
            this.addHorizontalSegments(getNumberSegments(digit), (numberColumns.plus(space)).times(index), (numberColumns).times(index.plus(1)).minus(1).plus(space * index))
            this.addVerticalSegments(getNumberSegments(digit), (numberColumns.plus(space)).times(index), (numberColumns).times(index.plus(1)).minus(1).plus(space * index))
        }
    }


    /**
     * Method that add the vertical segments into screen represented as a matrix
     * @param number number represented as a seven segments
     * @param initPosX initial position where the number begins
     * @param endPosX position where the number ends
     */
    private fun addVerticalSegments(number: IntArray, initPosX: Int, endPosX: Int) {
        for (segment in VERTICAL_SEGMENTS) {
            when (number[segment.minus(1)] == 1) {
                true -> fillVertical(segment, initPosX, endPosX)
            }
        }
    }

    /**
     * Method that add the horizontal segments into screen represented as a matrix
     * @param number number represented as a seven segments
     * @param initPosX initial position where the number begins
     * @param endPosX position where the number ends
     */
    private fun addHorizontalSegments(number: IntArray, initPosX: Int, endPosX: Int) {
        for (segment in HORIZONTAL_SEGMENTS) {
            when (number[segment.minus(1)] == 1) {
                true -> fillHorizontal(segment, initPosX, endPosX)
            }
        }
    }

    /**
     * Method that fill a horizontal segment with the respective representative character
     * @param segment segment to fill into the screen( matrix)
     * @param initPosX initial position where the segment begins
     * @param endPosX position where the segment ends
     */
    private fun fillHorizontal(segment: Int, initPosX: Int, endPosX: Int) {
        for (i in initPosX until endPosX) {
            if (i != initPosX && i != endPosX)
                when (segment) {
                    HORIZONTAL_SEGMENTS[0] -> {
                        Screen?.get(numberRows.minus(1))?.set(i, Constant.HORIZONTAL_CHAR.toString())
                    }
                    HORIZONTAL_SEGMENTS[1] -> {
                        Screen?.get(0)?.set(i, Constant.HORIZONTAL_CHAR.toString())
                    }
                    HORIZONTAL_SEGMENTS[2] -> {
                        Screen?.get(numberRows.div(2))?.set(i, Constant.HORIZONTAL_CHAR.toString())
                    }
                }
        }
    }

    /**
     * Method that fill a vertical segment with the respective representative character
     * @param segment segment to fill into the screen( matrix)
     * @param initPosX initial position where the segment begins
     * @param endPosX position where the segment ends
     */
    private fun fillVertical(segment: Int, initPosX: Int, endPosX: Int) {
        for (i in 0 until numberRows.div(2)) {
            if (i.rem(numberRows.div(2)) != 0)
                when (segment) {
                    VERTICAL_SEGMENTS[0] -> {
                        Screen?.get(i)?.set(endPosX, Constant.VERTICAL_CHAR.toString())
                    }
                    VERTICAL_SEGMENTS[3] -> {
                        Screen?.get(i)?.set(initPosX, Constant.VERTICAL_CHAR.toString())
                    }
                }
        }
        for (i in numberRows.div(2) until numberRows) {
            if (i.rem(numberRows.div(2)) != 0)
                when (segment) {
                    VERTICAL_SEGMENTS[1] -> {
                        Screen?.get(i)?.set(endPosX, Constant.VERTICAL_CHAR.toString())
                    }
                    VERTICAL_SEGMENTS[2] -> {
                        Screen?.get(i)?.set(initPosX, Constant.VERTICAL_CHAR.toString())
                    }
                }
        }
    }
}
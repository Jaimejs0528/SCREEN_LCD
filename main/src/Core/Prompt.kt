package Core

import Core.Factory.ScreenFactory
import Core.Interface.Screen
import util.Constant
import util.Tool

/**
 * Class that allows to the user interact with the application
 */
class Prompt private constructor() {
    private var screens: ArrayList<Screen> = ArrayList()
    var space: String? = ""
    private val END_STRING = "0,0"

    companion object {
        @JvmStatic
        private val PROMPT: Prompt = Prompt()

        /**
         * Singleton
         * Prompt instance
         */
        @JvmStatic
        val instance: Prompt by lazy { PROMPT }
    }


    /**
     * Method that show the outputs to the user
     */
    fun showPrompt() {
        if (this.space == "") {
            print(Constant.SPACE_INPUT_MESSAGE)
            this.space = readLine()
            Tool.validateNumber(this.space ?: "")
            Tool.validateRange(this.space?.toIntOrNull()
                    ?: -1, Constant.MIN_VALUE_SPACE, Constant.MAX_VALUE_SPACE, Constant.OUT_SPACE_RANGE_MESSAGE)
        }
        try {
            getScreens()
        } catch (ex: Exception) {
            println("${Constant.ERROR} ${ex.message}")
            showPrompt()
        }

    }

    /**
     * Method that get the inputs from user
     */
    private fun getScreens() {
        print(Constant.SCREEN_INPUT_MESSAGE)
        val input = readLine().toString()
        if (input != END_STRING) {
            val screenTemp: Screen = ScreenFactory.instance.selectScreen(Constant.LCD)
                    ?: throw NullPointerException(Constant.NOT_SCREEN_FOUND_MESSAGE)
            screenTemp.init(input, this.space?.toInt() ?: 0)
            screenTemp.fillScreen()
            this.screens.add(screenTemp)
            getScreens()
        } else {
            printScreens()
        }
    }

    /**
     * Method to print the screens into console
     */
    private fun printScreens() {
        for (screen in screens) {
            screen.print()
        }
    }
}
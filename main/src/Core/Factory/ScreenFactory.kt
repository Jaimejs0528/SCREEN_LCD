package Core.Factory

import Core.Interface.Screen
import Core.ScreenLCD
import util.Constant


/**
 * Class Factory Method pattern
 */
class ScreenFactory private constructor() {

    companion object {
        @JvmStatic
        private val factory: ScreenFactory = ScreenFactory()

        /**
         * Singleton
         * ScreenFactory instance
         */
        @JvmStatic
        val instance by lazy { factory }
    }

    /**
     * Method that select a Screen
     * @param screen name of the screen to select
     * @return Screen instance
     */
    fun selectScreen(screen: String): Screen? =
            when (screen) {
                Constant.LCD -> ScreenLCD()
                else -> null
            }
}
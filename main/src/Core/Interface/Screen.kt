package Core.Interface

interface Screen {

    /**
     * Method that initialize vars of the Screen
     * @param input String that contains size of screen and number to show
     * @param space space between numbers
     */
    fun init(input: String, space: Int = 0)

    /**
     * Method that print the number on screen
     */
    fun print()

    /**
     * Method that fills the screen with all chars for the segments
     */
    fun fillScreen()
}
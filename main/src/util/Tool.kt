package util

class Tool {

    companion object {

        /**
         * get size and number to print on Screen LCD, from a String
         * @param input String with values size and number mixed
         * @return ArrayList<Int>
         */
        @JvmStatic
        fun getSizeAndNumber(input: String): ArrayList<Int> {
            val sizeNumber: ArrayList<Int> = arrayListOf()
            when {
                (input.length < 3) -> {
                    throw IllegalArgumentException("${Constant.TOO_SHORT} $input")
                }
                input.contains(Constant.SEPARATOR_SIGN) -> {
                    val result = input.split(Constant.SEPARATOR_SIGN)
                    sizeNumber.add(Constant.POSITION_SIZE, validateNumber(result[Constant.POSITION_SIZE]))
                    sizeNumber.add(Constant.POSITION_VALUE, validateNumber(result[Constant.POSITION_VALUE]))
                    return sizeNumber
                }
                else -> {
                    throw IllegalArgumentException("${Constant.NOT_SEPARATOR_SIGN_MESSAGE} ${Constant.SEPARATOR_SIGN}")
                }
            }
        }

        /**
         * Method that returns a Int if its input parameter contains only digits
         * @param value input value as a String
         * @return Int
         */
        @JvmStatic
        fun validateNumber(value: String) = value.toIntOrNull()
                ?: throw NumberFormatException("${Constant.NOT_IS_A_NUMBER_MESSAGE} $value")

        /**
         * Validate if a number is between a specific range
         * @param value input value
         * @param min minimum value that the input can reach
         * @param max maximum value that the input can reach
         * @param errorMessage error message to show
         * @return Boolean
         */
        @JvmStatic
        fun validateRange(value: Int, min: Int, max: Int, errorMessage: String) =
                if (value in min..max) {
                    true
                } else {
                    throw IllegalArgumentException(errorMessage)
                }
    }
}



package util

class Constant {
    companion object {

        //Values utils
        val SEPARATOR_SIGN: Char = ','
        val HORIZONTAL_CHAR: Char = '-'
        val VERTICAL_CHAR: Char = '|'
        val MIN_VALUE_SPACE: Int = 0
        val MAX_VALUE_SPACE: Int = 5
        val MIN_VALUE_SIZE: Int = 1
        val MAX_VALUE_SIZE: Int = 10
        val POSITION_SIZE: Int = 0
        val POSITION_VALUE: Int = 1
        val LCD: String = "LCD"

        //Messages
        val NOT_SEPARATOR_SIGN_MESSAGE: String = "La entrada no posee el caracter separador:"
        val NOT_IS_A_NUMBER_MESSAGE: String = "Numero ingresado invalido:"
        val OUT_SIZE_RANGE_MESSAGE: String = "El tamaño debe estar entre $MIN_VALUE_SIZE y $MAX_VALUE_SIZE:"
        val OUT_SPACE_RANGE_MESSAGE: String = "El valor debe estar entre $MIN_VALUE_SPACE y $MAX_VALUE_SPACE:"
        val TOO_SHORT: String = "La entrada ingresada es demasiado corta:"
        val SPACE_INPUT_MESSAGE: String = "Ingresa el espacio entre digitos (0-5):"
        val SCREEN_INPUT_MESSAGE: String = "Entrada: "
        val NOT_SCREEN_FOUND_MESSAGE: String = "La pantalla no existe"
        val ERROR: String = "Error:"
    }
}
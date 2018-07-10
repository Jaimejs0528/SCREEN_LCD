import Core.Prompt


/**
 * Application main function
 */
fun main(args: Array<String>) {
    try {
        Prompt.instance.showPrompt()
    }catch (ex:Exception){
        println("${ex.message}")
    }

}


import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)

    val url = scanner.nextLine()
    //https://target.com/index.html?pass=12345&port=8080&cookie=&host=localhost
    val parametersAsSingleString = url.split('?')[1]

    val paramMap = parametersAsSingleString
        .split('&')
        .associate { keyValueString ->
        val splitPair: List<String> = keyValueString.split('=')
        splitPair[0] to splitPair[1]
    }

    paramMap.forEach { param ->
        if (param.value != "") {
            println("${param.key} : ${param.value}")
        } else {
            println("${param.key} : not found")
        }
    }

    if (paramMap["pass"] != null) {
        println("password : ${paramMap["pass"]}")
    }
}
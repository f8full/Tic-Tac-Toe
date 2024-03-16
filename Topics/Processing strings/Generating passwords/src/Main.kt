import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val uppercaseMinCount = scanner.nextInt()
    val lowercaseMinCount = scanner.nextInt()
    val digitsMinCount = scanner.nextInt()
    val desiredLength = scanner.nextInt()

    var password = ""

    for (i in 0 until lowercaseMinCount) {
        password = addNewChar(password, 'a'..'z')
    }

    for (i in 0 until uppercaseMinCount) {
        password = addNewChar(password, 'A'..'Z')
    }

    for (i in 0 until digitsMinCount) {
        password = addNewChar(password, 0..9)
    }

    val missingLength = desiredLength - password.length

    for (i in 0 until missingLength) {
        password = addNewChar(password, 'A'..'Z')
    }

    print(password)
}

private fun addNewChar(partialPassword: String, range: Any) : String =
    when(range) {
        is IntRange -> {
            var newChar = range.random()
            while (partialPassword.isNotEmpty() && newChar.toString() == partialPassword.last().toString()) {
                newChar = range.random()
            }

            partialPassword + newChar
        }
        is CharRange -> {
            var newChar = range.random()
            while (partialPassword.isNotEmpty() && newChar == partialPassword.last()) {
                newChar = range.random()
            }

            partialPassword + newChar

        }
        else -> partialPassword
    }

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val firstLine = scanner.nextLine()
    val secondLine = scanner.nextLine()

    print(firstLine.lowercase() == secondLine.lowercase())

    val example = "Example"
    example.substring(3)     // 1
    example.substring(0, 7)  // 2
//    example.substring(-1, 0) // 3
    example.substring(0, 0)  // 4
 //   example.substring(0, 8)
}
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val input = scanner.nextInt()

    val fibonacci = listOf(0, 1, 2, 3, 5, 8, 13, 21, 34, 55)
    val triangle = listOf(0, 1, 3, 6, 10, 15, 21, 28, 36, 45)
    val powerTen = listOf(1, 10, 100, 1000, 10000, 100000)

    when {
        fibonacci.find { it == input } != null -> print("F")
        triangle.find { it == input } != null -> print("T")
        powerTen.find { it == input } != null -> print("P")
        else -> print("N")
    }
}
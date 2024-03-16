import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val n = scanner.nextInt()

    var count = 0
    for(cur in a..b) {
        if (cur % n == 0) {
            count++
        }
    }
    print(count)
}
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()

    val numbers: MutableList<Int> = mutableListOf()

    for (i in 0 until n) {
        numbers.add(scanner.nextInt())
    }

    val m = scanner.nextInt()

    val mList = numbers.filter { it == m }

    print(mList.size)


}
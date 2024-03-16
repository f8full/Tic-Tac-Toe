import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val numbers: MutableList<Int> = mutableListOf()

    for (i in 1..scanner.nextInt()) {
        numbers.add(scanner.nextInt())
    }


    val p = scanner.nextInt()
    val m = scanner.nextInt()



    if (numbers.contains(p) && numbers.contains(m) ) {
        print("YES")
    } else {
        print("NO")
    }
}
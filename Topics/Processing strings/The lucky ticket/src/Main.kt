import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val ticketNumber = scanner.nextLine()

    //val twoHalves = ticketNumber
    val twoHalves = ticketNumber
        .map { it.toString().toInt() }
        .chunked(size = 3)


    if (twoHalves[0].sum() == twoHalves[1].sum()) {
        print("Lucky")
    } else {
        print("Regular")
    }
}
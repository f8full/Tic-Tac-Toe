import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val x = scanner.nextInt()

    val newList: MutableList<Int> = mutableListOf()

    for(intValue in x downTo 1) {
        newList.add(intValue)
    }

    newList.forEach {cur ->
        print("$cur ")

    }


}
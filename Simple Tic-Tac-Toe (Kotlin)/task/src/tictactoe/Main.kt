package tictactoe

import java.util.*
import kotlin.math.abs

typealias IsFinished = Boolean

fun main() {
    
    val scanner = Scanner(System.`in`)

    //val gridInput = scanner.nextLine()
    
    val gridInput = "_________"

    var grid2dCharList = mutableListOf<List<String>>()
    gridInput.chunked(3).forEach { row ->
        grid2dCharList.add(row.chunked(size = 1))
    }

    printGrid(grid2dCharList)

    var playerTurn = Player.X

    do {
        grid2dCharList = playTurn(
            gameGrid = grid2dCharList,
            player = playerTurn,
            scanner = scanner,
        ).toMutableList()

        printGrid(grid2dCharList)

        playerTurn = if (playerTurn == Player.X) {
            Player.O
        } else {
            Player.X
        }

    } while (!analyzeGrid(grid2dCharList))
}

private fun playTurn(gameGrid: List<List<String>>, player: Player, scanner: Scanner): List<List<String>> {
    var coordinateInput = scanner.nextLine()

    var analysisResult: CoordinateUserInputAnalysisResult

    do {
        analysisResult = analyzeCoordinateInput(scanner = scanner, input = coordinateInput )

        when(analysisResult) {
            is NonNumerical -> {
                println("You should enter numbers!")
                coordinateInput = scanner.nextLine()
            }
            is OutsideGrid -> {
                println("Coordinates should be from 1 to 3!")
                coordinateInput = scanner.nextLine()

            }
            is ValidCell -> {
                if (gameGrid[analysisResult.cell.indexPair.first][analysisResult.cell.indexPair.second] != "_") {
                    println("This cell is occupied! Choose another one!")
                    coordinateInput = scanner.nextLine()
                }
                else {
                    break
                }
            }
        }
    }
    while ( true )

    analysisResult as ValidCell

    return gameGrid.mapIndexed { rowIndex, row ->
        if (rowIndex == analysisResult.cell.indexPair.first) {
            row.mapIndexed { columnIndex, cellContent ->
                if (columnIndex == analysisResult.cell.indexPair.second) {
                    when(player) {
                        Player.X -> "X"
                        else -> "O"
                    }
                } else {
                    cellContent
                }
            }
        } else {
            row
        }
    }.toMutableList()
    
}

private fun analyzeGrid(gameGrid: List<List<String>>): IsFinished {

    val isDraw: Boolean
    val isUnfinished: Boolean
    var isImpossible: Boolean

    // check count difference
    val xCount = gameGrid.joinToString().count { it == 'X' }
    val oCount = gameGrid.joinToString().count { it == 'O' }
    isImpossible = abs(xCount - oCount) > 1

    val isOVictory = checkAllWin(
        grid = gameGrid,
        player = Player.O
    )
    val isXVictory = checkAllWin(
        grid = gameGrid,
        player = Player.X
    )

    isImpossible = isImpossible || (isOVictory && isXVictory)

    val isGridFull = gameGrid.joinToString().count { it == '_' } == 0

    isDraw = !isXVictory && !isOVictory && isGridFull

    isUnfinished = !isImpossible && !isDraw && !isXVictory && !isOVictory

    if (!isUnfinished) {
        printResult(isImpossible, isUnfinished, isDraw, isXVictory)
    }

    return !isUnfinished
}

private fun analyzeCoordinateInput(scanner: Scanner, input: String): CoordinateUserInputAnalysisResult  =
    try {
        val rowIndex = input.split(" ")[0].toInt() - 1
        val columnIndex = input.split(" ")[1].toInt() - 1

        if (rowIndex !in 0..2 || columnIndex !in 0..2) {
            OutsideGrid()
        } else {

            ValidCell(
                cell = GridCell.values().first { it.indexPair == Pair(rowIndex, columnIndex) }
            )
        }
    } catch (exception: Exception) {
        NonNumerical()
    }

private fun printResult(
    isImpossible: Boolean,
    isUnfinished: Boolean,
    isDraw: Boolean,
    isXVictory: Boolean
) {
    when {
        isImpossible -> print("Impossible")
        isUnfinished -> print("Game not finished")
        isDraw -> print("Draw")
        isXVictory -> print("X wins")
        else -> print("O wins")
    }
}

private fun printGrid(grid2dList: MutableList<List<String>>) {
    val printableGrid = mutableListOf(
        "---------"
    )

    grid2dList.chunked(3) { rowList ->
        rowList.forEach { row ->
            printableGrid.add(
                element = "| ${row.joinToString(" ").replace("_", " ")} |"
            )

        }

    }

    printableGrid.add("---------")

    printableGrid.forEach { row ->
        println(row)
    }
}

private fun checkAllWin(grid: List<List<String>>, player: Player): Boolean {
    var isWinner = false
    for (check in 0 until WinChecks.values().size) {
        isWinner = checkSingleWin(
            grid = grid,
            player = player,
            check = WinChecks.values()[check],
            )
        if (isWinner) {
            break
        }
    }
    return isWinner
}

private fun checkSingleWin(grid: List<List<String>>, player: Player, check: WinChecks): Boolean {
    var isWinner = true
    for (i in 0..2) {
        isWinner = grid[check.indexes[i].first][check.indexes[i].second] == player.name
        if (!isWinner) {
            break
        }
    }
    return isWinner
}

sealed class CoordinateUserInputAnalysisResult
private class NonNumerical(val printMessage: String = "This cell is occupied! Choose another one!"): CoordinateUserInputAnalysisResult()
private class OutsideGrid(val printMessage: String = "Coordinates should be from 1 to 3!"): CoordinateUserInputAnalysisResult()
private class ValidCell(val cell: GridCell): CoordinateUserInputAnalysisResult()

private enum class Player {
    X,
    O,
}
private enum class WinChecks(val indexes: List<Pair<Int, Int>>) {
    FIRST_ROW(listOf(GridCell.TOP_LEFT.indexPair, GridCell.TOP_MIDDLE.indexPair, GridCell.TOP_RIGHT.indexPair)),
    SECOND_ROW(listOf(GridCell.MIDDLE_LEFT.indexPair, GridCell.MIDDLE_MIDDLE.indexPair, GridCell.MIDDLE_RIGHT.indexPair)),
    THIRD_ROW(listOf(GridCell.BOTTOM_LEFT.indexPair, GridCell.BOTTOM_MIDDLE.indexPair, GridCell.BOTTOM_RIGHT.indexPair)),
    FIRST_COLUMN(listOf(GridCell.TOP_LEFT.indexPair, GridCell.MIDDLE_LEFT.indexPair, GridCell.BOTTOM_LEFT.indexPair)),
    SECOND_COLUMN(listOf(GridCell.TOP_MIDDLE.indexPair, GridCell.MIDDLE_MIDDLE.indexPair, GridCell.BOTTOM_MIDDLE.indexPair)),
    THIRD_COLUMN(listOf(GridCell.TOP_RIGHT.indexPair, GridCell.MIDDLE_RIGHT.indexPair, GridCell.BOTTOM_RIGHT.indexPair)),
    FIRST_DIAGONAL(listOf(GridCell.TOP_LEFT.indexPair, GridCell.MIDDLE_MIDDLE.indexPair, GridCell.BOTTOM_RIGHT.indexPair)),
    SECOND_DIAGONAL(listOf(GridCell.BOTTOM_LEFT.indexPair, GridCell.MIDDLE_MIDDLE.indexPair, GridCell.TOP_RIGHT.indexPair)),
}

// there should be a pair and the occupied status. That should be the grid.
private enum class GridCell(val indexPair: Pair<Int,Int>) {
    TOP_LEFT(Pair(0,0)),
    TOP_MIDDLE(Pair(0,1)),
    TOP_RIGHT(Pair(0,2)),
    MIDDLE_LEFT(Pair(1,0)),
    MIDDLE_MIDDLE(Pair(1,1)),
    MIDDLE_RIGHT(Pair(1,2)),
    BOTTOM_LEFT(Pair(2,0)),
    BOTTOM_MIDDLE(Pair(2,1)),
    BOTTOM_RIGHT(Pair(2,2)),
}
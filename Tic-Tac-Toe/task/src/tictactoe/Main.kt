package tictactoe

import java.util.*

val scanner = Scanner(System.`in`)
val aBattlefield = Array(3) { CharArray(3) }
const val player1 = 'X'
const val player2 = 'O'
const val space = ' '
var turn = 0


fun main() {
    // write your code here

    gameInit()
}

fun gameInit() {
    for (i in 0..2) {
        for (j in 0..2) {
            aBattlefield[i][j] = space
        }
    }
    draw()
    turner()
}

fun turner() {
    turn++
    if (turn % 2 == 1) {
        move(player1)
    } else {
        move(player2)
    }


}

fun move(c: Char) {
    println("Enter the coordinates: ")
    val nPosX = inputDigitValidator() - 49
    val nPosY = inputDigitValidator() - 49
    if (aBattlefield[nPosX][nPosY] != space) {
        println("This cell is occupied! Choose another one!")
        move(c)
    } else {
        aBattlefield[nPosX][nPosY] = c
        draw()
        decide(analyze(aBattlefield.size))

    }
}

fun draw() {
    println("---------")
    for (i in aBattlefield.indices) {
        print("| ")
        for (j in aBattlefield[i].indices) {
            print("${aBattlefield[i][j]} ")
        }
        print("|\n")
    }
    println("---------")
}

fun decide(state: Int) {
    when (state) {
        0 -> {
            turner()
        }
        1 -> println("Draw")
        2 -> println("Impossible")
        3 -> println("X wins")
        4 -> println("O wins")


    }

}

fun inputDigitValidator(): Int {
    return when (val input = scanner.next().single().toInt()) {
        !in 48..57 -> {
            println("You should enter numbers!")
            scanner.nextLine()
            inputDigitValidator()
        }
        !in 49..51 -> {
            println("Coordinates should be from 1 to 3!")
            scanner.nextLine()
            inputDigitValidator()
        }
        else -> {
            input
        }
    }
}

fun analyze(size: Int): Int {
    if (impossible(size)) {
        return 2
    }
    if (isWin(player1, size)) {
        return 3
    }
    if (isWin(player2, size)) {
        return 4
    }
    if (!isThereEmptyCell()) {
        return 1
    }
    return 0
}

fun isThereEmptyCell(): Boolean {
    for (i in aBattlefield.indices) {
        for (j in aBattlefield[i].indices) {
            if (aBattlefield[i][j] == space) {
                return true
            }
        }
    }
    return false
}

fun isWin(c: Char, size: Int): Boolean {
    val aDummy = CharArray(size)
    val aColumn = CharArray(size)
    val aLTRB = CharArray(size)
    val aRTLB = CharArray(size)
    //dummy init
    for (i in 0 until size) {
        aDummy[i] = c
    }
    for (i in 0 until size) {
        //RowCheck
        if (aBattlefield[i].contentEquals(aDummy)) {
            return true
        }
        //Column & Diagonal check
        for (j in 0 until size) {
            //Column feed
            aColumn[j] = aBattlefield[j][i]
            //Left-Right diagonal feed
            if (i == j) {
                aLTRB[i] = aBattlefield[i][j]
            }
            //Right-Left Diagonal feed
            if (i + j == size - 1) {
                aRTLB[i] = aBattlefield[i][j]
            }
        }
        //ColumnWin
        if (aColumn.contentEquals(aDummy)) {
            return true
        }
        //DiagonalWin
        if (aLTRB.contentEquals(aDummy) || aRTLB.contentEquals(aDummy)) {
            return true
        }
    }
    return false
}

fun impossible(size: Int): Boolean {
    var nX = 0
    var nY = 0
    for (row in aBattlefield) {
        for (cell in row) {
            if (cell == player1) {
                nX++
            }
            if (cell == player2) {
                nY++
            }
        }
    }
    //input error
    if (nX - nY !in -1..1) {
        return true
    }
    //both win
    if (isWin(player1, size) && isWin(player2, size)) {
        return true
    }
    return false
}
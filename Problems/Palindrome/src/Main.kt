fun main() {
    // write your code here
    val sInput = readLine()!!
    var sReversed = ""
    for (i in sInput.length - 1 downTo 0) {
        sReversed += sInput[i].toString()
    }
    println(if (sReversed == sInput) "yes" else "no")
}
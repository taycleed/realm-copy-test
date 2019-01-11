package taycleed.realmCopyTest

import java.util.*

private var start: Long = 0
fun check() {
    start = Calendar.getInstance().timeInMillis
}
fun end(): Long {
    val end = Calendar.getInstance().timeInMillis
    return end - start
}

fun getCharAtoZ(i: Int): String {
    return (i % ('z' - 'a') + 'a'.toInt()).toChar() + ""
}
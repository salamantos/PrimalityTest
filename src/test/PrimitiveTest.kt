package test

import java.lang.Math.sqrt

fun primitiveTest(n:Long):Boolean {
    var flag = false
    val upEdge = sqrt(n.toDouble()).toLong()
    for (i in 2..upEdge) {
        // condition for nonprime number
        if (n % i == 0L) {
            flag = true
            break
        }
    }

    return !flag
}
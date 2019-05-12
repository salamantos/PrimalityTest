package millerRabinTest

import java.math.BigInteger
import kotlin.random.Random.Default.nextLong

/**
 * Returns (x^y) % p
 *
 * @param xParam number x
 * @param yParam number y
 * @param p module
 * @return (x^y) % p
 */
private fun powerByModule(xParam: Long, yParam: Long, p: Long): BigInteger {
    var x = BigInteger.valueOf(xParam)
    var y = BigInteger.valueOf(yParam)
    var result = BigInteger.ONE

    // by module of p
    x %= BigInteger.valueOf(p)

    while (y > BigInteger.ZERO) {
        // y is odd
        if (y % BigInteger.TWO == BigInteger.ONE)
            result = (result * x) % BigInteger.valueOf(p)

        // y is even
        y = y shr 1
        x = (x * x) % BigInteger.valueOf(p)
    }
    return result
}

/**
 * One iteration of the Miller-Rabin algorithm
 *
 * @param n number to check
 * @param d number from equation n-1 = 2^s * d
 * @return only for one step: true if n is prime, false otherwise
 */
private fun millerRabinOneRepeat(n: Long, d: Long): Boolean {
    val a = nextLong(2, n - 2)
    var x = powerByModule(a, d, n)  // a^d % n
    if (x == BigInteger.ONE || x == BigInteger.valueOf(n - 1))
        return true

    var bigD = BigInteger.valueOf(d)
    while (bigD != BigInteger.valueOf(n - 1)) {
        x = (x * x) % BigInteger.valueOf(n)
        bigD *= BigInteger.TWO
        if (x == BigInteger.ONE)
            return false
        if (x == BigInteger.valueOf(n - 1))
            return true
    }
    return false
}

/**
 * Miller-Rabin algorithm
 *
 * @param n number to check
 * @param repeat iterations count of the algorithm
 * @return true if n is prime, false otherwise
 */
private fun millerRabin(n: Long, repeat: Int): Boolean {
    // Corner cases
    if (n <= 1 || n == 4L)
        return false
    if (n <= 3)
        return true

    // n - 1 = 2^s * d
    var d = n - 1
    while (d % 2 == 0L)
        d /= 2

    for (i in 0 until repeat)
        if (!millerRabinOneRepeat(n, d))
            return false

    return true
}

/**
 * Checks if the number is prime, public method
 *
 * @param n number to check
 * @param repeat iterations count of the algorithm
 * @return true if n is prime, false otherwise
 */
fun checkPrimality(n: Long, repeat: Int): Boolean {
    return millerRabin(n, repeat)
}

/**
 * Returns precision of the answer
 *
 * 4^(-repeat) chance of error for prime numbers
 * 0 chance of error for non-prime numbers
 *
 * @param repeat iterations count of the algorithm
 * @param isPrime answer of the algorithm
 * @return true if n is prime, false otherwise
 */
fun getPrecision(repeat: Int, isPrime: Boolean): Double {
    return if (isPrime) 1 - Math.pow(4.0, (-repeat).toDouble()) else 1.0
}

/**
 * Prints out a result of the algorithm
 *
 * @param n number to check
 * @param repeat iterations count of the algorithm
 */
fun printPrimality(n: Long, repeat: Int) {
    val isPrime = checkPrimality(n, repeat)
    println(
        "Number $n is ${if (isPrime) "prime" else "composite"} with precision of ${getPrecision(
            repeat,
            isPrime
        ) * 100.0}%"
    )
}

/**
 * Generates random prime number
 *
 * @param repeat iterations count of the algorithm
 * @return prime number
 */
fun generatePrime(repeat: Int): Long {
    var candidate = 4L
    while (!checkPrimality(candidate, repeat))
        candidate = nextLong(5, 4180175515311868073)
    return candidate
}
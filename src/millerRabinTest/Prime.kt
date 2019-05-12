package millerRabinTest

fun main () {
    printPrimality(43411L, 1)  // 4^(-k) precision
    printPrimality(4180175515311868073L, 10)  // 4^(-k) precision
    printPrimality(4180175515311868075L, 10)  // 100% precision when found composite number

    println("Generated prime number: ${generatePrime(50)}")
}
package test

import millerRabinTest.checkPrimality
import org.junit.Assert
import org.junit.Test

class Benchmark {
////////////////////// Miller-Rabin test //////////////////////
    @Test
    fun testSmallNumber() {
        Assert.assertTrue(checkPrimality(5L, 50))
    }

    @Test
    fun test16BitNumber() {
        Assert.assertTrue(checkPrimality(55411L, 50))
    }

    @Test
    fun test32BitNumber() {
        Assert.assertTrue(checkPrimality(4252374799L, 50))
    }

    @Test
    fun test64BitNumber() {
        Assert.assertTrue(checkPrimality(4180175515311868073L, 50))
    }

    @Test
    fun test64BitNumberForLoop() {
        for (i in 0 until 5) {
            Assert.assertTrue(checkPrimality(4180175515311868073L, 10))
        }
    }

////////////////////// Primitive test //////////////////////

    @Test
    fun testSmallNumberWithPrimitiveTest() {
        Assert.assertTrue(primitiveTest(5L))
    }

    @Test
    fun test16BitNumberWithPrimitiveTest() {
        Assert.assertTrue(primitiveTest(55411L))
    }

    @Test
    fun test32BitNumberWithPrimitiveTest() {
        Assert.assertTrue(primitiveTest(4252374799L))
    }

    @Test
    fun test64BitNumberWithPrimitiveTest() {
        Assert.assertTrue(primitiveTest(4180175515311868073L))
    }
}
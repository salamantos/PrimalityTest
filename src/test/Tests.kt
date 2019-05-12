package test

import millerRabinTest.checkPrimality
import org.junit.Assert
import org.junit.Test

class PrimalityTest {
    @Test
    fun testSmallNumbers() {
        Assert.assertFalse(checkPrimality(1L, 50))
        Assert.assertTrue(checkPrimality(2L, 50))
        Assert.assertTrue(checkPrimality(3L, 50))
        Assert.assertFalse(checkPrimality(4L, 50))
        Assert.assertTrue(checkPrimality(5L, 50))
    }

    @Test
    fun testEvenNumber() {
        Assert.assertFalse(checkPrimality(4180175515311868074L, 50))
    }

    @Test
    fun test64BitNumbers() {
        Assert.assertTrue(checkPrimality(4180175515311868073L, 50))
        Assert.assertFalse(checkPrimality(4180175515311868075L, 50))
        Assert.assertTrue(checkPrimality(3031608334839939637L, 50))
        Assert.assertTrue(checkPrimality(3951633230621453147L, 50))
        Assert.assertTrue(checkPrimality(8609276660307025841L, 50))
    }
}
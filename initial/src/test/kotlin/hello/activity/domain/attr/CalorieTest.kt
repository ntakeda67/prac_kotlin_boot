package hello.activity.domain.attr

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by nt67 on 7/20/17.
 */
class CalorieTest {

    @Test
    fun extractCalorieValue() {
        val calValue = 100
        val cal = Calorie(calValue, CalorieUnit.CAL)

        assertEquals(calValue, cal.get())
    }

    @Test
    fun extractKCalorieValue() {
        val calValue = 1000
        val cal = Calorie(calValue, CalorieUnit.CAL)

        assertEquals(1, cal.get(CalorieUnit.KCAL))
    }

    @Test
    fun extractKCalorie_omitPointNumber() {
        val calValue = 1999
        val cal = Calorie(calValue, CalorieUnit.CAL)

        assertEquals(1, cal.get(CalorieUnit.KCAL))
    }

    @Test
    fun toString_prettyPrint_with_Unit() {
        val cal = Calorie(1234000, CalorieUnit.CAL)
        assertEquals("1234000 [cal]", cal.toString())

        val kcal = Calorie(1234, CalorieUnit.KCAL)
        assertEquals("1234 [kcal]", kcal.toString())
    }

}
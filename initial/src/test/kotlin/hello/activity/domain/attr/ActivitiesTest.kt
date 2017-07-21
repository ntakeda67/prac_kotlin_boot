package hello.activity.domain.attr

import common.date.LocalDateTimeRange
import hello.activity.domain.Activities
import hello.activity.domain.Running
import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

/**
 * Created by nt67 on 7/21/17.
 */
class ActivitiesTest {
    @Test
    fun totalCalorie_valid() {
        val a = Activities()

        val r1 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )
        val r2 = Running(200
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )

        a.add(r1)
        assertEquals(a.totalCalorie(), 10000)

        a.add(r2)
        assertEquals(a.totalCalorie(), 30000)

        a.remove(r1)
        assertEquals(a.totalCalorie(), 20000)
    }
}
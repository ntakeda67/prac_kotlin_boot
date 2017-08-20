package hello.activity.domain

import common.date.LocalDateTimeRange
import org.junit.Test
import java.time.LocalDateTime

/**
 * Created by nt67 on 7/25/17.
 */
class ReportActivityTest {

    @Test
    fun report_empty_activity() {
        val a= Activities()
        val r = ReportActivity(a)
        r.report()

    }

    @Test
    fun report_activity() {
        val a= Activities(listOf(
                Running.buildInstance(75
                        , Running.Distance(5000)
                        , LocalDateTimeRange(
                          start = LocalDateTime.of(2015, 1, 1, 7, 0, 0)
                        , endInclusive = LocalDateTime.of(2015, 1, 1, 8, 0, 0)))
                , Running.buildInstance(74
                ,   Running.Distance(6000)
                ,   LocalDateTimeRange(
                      start = LocalDateTime.of(2015, 1, 3, 7, 30, 0)
                    , endInclusive = LocalDateTime.of(2015, 1, 3, 9, 0, 0)))
        ))
        val r = ReportActivity(a)
        r.report()
    }

    @Test
    fun report_date_sorted_activity() {
        val a= Activities(listOf(
                Running.buildInstance(75
                        , Running.Distance(5000)
                        , LocalDateTimeRange(
                        start = LocalDateTime.of(2015, 1, 7, 7, 0, 0)
                        , endInclusive = LocalDateTime.of(2015, 1, 7, 8, 0, 0)))
                , Running.buildInstance(74
                  , Running.Distance(6000)
                  , LocalDateTimeRange(
                      start = LocalDateTime.of(2015, 1, 3, 7, 30, 0)
                    , endInclusive = LocalDateTime.of(2015, 1, 3, 9, 0, 0)))
                , Running.buildInstance(74
                  , Running.Distance(4000)
                  , LocalDateTimeRange(
                      start = LocalDateTime.of(2015, 1, 5, 9, 30, 0)
                    , endInclusive = LocalDateTime.of(2015, 1, 5, 10, 10, 0)))
        ))
        val r = ReportActivity(a)
        r.report()
    }

}

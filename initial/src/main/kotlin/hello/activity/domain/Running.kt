package hello.activity.domain

import common.date.LocalDateTimeRange
import hello.activity.domain.attr.Calorie
import hello.activity.domain.attr.CalorieUnit

/**
 * Created by nt67 on 7/21/17.
 */
class Running(
          private val weightKg: Int
        , private val distanceM: Distance
        , private val duration: LocalDateTimeRange) : Activity {
    override fun getWasteCalorie(): Calorie  = Calorie(weightKg * distanceM.meter(), CalorieUnit.CAL)

    override fun getDuration(): LocalDateTimeRange = duration
    class Distance {
        companion object {
            fun acceptRange(): IntRange = 1 .. 100000
            fun isValid(m: Int): Boolean = m in acceptRange()
        }

        private var meter: Int = 0
        fun meter(): Int = meter

        constructor(meter: Int) {
            if(!isValid(meter)) {
                throw IllegalArgumentException("given distance(${meter}) is illegal")
            }
            this.meter = meter
        }

    }
}


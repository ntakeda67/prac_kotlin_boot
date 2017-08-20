package hello.activity.domain

import common.attr.NotYetPersistedID
import common.attr.RecordID
import common.date.LocalDateTimeRange
import common.date.NullLocalDateTimeRange
import hello.activity.domain.attr.Calorie
import hello.activity.domain.attr.CalorieUnit

/**
 * Created by nt67 on 7/21/17.
 */
class Running : Activity {
    private constructor()
    private var weightKg: Int = 0
    private var distanceM: Distance = NullDistance
    private var duration : LocalDateTimeRange = NullLocalDateTimeRange

    companion object {
        fun buildInstance(rid :RecordID
                          , weightKg: Int
                          , distanceM: Distance
                          , duration: LocalDateTimeRange): Running {
            var r = Running()
            r.weightKg = weightKg
            r.distanceM = distanceM
            r.duration = duration
            r.rid = rid
            return r
        }
        fun buildInstance(  weightKg: Int
                          , distanceM: Distance
                          , duration: LocalDateTimeRange): Running {
            return buildInstance(NotYetPersistedID, weightKg, distanceM, duration)
        }
    }

    override fun getActivityID(): RecordID = rid

    override fun getWasteCalorie(): Calorie  = Calorie(weightKg * distanceM.meter(), CalorieUnit.CAL)

    override fun getDuration(): LocalDateTimeRange = duration

    open class Distance {
        companion object {
            fun acceptRange(): IntRange = 0 .. 100000
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

    object NullDistance: Distance(0)
}


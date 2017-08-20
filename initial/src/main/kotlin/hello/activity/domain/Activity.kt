package hello.activity.domain

import common.attr.NotYetPersistedID
import common.attr.RecordID
import common.date.LocalDateTimeRange
import common.domain.PersistableModel
import hello.activity.domain.attr.Calorie
import java.time.LocalDate

abstract class Activity : PersistableModel() {
    protected var rid: RecordID = NotYetPersistedID
    fun setActivityID(rid: RecordID) {
        when(this.rid) {
            // ほんとは persistence層 意外には公開したくない
            // でも、interfaceもひとつ切るのはやり過ぎかなって
            // ビジネスルールに属するものとは思っている
            // 実装整合に困難がおきる場合は Wrapper をしかける
            NotYetPersistedID -> this.rid = rid
            else -> {
                throw IllegalStateException("Not allowed to reassign RecordID")
            }
        }
    }

    abstract fun getActivityID(): RecordID
    abstract fun getWasteCalorie(): Calorie
    abstract fun getDuration(): LocalDateTimeRange
    fun getActivityDate(): LocalDate = getDuration().start.toLocalDate()
}
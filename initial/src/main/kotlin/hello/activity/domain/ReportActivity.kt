package hello.activity.domain

import common.date.LocalDateRange
import common.date.LocalDateTimeRange

/**
 * Created by nt67 on 7/25/17.
 */
class ReportActivity(private val acts: Activities) {

    fun report() {
        if(acts.getAllActivityList().isEmpty() ) {
            println("No Activity yet.")
            return
        }

        acts.getAllActivityList()
                .sortedBy { it.getActivityDate() }
                .map { "Date: ${it.getActivityDate()}, Cal: ${it.getWasteCalorie()}" }
                .forEach{ println(it) }
    }
}
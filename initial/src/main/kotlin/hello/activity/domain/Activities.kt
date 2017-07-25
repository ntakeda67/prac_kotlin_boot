package hello.activity.domain

import common.date.LocalDateRange
import java.time.LocalDate

/**
 * Created by nt67 on 7/21/17.
 */
class Activities {
    private val activityList: MutableList<Activity> = ArrayList()

    constructor(){}
    constructor(activityList: List<Activity>) {
        this.activityList.clear()
        this.activityList.addAll(activityList)
    }

    fun add(act: Activity) { activityList.add(act) }
    fun remove(act: Activity) { activityList.remove(act) }
    fun totalCalorie(): Int {
        return activityList.sumBy { it.getWasteCalorie().get() }
    }
    fun totalCalorie(date : LocalDate): Int {
        return activityList
                .filter { it.getActivityDate().equals(date) }
                .sumBy { it.getWasteCalorie().get() }
    }

    fun getAllActivityList() : List<Activity> = activityList.toList()
    fun getActivityList(dateRange: LocalDateRange): List<Activity> {
        return activityList.filter { it.getActivityDate() in dateRange }
    }
}
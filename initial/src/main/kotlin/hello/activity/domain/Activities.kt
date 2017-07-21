package hello.activity.domain

import hello.activity.domain.attr.Calorie

/**
 * Created by nt67 on 7/21/17.
 */
class Activities {
    private val activityList: MutableList<Activity> = ArrayList()
    fun add(act: Activity) { activityList.add(act) }
    fun remove(act: Activity) { activityList.remove(act) }
    fun totalCalorie(): Int {
        return activityList.sumBy { it.getWasteCalorie().get() }
    }
}
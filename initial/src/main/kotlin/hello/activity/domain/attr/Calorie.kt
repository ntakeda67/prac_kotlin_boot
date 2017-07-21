package hello.activity.domain.attr

/**
 * Created by nt67 on 7/20/17.
 */
data class Calorie(private val value:Int, private val unit: CalorieUnit) {
    fun get() = value * unit.toCalorieRate()

    // TODO 四捨五入
    fun get(calUnit: CalorieUnit) = get() / calUnit.toCalorieRate()
    override fun toString() = "${value} [${unit}]"
}

enum class CalorieUnit {
    CAL {
        override fun toCalorieRate() = 1
        override fun toString() = "cal"
    }
    , KCAL {
        override fun toCalorieRate() = 1000
        override fun toString() = "kcal"
    };

    abstract fun toCalorieRate(): Int
}
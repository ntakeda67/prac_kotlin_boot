package hello.user.domain.attr

enum class Salutation {
    MR{
        override fun toString():String = "Mr."
    },
    MS{
        override fun toString():String = "Ms."
    },
    MRS{
        override fun toString():String = "Mrs."
    },
    MISS{
        override fun toString():String = "Miss."
    },
    DR{
        override fun toString():String = "Dr."
    },
    EMPTY {
        override fun toString():String = ""
    }
}
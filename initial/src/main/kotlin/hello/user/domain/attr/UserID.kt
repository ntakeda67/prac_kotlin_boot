package hello.user.domain.attr

open class UserID(private val id:String) {
    override fun equals(other: Any?): Boolean {
        when(other) {
            is UserID -> return other.id == id
            else -> return false
        }
    }
}

object UndefinedUserID: UserID("Undefined")
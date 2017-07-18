package hello.user.domain

import hello.user.domain.attr.*

class User(private var id:UserID, private var name:FullName) {

    constructor(name: FullName): this(UndefinedUserID, name)

    fun getUserID() = this.id
    fun rename(name:FullName) {
        this.name = name
    }

    fun assignID(id: UserID) {
        when(this.id) {
            is UndefinedUserID -> this.id = id
            else -> throw java.lang.IllegalAccessException("" +
                    "UserID is already assingned. ID=${this.id}")
        }
        this.id = id;
    }
}
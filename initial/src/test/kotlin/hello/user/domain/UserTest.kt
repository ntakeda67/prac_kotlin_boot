package hello.user.domain

import hello.user.domain.attr.*
import kotlin.test.assertEquals
import org.junit.Test

class UserTest {
    @Test
    fun generateWithoutUserID_givenUndefined(){
        val user = User(FullName(
                firstName = "Test"
                , lastName = "User"))
        assertEquals(UndefinedUserID, user.getUserID())
    }

    @Test
    fun UserIDisUndefined_canAssignUserID() {
        val user = User(FullName(
                firstName = "Test"
                , lastName = "User"))
        user.assignID(UserID("newID"))
        assertEquals(UserID("newID"), user.getUserID())
    }

    @Test(expected = java.lang.IllegalAccessException::class)
    fun UserIDcannotReassign() {
        val user = User(
                UserID("firstID")
                , FullName(
                        firstName = "Test"
                        , lastName = "User"))
        user.assignID(UserID("newID"))
    }

    @Test
    fun canRenameUser() {
        val oldName = FullName(
                firstName = "Test"
                , lastName = "User")
        val newName = FullName(
                firstName = "Renamed"
                , lastName = "User")
        val user = User(
                UserID("firstID")
                , oldName)
        assertEquals(oldName, user.getName())

        user.rename(newName)

        assertEquals(newName, user.getName())
    }
}
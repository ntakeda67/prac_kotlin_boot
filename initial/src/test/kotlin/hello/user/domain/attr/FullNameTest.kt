package hello.user.domain.attr

import org.junit.Test
import kotlin.test.assertEquals

class FullNameTest {

    object TestData {
        val allProp = FullName(
                firstName = "First"
                , lastName = "Last"
                , middleName = "Middle"
                , salutation = Salutation.MR)

        val noMiddle = FullName(
                firstName = "First"
                , lastName = "Last"
                , salutation = Salutation.MRS)

        val noSalut = FullName(
                firstName = "First"
                , lastName = "Last"
                , middleName = "Middle")
    }

    @Test
    fun allProp_toString() {
        assertEquals(
                "First Middle Last"
                , TestData.allProp.toString()
        )
    }

    @Test
    fun allProp_toStringWithSalutation(){
        assertEquals(
                "Mr. First Middle Last"
                , TestData.allProp.toStringWithSalutation()
                )
    }

    @Test
    fun middleNameIsEmpty_notConcatenate_toString() {
        assertEquals(
                "First Last"
                , TestData.noMiddle.toString()
        )
    }

    @Test
    fun middleNameIsEmpty_notConcatenate_toStringWithSalutation(){
        assertEquals(
                "Mrs. First Last"
                , TestData.noMiddle.toStringWithSalutation()
        )
    }

    @Test
    fun noSalutation_notConcatenate_toStringWithSalutation(){
        assertEquals(
                "First Middle Last"
                , TestData.noSalut.toStringWithSalutation()
        )
    }

}
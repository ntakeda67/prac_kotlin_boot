package hello.user.domain.attr

data class FullName(
          val firstName: String
        , val lastName: String
        , val middleName: String = ""
        , val salutation: Salutation = Salutation.EMPTY
) {
    override fun toString(): String {
        if(middleName.length > 0) {
            return "${firstName} ${middleName} ${lastName}"
        }
        return "${firstName} ${lastName}"
    }

    fun toStringWithSalutation(): String {
        return when(salutation) {
            Salutation.EMPTY -> toString()
            else -> "${salutation} ${toString()}"
        }
    }
}
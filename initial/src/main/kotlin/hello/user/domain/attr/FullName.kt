package hello.user.domain.attr

data class FullName(
          val firstName: String
        , val lastName: String
        , val middleName: String = ""
        , val salutation: Salutation = Salutation.EMPTY
)
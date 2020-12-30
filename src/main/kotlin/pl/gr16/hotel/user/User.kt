package pl.gr16.hotel.user

data class User(
        val id: Int = 0,
        val firstName: String,
        val lastName: String,
        val login: String,
        val password: String
)

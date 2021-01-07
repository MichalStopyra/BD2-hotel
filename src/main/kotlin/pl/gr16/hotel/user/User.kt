package pl.gr16.hotel.user

import org.jetbrains.exposed.dao.id.IntIdTable

data class User(
        val id: Int = 0,
        val firstName: String,
        val lastName: String,
        val login: String,
        val password: String
)

object UserTable : IntIdTable(name="UserTab") {
    val firstName = varchar("firstName", 50)
    val lastName = varchar("lastName", 50)
    val login = varchar("login", 50)
    val password = varchar("password", 50)
}

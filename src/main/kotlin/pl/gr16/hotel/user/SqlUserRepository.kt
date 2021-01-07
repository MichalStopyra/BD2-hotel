package pl.gr16.hotel.user

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlUserRepository: UserRepository {
    override fun findAll() =
            UserTable
                    .selectAll()
                    .map {
                        User (
                                it[UserTable.id].value,
                                it[UserTable.firstName],
                                it[UserTable.lastName],
                                it[UserTable.login],
                                it[UserTable.password]
                                )
                    }
}

package pl.gr16.hotel.user

interface UserRepository {
    fun findAll(): List<User>
}
package pl.gr16.hotel.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService (
    private val userRepository: UserRepository) {
    fun findAll() = userRepository.findAll()
}
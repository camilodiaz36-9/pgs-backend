package co.edu.udistrital.esping.pgs.api.users.repository

import co.edu.udistrital.esping.pgs.api.users.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {
    fun findByLoginAndPassword(login: String, password: String): List<User>
}
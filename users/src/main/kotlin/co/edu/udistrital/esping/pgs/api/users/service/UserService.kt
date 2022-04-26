package co.edu.udistrital.esping.pgs.api.users.service

import co.edu.udistrital.esping.pgs.api.users.dto.UserDTO
import co.edu.udistrital.esping.pgs.api.users.model.LoginDTO

interface UserService {
    fun createUser(user: UserDTO): UserDTO
    fun updateUser(user: UserDTO, id: Int): UserDTO?
    fun deleteUser(id: Int)
    fun getUserById(id: Int): UserDTO?
    fun getAllUsers(): List<UserDTO>
    fun login(login: LoginDTO): UserDTO?
}
package co.edu.udistrital.esping.pgs.api.users.service.impl

import co.edu.udistrital.esping.pgs.api.users.dto.UserDTO
import co.edu.udistrital.esping.pgs.api.users.dto.LoginDTO
import co.edu.udistrital.esping.pgs.api.users.model.User
import co.edu.udistrital.esping.pgs.api.users.repository.UserRepository
import co.edu.udistrital.esping.pgs.api.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun createUser(user: UserDTO): UserDTO {
        val userEntity = dtoToEntity(user)

        val userInserted = userRepository.save(userEntity)

        return entityToDTO(userInserted)
    }

    override fun updateUser(user: UserDTO, id: Int): UserDTO? {
        val foundUser = userRepository.findById(id)

        return if (foundUser.isPresent) {
            val userEntity = foundUser.get()

            userEntity.id = id
            userEntity.email = user.email
            userEntity.password = user.password
            userEntity.login = user.login
            userEntity.phone = user.phone
            userEntity.address = user.address
            userEntity.token = user.token

            val userUpdated = userRepository.save(userEntity)

            entityToDTO(userUpdated)
        } else {
            null
        }
    }

    override fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }

    override fun getUserById(id: Int): UserDTO? {
        val foundUser = userRepository.findById(id)

        return if (foundUser.isPresent) {
            val userEntity = foundUser.get()
            entityToDTO(userEntity)
        } else {
            null
        }
    }

    override fun getAllUsers(): List<UserDTO> {
        val listAllUsers = userRepository.findAll()

        val listUserResponse = arrayListOf<UserDTO>()

        for (userEntity in listAllUsers) {
            val user = entityToDTO(userEntity)
            listUserResponse.add(user)
        }

        return listUserResponse
    }

    override fun login(login: LoginDTO): UserDTO? {
        val foundUser = userRepository.findByLoginAndPassword(login.username, login.password)

        return if(foundUser.isNotEmpty()){
            entityToDTO(foundUser[0])
        } else {
            null
        }
    }

    private fun entityToDTO(userEntity: User): UserDTO {
        val id = userEntity.id
        val email = userEntity.email
        val password = userEntity.password
        val login = userEntity.login
        val phone = userEntity.phone
        val address = userEntity.address
        val token = userEntity.token

        return UserDTO(id, email, password, login, phone, address, token)
    }

    private fun dtoToEntity(userDTO: UserDTO): User {
        val email = userDTO.email
        val password = userDTO.password
        val login = userDTO.login
        val phone = userDTO.phone
        val address = userDTO.address
        val token = userDTO.token

        val userEntity = User()
        userEntity.email = email
        userEntity.password = password
        userEntity.login = login
        userEntity.phone = phone
        userEntity.address = address
        userEntity.token = token

        return userEntity
    }
}
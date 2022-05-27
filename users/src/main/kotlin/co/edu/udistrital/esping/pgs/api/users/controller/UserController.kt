package co.edu.udistrital.esping.pgs.api.users.controller

import co.edu.udistrital.esping.pgs.api.users.dto.UserDTO
import co.edu.udistrital.esping.pgs.api.users.dto.LoginDTO
import co.edu.udistrital.esping.pgs.api.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/user")
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        val response = userService.createUser(user)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/user/{id}")
    fun updateUser(
        @RequestBody user: UserDTO,
        @PathVariable(required = true) id: Int
    ): ResponseEntity<UserDTO>? {
        val response = userService.updateUser(user, id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable(required = true) id: Int): ResponseEntity<Any> {
        userService.deleteUser(id)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable(required = true) id: Int): ResponseEntity<UserDTO> {
        val response = userService.getUserById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/user")
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        val response = userService.getAllUsers()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/user/login")
    fun login(@RequestBody login: LoginDTO): ResponseEntity<Any> {
        val response = userService.login(login)

        return if(response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

    }
}
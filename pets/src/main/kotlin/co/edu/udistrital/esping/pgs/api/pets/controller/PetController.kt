package co.edu.udistrital.esping.pgs.api.pets.controller

import co.edu.udistrital.esping.pgs.api.pets.dto.PetDto
import co.edu.udistrital.esping.pgs.api.pets.service.PetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PetController {

    @Autowired
    private lateinit var petService: PetService

    @PostMapping("/pet")
    fun createPet(@RequestBody pet: PetDto): ResponseEntity<PetDto> {
        val response = petService.createPet(pet)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/pet/{id}")
    fun getPetById(@PathVariable(required = true) id: Int): ResponseEntity<PetDto> {
        val response = petService.getPetById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/pet")
    fun getAllPets(): ResponseEntity<List<PetDto>> {
        val response = petService.getAllPets()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/pet/user/{id}")
    fun getAllPetsByUser(@PathVariable(required = true) id: Int): ResponseEntity<List<PetDto>> {
        val response = petService.getPetsByUser(idUser = id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/pet/{id}")
    fun deletePet(@PathVariable(required = true) id: Int): ResponseEntity<Any> {
        petService.deletePet(id)
        return ResponseEntity(null, HttpStatus.OK)
    }

}
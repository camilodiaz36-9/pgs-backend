package co.edu.udistrital.esping.pgs.api.pettypes.controller

import co.edu.udistrital.esping.pgs.api.pettypes.dto.PetTypeDTO
import co.edu.udistrital.esping.pgs.api.pettypes.service.PetTypeService
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
class PetTypeController {

    @Autowired
    private lateinit var petTypeService: PetTypeService

    @PostMapping("/petType")
    fun createPetType(@RequestBody petType: PetTypeDTO): ResponseEntity<PetTypeDTO> {
        val response = petTypeService.createPetType(petType)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/petType/{id}")
    fun updatePetType(
        @RequestBody petType: PetTypeDTO,
        @PathVariable(required = true) id: Int
    ): ResponseEntity<PetTypeDTO>? {
        val response = petTypeService.updatePetType(petType, id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/petType/{id}")
    fun deletePetType(@PathVariable(required = true) id: Int): ResponseEntity<Any> {
        petTypeService.deletePetType(id)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @GetMapping("/petType/{id}")
    fun getPetTypeById(@PathVariable(required = true) id: Int): ResponseEntity<PetTypeDTO> {
        val response = petTypeService.getPetTypeById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/petType")
    fun getAllPetTypes(): ResponseEntity<List<PetTypeDTO>> {
        val response = petTypeService.getAllPetTypes()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
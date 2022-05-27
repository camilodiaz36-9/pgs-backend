package co.edu.udistrital.esping.pgs.api.pets.service

import co.edu.udistrital.esping.pgs.api.pets.dto.PetDto

interface PetService {
    fun getPetById(id: Int): PetDto?
    fun getAllPets(): List<PetDto>
    fun getPetsByUser(idUser: Int): List<PetDto>?
    fun createPet(petDto: PetDto): PetDto
    fun deletePet(id: Int)
}
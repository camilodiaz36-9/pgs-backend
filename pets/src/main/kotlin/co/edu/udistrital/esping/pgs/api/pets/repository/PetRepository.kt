package co.edu.udistrital.esping.pgs.api.pets.repository

import co.edu.udistrital.esping.pgs.api.pets.model.Pet
import co.edu.udistrital.esping.pgs.api.pets.model.User
import org.springframework.data.repository.CrudRepository

interface PetRepository: CrudRepository<Pet, Int> {
    fun findByIdUser(idUser: User): List<Pet>
}
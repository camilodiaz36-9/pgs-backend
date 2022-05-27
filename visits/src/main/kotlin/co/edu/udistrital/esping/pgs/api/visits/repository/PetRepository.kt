package co.edu.udistrital.esping.pgs.api.visits.repository

import co.edu.udistrital.esping.pgs.api.visits.model.Pet
import org.springframework.data.repository.CrudRepository

interface PetRepository: CrudRepository<Pet, Int> {
}
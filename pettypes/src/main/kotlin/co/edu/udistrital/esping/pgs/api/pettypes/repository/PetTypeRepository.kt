package co.edu.udistrital.esping.pgs.api.pettypes.repository

import co.edu.udistrital.esping.pgs.api.pettypes.model.PetType
import org.springframework.data.repository.CrudRepository

interface PetTypeRepository: CrudRepository<PetType, Int> {

}
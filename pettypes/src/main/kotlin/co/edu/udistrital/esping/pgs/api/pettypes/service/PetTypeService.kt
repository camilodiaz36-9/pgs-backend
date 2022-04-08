package co.edu.udistrital.esping.pgs.api.pettypes.service

import co.edu.udistrital.esping.pgs.api.pettypes.dto.PetTypeDTO

interface PetTypeService {
    fun createPetType(petType: PetTypeDTO) : PetTypeDTO
    fun updatePetType(petType: PetTypeDTO) : PetTypeDTO?
    fun deletePetType(id: Int)
    fun getPetTypeById(id: Int): PetTypeDTO
    fun getAllPetTypes(): List<PetTypeDTO>
}
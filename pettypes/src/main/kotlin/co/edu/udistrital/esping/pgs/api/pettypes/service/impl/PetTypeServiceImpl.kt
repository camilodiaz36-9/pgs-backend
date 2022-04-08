package co.edu.udistrital.esping.pgs.api.pettypes.service.impl

import co.edu.udistrital.esping.pgs.api.pettypes.dto.PetTypeDTO
import co.edu.udistrital.esping.pgs.api.pettypes.repository.PetTypeRepository
import co.edu.udistrital.esping.pgs.api.pettypes.service.PetTypeService
import org.springframework.beans.factory.annotation.Autowired

class PetTypeServiceImpl : PetTypeService {

    @Autowired
    private lateinit var petTypeRepository: PetTypeRepository

    override fun createPetType(petType: PetTypeDTO): PetTypeDTO {
        TODO("Not yet implemented")
    }

    override fun updatePetType(petType: PetTypeDTO): PetTypeDTO? {
        TODO("Not yet implemented")
    }

    override fun deletePetType(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getPetTypeById(id: Int): PetTypeDTO {
        TODO("Not yet implemented")
    }

    override fun getAllPetTypes(): List<PetTypeDTO> {
        TODO("Not yet implemented")
    }

    private fun entityToDTO(){

    }

    private fun dtoToEntity(){

    }
}
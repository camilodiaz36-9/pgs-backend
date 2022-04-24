package co.edu.udistrital.esping.pgs.api.pettypes.service.impl

import co.edu.udistrital.esping.pgs.api.pettypes.dto.PetTypeDTO
import co.edu.udistrital.esping.pgs.api.pettypes.model.PetType
import co.edu.udistrital.esping.pgs.api.pettypes.repository.PetTypeRepository
import co.edu.udistrital.esping.pgs.api.pettypes.service.PetTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PetTypeServiceImpl : PetTypeService {

    @Autowired
    private lateinit var petTypeRepository: PetTypeRepository

    override fun createPetType(petType: PetTypeDTO): PetTypeDTO {
        val petTypeEntity = dtoToEntity(petType)

        val petTypeInserted = petTypeRepository.save(petTypeEntity)

        return entityToDTO(petTypeInserted)
    }

    override fun updatePetType(petType: PetTypeDTO, id: Int): PetTypeDTO? {
        val foundPetType = petTypeRepository.findById(id)

        return if(foundPetType.isPresent) {
            val petTypeEntity = foundPetType.get()

            petTypeEntity.id = id
            petTypeEntity.name = petType.name

            val petTypeUpdated = petTypeRepository.save(petTypeEntity)

            entityToDTO(petTypeUpdated)
        } else {
            null
        }
    }

    override fun deletePetType(id: Int) {
        petTypeRepository.deleteById(id)
    }

    override fun getPetTypeById(id: Int): PetTypeDTO? {
        val foundPetType = petTypeRepository.findById(id)

        return if(foundPetType.isPresent) {
            val petTypeEntity = foundPetType.get()
            entityToDTO(petTypeEntity)
        } else {
            null
        }
    }

    override fun getAllPetTypes(): List<PetTypeDTO> {
        val listAllPetTypes = petTypeRepository.findAll()

        val listPetTypeResponse = arrayListOf<PetTypeDTO>()

        for (petTypeEntity in listAllPetTypes) {
            val petType = entityToDTO(petTypeEntity)
            listPetTypeResponse.add(petType)
        }

        return listPetTypeResponse
    }

    private fun entityToDTO(petTypeEntity: PetType): PetTypeDTO{
        val id = petTypeEntity.id
        val name = petTypeEntity.name

        return PetTypeDTO(id, name)
    }

    private fun dtoToEntity(petTypeDTO: PetTypeDTO): PetType{
        val name = petTypeDTO.name

        val petTypeEntity = PetType()
        petTypeEntity.name = name

        return petTypeEntity
    }
}
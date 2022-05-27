package co.edu.udistrital.esping.pgs.api.pets.service.impl

import co.edu.udistrital.esping.pgs.api.pets.dto.PetDto
import co.edu.udistrital.esping.pgs.api.pets.model.Pet
import co.edu.udistrital.esping.pgs.api.pets.model.User
import co.edu.udistrital.esping.pgs.api.pets.repository.PetRepository
import co.edu.udistrital.esping.pgs.api.pets.service.PetService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class PetServiceImpl: PetService {

    @Autowired
    private lateinit var petRepository: PetRepository

    override fun getPetById(id: Int): PetDto? {
        val foundPet = petRepository.findById(id)

        return if (foundPet.isPresent){
            val petEntity = foundPet.get()
            entityToDTO(petEntity)
        } else {
            null
        }
    }

    override fun getAllPets(): List<PetDto> {
        val listAllPets = petRepository.findAll()

        val listPetResponse = arrayListOf<PetDto>()

        for (petEntity in listAllPets) {
            val pet = entityToDTO(petEntity)
            listPetResponse.add(pet)
        }

        return listPetResponse
    }

    override fun getPetsByUser(idUser: Int): List<PetDto>? {
        val user = User()
        user.id = idUser

        val listPetsByUser = petRepository.findByIdUser(user)

        val listPetResponse = arrayListOf<PetDto>()

        for (petEntity in listPetsByUser) {
            val pet = entityToDTO(petEntity)
            listPetResponse.add(pet)
        }

        return listPetResponse
    }

    override fun createPet(petDto: PetDto): PetDto {
        val petEntity = dtoToEntity(petDto)

        val petInserted = petRepository.save(petEntity)

        return entityToDTO(petInserted)
    }

    override fun deletePet(id: Int) {
        petRepository.deleteById(id)
    }

    private fun entityToDTO(petEntity: Pet): PetDto {
        return modelMapperInstance().map(petEntity, PetDto::class.java)
    }

    private fun dtoToEntity(petDto: PetDto): Pet {
        return modelMapperInstance().map(petDto, Pet::class.java)
    }

    @Bean
    fun modelMapperInstance(): ModelMapper {
        return ModelMapper()
    }
}
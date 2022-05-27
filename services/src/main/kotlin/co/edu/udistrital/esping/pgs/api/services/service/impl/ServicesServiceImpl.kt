package co.edu.udistrital.esping.pgs.api.services.service.impl

import co.edu.udistrital.esping.pgs.api.services.dto.ServiceDto
import co.edu.udistrital.esping.pgs.api.services.model.Service
import co.edu.udistrital.esping.pgs.api.services.repository.ServiceRepository
import co.edu.udistrital.esping.pgs.api.services.service.ServicesService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

@org.springframework.stereotype.Service
class ServicesServiceImpl : ServicesService {

    @Autowired
    private lateinit var serviceRepository: ServiceRepository

    override fun getServiceById(id: Int): ServiceDto? {
        val foundService = serviceRepository.findById(id)

        return if(foundService.isPresent) {
            val serviceEntity = foundService.get()
            entityToDto(serviceEntity)
        } else {
            null
        }
    }

    override fun getAllServices(): List<ServiceDto> {
        val listAllServices = serviceRepository.findAll()

        val listServiceResponse = arrayListOf<ServiceDto>()

        listAllServices.forEach { serviceEntity ->
            val service = entityToDto(serviceEntity)
            listServiceResponse.add(service)
        }

        return listServiceResponse

    }

    fun entityToDto(entity: Service): ServiceDto {
        return modelMapperInstance().map(entity, ServiceDto::class.java)
    }

    fun dtoToEntity(dto: ServiceDto): Service {
        return modelMapperInstance().map(dto, Service::class.java)
    }

    @Bean
    fun modelMapperInstance(): ModelMapper {
        return ModelMapper()
    }
}
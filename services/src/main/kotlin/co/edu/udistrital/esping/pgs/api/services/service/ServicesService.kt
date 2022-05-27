package co.edu.udistrital.esping.pgs.api.services.service

import co.edu.udistrital.esping.pgs.api.services.dto.ServiceDto

interface ServicesService {
    fun getServiceById(id: Int): ServiceDto?
    fun getAllServices(): List<ServiceDto>
}
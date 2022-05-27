package co.edu.udistrital.esping.pgs.api.services.controller

import co.edu.udistrital.esping.pgs.api.services.dto.ServiceDto
import co.edu.udistrital.esping.pgs.api.services.service.ServicesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ServiceController {

    @Autowired
    private lateinit var servicesService: ServicesService

    @GetMapping("/service/{id}")
    fun getServiceById(@PathVariable(required = true) id: Int): ResponseEntity<ServiceDto> {
        val response = servicesService.getServiceById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/service")
    fun getAllServices(): ResponseEntity<List<ServiceDto>>{
        val response = servicesService.getAllServices()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
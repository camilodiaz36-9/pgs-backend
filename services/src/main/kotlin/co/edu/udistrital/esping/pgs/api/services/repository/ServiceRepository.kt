package co.edu.udistrital.esping.pgs.api.services.repository

import co.edu.udistrital.esping.pgs.api.services.model.Service
import org.springframework.data.repository.CrudRepository

interface ServiceRepository : CrudRepository<Service, Int>
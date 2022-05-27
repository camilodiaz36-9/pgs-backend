package co.edu.udistrital.esping.pgs.api.visits.repository

import co.edu.udistrital.esping.pgs.api.visits.model.Status
import org.springframework.data.repository.CrudRepository

interface StatusRepository: CrudRepository<Status, Int> {
}
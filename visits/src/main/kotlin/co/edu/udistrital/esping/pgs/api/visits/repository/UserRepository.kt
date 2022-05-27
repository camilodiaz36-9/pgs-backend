package co.edu.udistrital.esping.pgs.api.visits.repository

import co.edu.udistrital.esping.pgs.api.visits.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {
}
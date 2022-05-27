package co.edu.udistrital.esping.pgs.api.clients.repository

import co.edu.udistrital.esping.pgs.api.clients.model.Client
import co.edu.udistrital.esping.pgs.api.clients.model.User
import org.springframework.data.repository.CrudRepository

interface ClientRepository : CrudRepository<Client, Int> {
    fun findByUser(user: User): Client
}
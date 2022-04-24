package co.edu.udistrital.esping.pgs.api.clients.service

import co.edu.udistrital.esping.pgs.api.clients.dto.ClientDTO

interface ClientService {
    fun createClient(client: ClientDTO): ClientDTO
    fun updateClient(client: ClientDTO, id: Int): ClientDTO?
    fun deleteClient(id: Int)
    fun getClientById(id: Int): ClientDTO?
    fun getAllClients(): List<ClientDTO>
}
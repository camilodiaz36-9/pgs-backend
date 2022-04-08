package co.edu.udistrital.esping.pgs.api.clients.service.impl

import co.edu.udistrital.esping.pgs.api.clients.dto.ClientDTO
import co.edu.udistrital.esping.pgs.api.clients.model.Client
import co.edu.udistrital.esping.pgs.api.clients.repository.ClientRepository
import co.edu.udistrital.esping.pgs.api.clients.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl : ClientService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    override fun createClient(client: ClientDTO): ClientDTO {
        val clientEntity = dtoToEntity(client)

        val clientInserted = clientRepository.save(clientEntity)

        return entityToDTO(clientInserted)
    }

    override fun updateClient(client: ClientDTO): ClientDTO? {
        val foundClient = client.id?.let { clientRepository.findById(it) }

        if (foundClient != null) {
            if (foundClient.isPresent) {
                val clientEntity = foundClient.get()

                clientEntity.id = client.id
                clientEntity.documentType = client.documentType
                clientEntity.clientName = client.clientName
                clientEntity.address = client.address
                clientEntity.phone = client.phone
                clientEntity.lastVisited = client.lastVisited
                clientEntity.discount = client.discount
                clientEntity.pendingForPayment = client.pendingForPayment
                clientEntity.city = client.city

                val clientUpdated = clientRepository.save(clientEntity)

                return entityToDTO(clientUpdated)
            } else {
                return null
            }
        } else {
            return null
        }
    }

    override fun deleteClient(id: Int) {
        clientRepository.deleteById(id)
    }

    override fun getClientById(id: Int): ClientDTO? {
        val foundClient = clientRepository.findById(id)

        return if (foundClient.isPresent) {
            val clientEntity = foundClient.get()
            entityToDTO(clientEntity)
        } else {
            null
        }

    }

    override fun getAllClients(): List<ClientDTO> {
        val listAllClients = clientRepository.findAll()

        val listClientsResponse = arrayListOf<ClientDTO>()

        for (clientEntity in listAllClients) {
            val client = entityToDTO(clientEntity)
            listClientsResponse.add(client)
        }

        return listClientsResponse
    }

    fun entityToDTO(clientEntity: Client): ClientDTO {
        val id = clientEntity.id
        val documentType = clientEntity.documentType
        val clientName = clientEntity.clientName
        val address = clientEntity.address
        val phone = clientEntity.phone
        val lastVisited = clientEntity.lastVisited
        val discount = clientEntity.discount
        val pendingForPayment = clientEntity.pendingForPayment
        val city = clientEntity.city

        return ClientDTO(
            id, documentType, clientName, address, phone, lastVisited, discount, pendingForPayment, city
        )
    }

    fun dtoToEntity(clientDTO: ClientDTO): Client {
        val id = clientDTO.id
        val documentType = clientDTO.documentType
        val clientName = clientDTO.clientName
        val address = clientDTO.address
        val phone = clientDTO.phone
        val lastVisited = clientDTO.lastVisited
        val discount = clientDTO.discount
        val pendingForPayment = clientDTO.pendingForPayment
        val city = clientDTO.city

        val clientEntity = Client()
        clientEntity.id = id
        clientEntity.documentType = documentType
        clientEntity.clientName = clientName
        clientEntity.address = address
        clientEntity.phone = phone
        clientEntity.lastVisited = lastVisited
        clientEntity.discount = discount
        clientEntity.pendingForPayment = pendingForPayment
        clientEntity.city = city

        return clientEntity
    }

}
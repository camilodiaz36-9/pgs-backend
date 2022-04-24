package co.edu.udistrital.esping.pgs.api.clients.service.impl

import co.edu.udistrital.esping.pgs.api.clients.dto.ClientDTO
import co.edu.udistrital.esping.pgs.api.clients.model.Client
import co.edu.udistrital.esping.pgs.api.clients.model.User
import co.edu.udistrital.esping.pgs.api.clients.repository.ClientRepository
import co.edu.udistrital.esping.pgs.api.clients.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class ClientServiceImpl : ClientService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    override fun createClient(client: ClientDTO): ClientDTO {
        val clientEntity = dtoToEntity(client)

        val clientInserted = clientRepository.save(clientEntity)

        return entityToDTO(clientInserted)
    }

    override fun updateClient(client: ClientDTO, id: Int): ClientDTO? {
        val foundClient = clientRepository.findById(id)

        if (foundClient.isPresent) {
            val clientEntity = foundClient.get()

            clientEntity.id = id
            clientEntity.clientNumber = client.clientNumber
            clientEntity.documentType = client.documentType
            clientEntity.clientName = client.clientName
            clientEntity.lastVisited = client.lastVisited?.toInstant(ZoneOffset.UTC)
            clientEntity.discount = client.discount
            clientEntity.pendingPayment = client.pendingForPayment

            val userEntity = User()
            userEntity.id = client.user

            clientEntity.user = userEntity

            val clientUpdated = clientRepository.save(clientEntity)

            return entityToDTO(clientUpdated)
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
        val clientNumber = clientEntity.clientNumber
        val documentType = clientEntity.documentType
        val clientName = clientEntity.clientName
        val lastVisited = LocalDateTime.ofInstant(clientEntity.lastVisited, ZoneOffset.UTC)
        val discount = clientEntity.discount
        val pendingForPayment = clientEntity.pendingPayment
        val user = clientEntity.user?.id

        return ClientDTO(
            id, clientNumber, documentType, clientName, lastVisited, discount, pendingForPayment, user
        )
    }

    fun dtoToEntity(clientDTO: ClientDTO): Client {
        val clientNumber = clientDTO.clientNumber
        val documentType = clientDTO.documentType
        val clientName = clientDTO.clientName
        val lastVisited = clientDTO.lastVisited
        val discount = clientDTO.discount
        val pendingForPayment = clientDTO.pendingForPayment
        val user = clientDTO.user

        val clientEntity = Client()
        clientEntity.clientNumber = clientNumber
        clientEntity.documentType = documentType
        clientEntity.clientName = clientName
        clientEntity.lastVisited = lastVisited?.toInstant(ZoneOffset.UTC)
        clientEntity.discount = discount
        clientEntity.pendingPayment = pendingForPayment

        val userEntity = User()
        userEntity.id = user

        clientEntity.user = userEntity

        return clientEntity
    }

}
package co.edu.udistrital.esping.pgs.api.clients.controller

import co.edu.udistrital.esping.pgs.api.clients.dto.ClientDTO
import co.edu.udistrital.esping.pgs.api.clients.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class ClientController {

    @Autowired
    private lateinit var clientService: ClientService

    @PostMapping("/clients")
    fun createClient(@RequestBody client: ClientDTO): ResponseEntity<ClientDTO> {
        val response = clientService.createClient(client)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/clients/{id}")
    fun updateClient(
        @RequestBody client: ClientDTO,
        @PathVariable(required = true) id: Int
    ): ResponseEntity<ClientDTO>? {
        val response = clientService.updateClient(client, id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/clients/{id}")
    fun deleteClient(@PathVariable(required = true) id: Int): ResponseEntity<Any> {
        clientService.deleteClient(id)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @GetMapping("/clients/{id}")
    fun getClientById(id: Int): ResponseEntity<ClientDTO> {
        val response = clientService.getClientById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/clients")
    fun getAllClients(): ResponseEntity<List<ClientDTO>> {
        val response = clientService.getAllClients()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
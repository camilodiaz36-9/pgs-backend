package co.edu.udistrital.esping.pgs.api.clients.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class ClientDTO(
    val id: Int?,
    val clientNumber: Int?,
    val documentType: Int?,
    val clientName: String?,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val lastVisited: LocalDateTime?,
    val discount: Int?,
    val pendingForPayment: Int?,
    val user: Int?
)

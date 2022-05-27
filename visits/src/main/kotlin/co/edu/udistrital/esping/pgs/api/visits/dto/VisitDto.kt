package co.edu.udistrital.esping.pgs.api.visits.dto

import java.io.Serializable
import java.time.Instant
import java.time.LocalTime

data class VisitDto(
    var id: Int? = null,
    var visitDate: Instant? = null,
    var initialDescription: String? = null,
    var finalDescription: String? = null,
    var realTimeUsed: LocalTime? = null,
    var totalAmount: Int? = null,
    var uuid: String? = null,
    var photo: String? = null,
    var idService: ServiceDto? = null,
    var idStatus: StatusDto? = null,
    var idUser: UserDto? = null,
    var idPet: PetDto? = null
) : Serializable

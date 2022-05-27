package co.edu.udistrital.esping.pgs.api.services.dto

import java.io.Serializable
import java.time.LocalTime

data class ServiceDto(
    var id: Int? = null,
    var name: String? = null,
    var averageTime: LocalTime? = null,
    var baseAmount: Int? = null
) : Serializable

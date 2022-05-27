package co.edu.udistrital.esping.pgs.api.visits.dto

data class DataDto(
    val title: String,
    val message: String,
    val type: Int,
    val destination: Int,
    val visitId: Int,
    val photo: String?
)

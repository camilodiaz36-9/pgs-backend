package co.edu.udistrital.esping.pgs.api.users.dto

data class UserDTO(
    val id: Int?,
    val email: String?,
    val password: String?,
    val login: String?,
    val phone: Double?,
    val address: String?
)

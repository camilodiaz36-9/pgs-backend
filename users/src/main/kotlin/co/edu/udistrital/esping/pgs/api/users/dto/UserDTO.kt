package co.edu.udistrital.esping.pgs.api.users.dto

import java.math.BigDecimal

data class UserDTO(
    val id: Int?,
    val email: String?,
    val password: String?,
    val login: String?,
    val phone: BigDecimal?,
    val address: String?,
    val token: String?
)

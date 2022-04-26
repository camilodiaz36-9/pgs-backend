package co.edu.udistrital.esping.pgs.api.users.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "users")
open class User {
    @Id
    @Column(name = "id_user", nullable = false)
    open var id: Int? = null

    @Lob
    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Lob
    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Lob
    @Column(name = "login", nullable = false)
    open var login: String? = null

    @Column(name = "phone", nullable = false)
    open var phone: Double? = null

    @Lob
    @Column(name = "address", nullable = false)
    open var address: String? = null
}
package co.edu.udistrital.esping.pgs.api.clients.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
open class User {
    @Id
    @Column(name = "id_user", nullable = false)
    open var id: Int? = null

}
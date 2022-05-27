package co.edu.udistrital.esping.pgs.api.pets.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fur_statuses")
open class FurStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fur_status", nullable = false)
    open var id: Int? = null

    //TODO [JPA Buddy] generate columns from DB
}
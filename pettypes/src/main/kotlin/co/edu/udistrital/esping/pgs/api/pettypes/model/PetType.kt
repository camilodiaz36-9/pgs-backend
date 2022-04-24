package co.edu.udistrital.esping.pgs.api.pettypes.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "pet_types")
open class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet_type", nullable = false)
    open var id: Int? = null

    @Lob
    @Column(name = "name", nullable = false)
    open var name: String? = null
}
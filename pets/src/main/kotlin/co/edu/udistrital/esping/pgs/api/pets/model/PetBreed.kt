package co.edu.udistrital.esping.pgs.api.pets.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pet_breeds")
open class PetBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet_breed", nullable = false)
    open var id: Int? = null

    //TODO [JPA Buddy] generate columns from DB
}
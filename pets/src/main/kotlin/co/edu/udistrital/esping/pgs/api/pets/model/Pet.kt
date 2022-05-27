package co.edu.udistrital.esping.pgs.api.pets.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.Table
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "pets")
open class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet", nullable = false)
    open var id: Int? = null

    @Lob
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "birth_date", nullable = false)
    open var birthDate: LocalDate? = null

    @Column(name = "gender", nullable = false)
    open var gender: Int? = null

    @Column(name = "is_sterilized", nullable = false)
    open var isSterilized: Int? = null

    @Column(name = "is_vaccined", nullable = false)
    open var isVaccined: Int? = null

    @Lob
    @Column(name = "weight", nullable = false)
    open var weight: String? = null

    @Lob
    @Column(name = "photo")
    open var photo: String? = null

    @Lob
    @Column(name = "additional_observations")
    open var additionalObservations: String? = null

    @Column(name = "last_visited", nullable = false)
    open var lastVisited: Instant? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    open var idUser: co.edu.udistrital.esping.pgs.api.pets.model.User? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pet_breed", nullable = false)
    open var idPetBreed: PetBreed? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_fur_status", nullable = false)
    open var idFurStatus: FurStatus? = null
}
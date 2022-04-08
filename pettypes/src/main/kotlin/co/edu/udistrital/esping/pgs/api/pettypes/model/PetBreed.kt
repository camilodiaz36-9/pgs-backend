package co.edu.udistrital.esping.pgs.api.pettypes.model

import javax.persistence.*

@Entity
@Table(name = "pet_breeds")
open class PetBreed {
    @Id
    @Column(name = "id_pet_breed", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet_type")
    open var idPetType: PetType? = null

    //TODO Reverse Engineering! Migrate other columns to the entity
}
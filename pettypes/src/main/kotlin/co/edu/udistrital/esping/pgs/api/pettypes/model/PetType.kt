package co.edu.udistrital.esping.pgs.api.pettypes.model

import javax.persistence.*

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

    @OneToMany(mappedBy = "idPetType")
    open var petBreeds: MutableSet<PetBreed> = mutableSetOf()
}
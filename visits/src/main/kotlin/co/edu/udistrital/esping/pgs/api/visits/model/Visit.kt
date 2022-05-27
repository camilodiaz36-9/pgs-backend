package co.edu.udistrital.esping.pgs.api.visits.model

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
import java.time.LocalTime

@Entity
@Table(name = "visits")
open class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visit", nullable = false)
    open var id: Int? = null

    @Column(name = "visit_date", nullable = false)
    open var visitDate: Instant? = null

    @Lob
    @Column(name = "initial_description", nullable = false)
    open var initialDescription: String? = null

    @Lob
    @Column(name = "final_description", nullable = false)
    open var finalDescription: String? = null

    @Column(name = "real_time_used")
    open var realTimeUsed: LocalTime? = null

    @Column(name = "total_amount", nullable = false)
    open var totalAmount: Int? = null

    @Lob
    @Column(name = "uuid", nullable = false)
    open var uuid: String? = null

    @Lob
    @Column(name = "photo", nullable = true)
    open var photo: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_service", nullable = false)
    open var idService: Service? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status", nullable = false)
    open var idStatus: Status? = null

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    open var idUser: User? = null

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pet", nullable = false)
    open var idPet: Pet? = null
}
package co.edu.udistrital.esping.pgs.api.services.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table
import java.time.LocalTime

@Entity
@Table(name = "services")
open class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", nullable = false)
    open var id: Int? = null

    @Lob
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "average_time", nullable = false)
    open var averageTime: LocalTime? = null

    @Column(name = "base_amount", nullable = false)
    open var baseAmount: Int? = null
}
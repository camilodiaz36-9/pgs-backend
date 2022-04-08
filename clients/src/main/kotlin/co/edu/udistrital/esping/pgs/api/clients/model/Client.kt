package co.edu.udistrital.esping.pgs.api.clients.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "clientes")
open class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cliente", nullable = false)
    open var id: Int? = null

    @Column(name = "tipo_documento", nullable = false)
    open var documentType: Int? = null

    @Lob
    @Column(name = "nombre_cliente", nullable = false)
    open var clientName: String? = null

    @Lob
    @Column(name = "direccion", nullable = false)
    open var address: String? = null

    @Lob
    @Column(name = "telefono", nullable = false)
    open var phone: String? = null

    @Column(name = "fecha_ultima_visita", nullable = false)
    open var lastVisited: LocalDateTime? = null

    @Column(name = "descuento", nullable = false)
    open var discount: Int? = null

    @Column(name = "saldo", nullable = false)
    open var pendingForPayment: Int? = null

    @Lob
    @Column(name = "ciudad", nullable = false)
    open var city: String? = null
}
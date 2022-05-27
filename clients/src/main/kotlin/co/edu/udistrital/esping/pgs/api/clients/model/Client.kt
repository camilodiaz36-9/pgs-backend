package co.edu.udistrital.esping.pgs.api.clients.model

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

@Entity
@Table(name = "clients")
open class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    open var id: Int? = null

    @Column(name = "client_number", nullable = false)
    open var clientNumber: Int? = null

    @Column(name = "document_type", nullable = false)
    open var documentType: Int? = null

    @Lob
    @Column(name = "client_name", nullable = false)
    open var clientName: String? = null

    @Column(name = "last_visited", nullable = false)
    open var lastVisited: Instant? = null

    @Column(name = "discount", nullable = false)
    open var discount: Int? = null

    @Column(name = "pending_payment", nullable = false)
    open var pendingPayment: Int? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    open var user: User? = null
}
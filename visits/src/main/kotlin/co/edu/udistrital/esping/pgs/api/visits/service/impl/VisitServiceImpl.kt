package co.edu.udistrital.esping.pgs.api.visits.service.impl

import co.edu.udistrital.esping.pgs.api.visits.dto.DataDto
import co.edu.udistrital.esping.pgs.api.visits.dto.PushyPushRequestDto
import co.edu.udistrital.esping.pgs.api.visits.dto.VisitDto
import co.edu.udistrital.esping.pgs.api.visits.model.Visit
import co.edu.udistrital.esping.pgs.api.visits.repository.NotificationRepository
import co.edu.udistrital.esping.pgs.api.visits.repository.PetRepository
import co.edu.udistrital.esping.pgs.api.visits.repository.StatusRepository
import co.edu.udistrital.esping.pgs.api.visits.repository.UserRepository
import co.edu.udistrital.esping.pgs.api.visits.repository.VisitRepository
import co.edu.udistrital.esping.pgs.api.visits.service.VisitService
import co.edu.udistrital.esping.pgs.api.visits.util.NotificationType
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class VisitServiceImpl: VisitService {

    @Autowired
    private lateinit var visitRepository: VisitRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var petRepository: PetRepository

    @Autowired
    private lateinit var statusRepository: StatusRepository

    @Value("\${visits.secretApiKey}")
    private lateinit var secretApiKey: String

    @Value("\${visits.baseURL}")
    private lateinit var baseURL: String

    @Value("\${visits.notifications.default.title}")
    private lateinit var defaultTitle: String

    @Value("\${visits.notifications.default.text}")
    private lateinit var defaultText: String

    @Value("\${visits.notifications.created.owner.title}")
    private lateinit var createdOwnerTitle: String

    @Value("\${visits.notifications.created.owner.text}")
    private lateinit var createdOwnerText: String

    @Value("\${visits.notifications.created.estheticist.title}")
    private lateinit var createdEstheticistTitle: String

    @Value("\${visits.notifications.created.estheticist.text}")
    private lateinit var createdEstheticistText: String

    @Value("\${visits.notifications.started.owner.title}")
    private lateinit var startedOwnerTitle: String

    @Value("\${visits.notifications.started.owner.text}")
    private lateinit var startedOwnerText: String

    @Value("\${visits.notifications.continued.owner.title}")
    private lateinit var continuedOwnerTitle: String

    @Value("\${visits.notifications.continued.owner.text}")
    private lateinit var continuedOwnerText: String

    @Value("\${visits.notifications.ended.owner.title}")
    private lateinit var endedOwnerTitle: String

    @Value("\${visits.notifications.ended.owner.text}")
    private lateinit var endedOwnerText: String


    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createVisit(visit: VisitDto): VisitDto {
        //Save visit
        val visitEntity = dtoToEntity(visit)
        val visitInserted = visitRepository.save(visitEntity)

        handleNotifications(visitInserted = visitInserted)

        return entityToDto(visitInserted)
    }

    override fun getVisitById(id: Int): VisitDto {
        val resultOpt = visitRepository.findById(id)
        val result = resultOpt.get()

        return entityToDto(result)
    }

    override fun updateVisit(visit: VisitDto): VisitDto {
        val entityToUpdate = dtoToEntity(visit)
        val result = visitRepository.save(entityToUpdate)

        handleNotifications(result)

        return entityToDto(result)
    }

    fun handleNotifications(visitInserted: Visit) {

        val statusOtp = visitInserted.idStatus?.id?.let { statusRepository.findById(it) }
        val status = statusOtp?.get()

        //Si el estado es 5, no se envía ninguna notificación a nadie
        if (status != null) {
            if(status.id != 5){
                val petOptional = visitInserted.idPet?.id?.let { petRepository.findById(it) }
                val pet = petOptional?.get()

                val petOwnerId = pet?.idUser
                val petOwnerOptional = petOwnerId?.id?.let { userRepository.findById(it) }
                val petOwner = petOwnerOptional?.get()

                //Notify pet owner
                val petOwnerToken = petOwner?.token

                var titleNotification: String = defaultTitle
                var textNotification: String = defaultText
                var typeNotification: Int = NotificationType.START_CLIENT.type
                var photo: String? = null

                //Cita agendada
                if (status != null) {
                    if(status.id == 1){
                        titleNotification = this.createdOwnerTitle
                        textNotification = this.createdOwnerText
                        typeNotification = NotificationType.START_CLIENT.type
                        photo = visitInserted.photo
                    }
                    if(status.id == 2){
                        titleNotification = this.startedOwnerTitle
                        textNotification = this.startedOwnerText
                        typeNotification = NotificationType.START_ESTHETICIST.type
                        photo = visitInserted.photo
                    }
                    if(status.id == 3){
                        titleNotification = this.continuedOwnerTitle
                        textNotification = this.continuedOwnerText
                        typeNotification = NotificationType.CONTINUE_SERVICE.type
                        photo = visitInserted.photo
                    }
                    if(status.id == 4){
                        titleNotification = this.endedOwnerTitle
                        textNotification = this.endedOwnerText
                        typeNotification = NotificationType.FINISH_SERVICES.type
                        photo = visitInserted.photo
                    }
                }

                val deviceTokens = arrayListOf<String>()
                if (petOwnerToken != null) {
                    deviceTokens.add(petOwnerToken)
                }

                val dataNotification = visitInserted.id?.let { idVisit ->
                    DataDto(title = titleNotification, message = textNotification,
                        type = typeNotification, destination = 0, visitId = idVisit, photo = photo
                    )
                }

                val pushyPushRequest = dataNotification?.let { data ->
                    PushyPushRequestDto(deviceTokens.toList(), data)
                }

                val result = pushyPushRequest?.let { request ->
                    NotificationRepository.dispatchNotification(baseURL, secretApiKey, request)
                }

                if(result in 200..299){
                    logger.debug("Notification dispatched to pet owner")
                } else {
                    logger.error("Error dispatching notification to pet owner. Response code: $result")
                }

                //Notify user esthetician only if status is 1
                if (status?.id == 1){
                    val esthetician = visitInserted.idUser
                    val estheticianOptional = esthetician?.id?.let { userRepository.findById(it) }
                    val estheticianUser = estheticianOptional?.get()

                    val estheticianToken = estheticianUser?.token
                    val titleNotificationEsthetician = this.createdEstheticistTitle
                    val textNotificationEsthetician = this.createdEstheticistText
                    val deviceTokensEsthetician = arrayListOf<String>()
                    if (estheticianToken != null) {
                        deviceTokensEsthetician.add(estheticianToken)
                    }

                    val typeNotificationEsthetician = NotificationType.START_CLIENT.type

                    val dataNotificationEsthetician = visitInserted.id?.let { idVisit ->
                        DataDto(title = titleNotificationEsthetician,
                            message = textNotificationEsthetician, type = typeNotificationEsthetician, destination = 1,
                            visitId = idVisit, photo = null
                        )
                    }

                    val pushyPushRequestEsthetician =
                        dataNotificationEsthetician?.let { data ->
                            PushyPushRequestDto(deviceTokensEsthetician, data)
                        }

                    val resultEsthetician =
                        pushyPushRequestEsthetician?.let { request ->
                            NotificationRepository.dispatchNotification(baseURL, secretApiKey, request)
                        }

                    if(resultEsthetician in 200..299){
                        logger.debug("Notification dispatched to esthetician")
                    } else {
                        logger.error("Error dispatching notification to esthetician. Response code: $result")
                    }
                }
            } else {
                logger.debug("No notification dispatched because visit has ended.")
            }
        }
    }

    fun entityToDto(entity: Visit): VisitDto {
        return modelMapperInstance().map(entity, VisitDto::class.java)
    }

    fun dtoToEntity(dto: VisitDto): Visit {
        return modelMapperInstance().map(dto, Visit::class.java)
    }

    @Bean
    fun modelMapperInstance(): ModelMapper {
        return ModelMapper()
    }
}
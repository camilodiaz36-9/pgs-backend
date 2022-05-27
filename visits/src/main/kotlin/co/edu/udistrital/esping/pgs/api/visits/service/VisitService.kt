package co.edu.udistrital.esping.pgs.api.visits.service

import co.edu.udistrital.esping.pgs.api.visits.dto.VisitDto

interface VisitService {
    fun createVisit(visit: VisitDto): VisitDto
    fun getVisitById(id: Int): VisitDto
    fun updateVisit(visit: VisitDto): VisitDto
}
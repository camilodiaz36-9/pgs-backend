package co.edu.udistrital.esping.pgs.api.visits.controller

import co.edu.udistrital.esping.pgs.api.visits.dto.VisitDto
import co.edu.udistrital.esping.pgs.api.visits.service.VisitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class VisitController {

    @Autowired
    private lateinit var visitService: VisitService

    @PostMapping("/visit")
    fun createVisit(@RequestBody visit: VisitDto): ResponseEntity<VisitDto> {
        val response = visitService.createVisit(visit)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/visit/{id}")
    fun getVisitById(@PathVariable(required = true) id: Int): ResponseEntity<VisitDto> {
        val response = visitService.getVisitById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/visit")
    fun updateVisit(@RequestBody visit: VisitDto): ResponseEntity<VisitDto> {
        val response = visitService.updateVisit(visit)
        return ResponseEntity(response, HttpStatus.OK)
    }

}
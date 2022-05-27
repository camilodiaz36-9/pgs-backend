package co.edu.udistrital.esping.pgs.api.visits.repository;

import co.edu.udistrital.esping.pgs.api.visits.model.Visit
import org.springframework.data.repository.CrudRepository

interface VisitRepository : CrudRepository<Visit, Int> {
}
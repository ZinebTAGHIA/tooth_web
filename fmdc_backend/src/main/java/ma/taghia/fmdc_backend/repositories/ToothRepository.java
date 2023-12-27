package ma.taghia.fmdc_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.taghia.fmdc_backend.entities.Tooth;

@Repository
public interface ToothRepository extends JpaRepository<Tooth, Integer> {
}

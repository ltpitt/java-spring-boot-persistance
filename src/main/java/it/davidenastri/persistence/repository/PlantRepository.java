package it.davidenastri.persistence.repository;

import it.davidenastri.persistence.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    List<Plant> findByPriceLessThan(BigDecimal price);

}

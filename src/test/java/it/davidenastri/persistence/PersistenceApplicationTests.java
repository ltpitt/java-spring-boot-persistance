package it.davidenastri.persistence;

import it.davidenastri.persistence.entity.Delivery;
import it.davidenastri.persistence.entity.Plant;
import it.davidenastri.persistence.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class PersistenceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	PlantRepository plantRepository;

	@Test
	public void testPriceLessThan() {
		// Test boundary conditions
		Plant plant = testEntityManager.persist(new Plant("Test Leaf", new BigDecimal(4.99)));
		testEntityManager.persist(new Plant("Test Weed", new BigDecimal(5.01)));

		List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
		Assertions.assertEquals(1, cheapPlants.size(), "Size");
		Assertions.assertEquals(plant.getId(), cheapPlants.get(0).getId(), "Id");
	}

	public void testDeliveryCompleted() {
		Plant plant = testEntityManager.persist(new Plant("Test Leaf", new BigDecimal(4.99)));
		Delivery delivery = testEntityManager.persist(new Delivery("A Guy", "An Address", LocalDateTime.now()));

		delivery.setPlants(Lists.newArrayList(plant));
		plant.setDelivery(delivery);

		// Test both before and after
		Assertions.assertFalse(plantRepository.deliveryCompleted(plant.getId()));
		delivery.setCompleted(true);
		Assertions.assertTrue(plantRepository.deliveryCompleted(plant.getId()));

	}

}

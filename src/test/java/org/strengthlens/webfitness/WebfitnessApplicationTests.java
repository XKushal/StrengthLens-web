package org.strengthlens.webfitness;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.docker.compose.enabled=false",
		"spring.datasource.url=jdbc:h2:mem:webfitness-test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class WebfitnessApplicationTests {

	@Test
	void contextLoads() {
	}

}

package org.strengthlens.webfitness;

import org.junit.jupiter.api.Test;
import org.strengthlens.webfitness.controller.AuthController;
import org.strengthlens.webfitness.security.SecurityConfig;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class SingleApplicationStructureTests {

	@Test
	void oauthControllerLivesUnderMainApplicationPackage() {
		assertThat(AuthController.class.getPackageName())
				.isEqualTo("org.strengthlens.webfitness.controller");
	}

	@Test
	void securityConfigDefinesSecurityFilterChain() {
		assertThat(SecurityConfig.class.getPackageName())
				.isEqualTo("org.strengthlens.webfitness.security");

		assertThat(SecurityConfig.class.getDeclaredMethods())
				.extracting(Method::getReturnType)
				.contains(SecurityFilterChain.class);
	}
}

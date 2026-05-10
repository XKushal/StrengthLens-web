package org.strengthlens.webfitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.strengthlens.webfitness.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

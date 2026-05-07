package org.strengthlens.webfitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.strengthlens.webfitness.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

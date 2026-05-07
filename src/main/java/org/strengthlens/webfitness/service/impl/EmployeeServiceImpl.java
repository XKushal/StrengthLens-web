package org.strengthlens.webfitness.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.strengthlens.webfitness.dto.EmployeeDTO;
import org.strengthlens.webfitness.entity.Employee;
import org.strengthlens.webfitness.exception.ResourceNotFoundException;
import org.strengthlens.webfitness.mapper.EmployeeMapper;
import org.strengthlens.webfitness.repository.EmployeeRepository;
import org.strengthlens.webfitness.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + employeeId + "not found"));
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updateEmployeeDto) {
        Employee employeeToUpdate = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + employeeId + "not found"));

        employeeToUpdate.setFirstName(updateEmployeeDto.getFirstName());
        employeeToUpdate.setLastName(updateEmployeeDto.getLastName());
        employeeToUpdate.setEmail(updateEmployeeDto.getEmail());
        Employee updatedEmployee =  employeeRepository.save(employeeToUpdate);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeId(Long employeeId) {
        Employee deletedEmployee =  employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + employeeId + "not found"));
        employeeRepository.delete(deletedEmployee);
    }


}

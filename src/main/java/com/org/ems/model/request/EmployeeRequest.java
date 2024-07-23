package com.org.ems.model.request;

import com.org.ems.util.validator.SalaryConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Employee name cannot be null or blank")
    private String name;

    @Min(value = 1, message = "Input valid departmentId, should be greater than zero")
    private int departmentId;

    @SalaryConstraint
    private double salary;
}
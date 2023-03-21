package com.startup.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotEmpty
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotEmpty
    @NotBlank
    @Size(min = 2)
    private String surname;
    @Min(value = 0)
    @Max(value = 150)
    private int age;
}

package com.project.shopapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank(message = "Catogery not null")
    private String name;
}

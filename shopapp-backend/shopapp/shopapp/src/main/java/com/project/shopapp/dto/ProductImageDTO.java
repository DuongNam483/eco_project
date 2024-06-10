package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class ProductImageDTO {

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product id phải lớn hơn 0")
    private Long productId;

    @JsonProperty("image_url")
    @Size(min = 5, max = 200, message = "Tên ảnh")
    private String imageUrl;

}

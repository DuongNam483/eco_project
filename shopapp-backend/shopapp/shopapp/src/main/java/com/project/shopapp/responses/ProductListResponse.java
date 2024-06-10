package com.project.shopapp.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductListResponse {
    private List<ProductResponse> productResponses;
    private int totalPages;
}

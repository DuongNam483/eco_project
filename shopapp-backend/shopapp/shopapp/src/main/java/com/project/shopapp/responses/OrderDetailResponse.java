package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail) {
        OrderDetailResponse orderDetailResponse = OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .price(orderDetail.getPrice())
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .totalMoney(orderDetail.getTotalMoney())
                .color(orderDetail.getColor())
                .numberOfProducts(orderDetail.getNumberOfProducts())
                .build();
        return orderDetailResponse;
    }
}


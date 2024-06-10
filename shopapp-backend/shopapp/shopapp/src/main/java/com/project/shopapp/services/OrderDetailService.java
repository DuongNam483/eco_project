package com.project.shopapp.services;

import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail creatOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;
    OrderDetail getOrderDetailById(Long id) throws DataNotFound;
    List<OrderDetail> findByOrderId(Long orderId);
    OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFound;
    void deleteOrderDetail(long id);
}

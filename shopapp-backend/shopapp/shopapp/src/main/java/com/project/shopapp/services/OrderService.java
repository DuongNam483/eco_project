package com.project.shopapp.services;

import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    Order creatOrder(OrderDTO orderDTO) throws Exception;
    Order getOrderById(Long id) throws DataNotFound;
    Order updateOrder(Long categoryId, OrderDTO orderDTO) throws DataNotFound, Exception;
    void deleteOrder(long id);
    List<Order> findByUserId(long userId);
}

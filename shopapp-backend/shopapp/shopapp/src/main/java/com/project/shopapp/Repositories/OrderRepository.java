package com.project.shopapp.Repositories;

import com.project.shopapp.models.Category;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}

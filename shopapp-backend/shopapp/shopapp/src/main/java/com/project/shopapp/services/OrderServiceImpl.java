package com.project.shopapp.services;

import com.project.shopapp.Repositories.OrderRepository;
import com.project.shopapp.Repositories.UserRepository;
import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderStatus;
import com.project.shopapp.models.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order creatOrder(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFound("Không tìm thấy người dùng"));

        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));

        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if(shippingDate == null || shippingDate.isBefore(LocalDate.now()) ){
            throw new DataNotFound("Ngày phải là ngày hôm nay");
        }
        order.setActive(true);
        order.setShippingDate(shippingDate);
        orderRepository.save(order);

        return order;
    }

    @Override
    public Order getOrderById(Long id) throws DataNotFound {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Order không tồn tại"));
        return order;
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Không tồn tại order"));
        User existingUser = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFound("Không tồn tại user"));

        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));

        modelMapper.map(orderDTO, order);
        order.setUser(existingUser);

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null){
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return orderRepository.findByUserId(userId);
    }
}

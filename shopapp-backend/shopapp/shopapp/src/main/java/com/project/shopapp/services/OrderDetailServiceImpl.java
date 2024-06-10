package com.project.shopapp.services;

import com.project.shopapp.Repositories.OrderDetailRepository;
import com.project.shopapp.Repositories.OrderRepository;
import com.project.shopapp.Repositories.ProductRepository;
import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.responses.OrderDetailResponse;
import jakarta.persistence.MappedSuperclass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDetail creatOrderDetail(OrderDetailDTO orderDetailDTO)  throws Exception{
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(()  -> new DataNotFound("Không có order"));
        Product product = productRepository.findById(orderDetailDTO.getProductId())
        .orElseThrow(() -> new DataNotFound("Sản phẩm không tồn tại"));
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .price(orderDetailDTO.getPrice())
                .build();
        return orderDetailRepository.save(orderDetail);

    }

    @Override
    public OrderDetail getOrderDetailById(Long id) throws DataNotFound {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Không tìm thấy orderdetail"));
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFound {

        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() ->new DataNotFound("Không tìm thấy orderdetail với id = " + id));

        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() ->new DataNotFound("Không tìm thấy order với id = " + orderDetailDTO.getOrderId()));

        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() ->new DataNotFound("Không tìm thấy product với id = " + orderDetailDTO.getProductId()));

        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetail(long id) {
        orderDetailRepository.deleteById(id);
    }
}

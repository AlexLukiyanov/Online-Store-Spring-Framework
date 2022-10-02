package services;

import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductsService;
import dto.OrderDto;
import entities.Order;
import entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductsService productsService;
    private final OrderRepository orderRepository;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void saveOrder(OrderDto orderDto){
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setUsername(orderDto.getUsername());
        order.setTotalPrice(orderDto.getTotalPrice());
        List<OrderItem> items = orderDto.getItemDtoList().stream()
                .map(o -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(o.getQuantity());
                    orderItem.setPricePerProduct(o.getPricePerProduct());
                    orderItem.setPrice(o.getPrice());
                    orderItem.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
    }

    public void save(Product sumeProduct){
        productsService.save(sumeProduct);
    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}

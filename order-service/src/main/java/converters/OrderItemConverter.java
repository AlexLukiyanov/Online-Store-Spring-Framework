package converters;

import dto.OrderItemDto;
import entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}

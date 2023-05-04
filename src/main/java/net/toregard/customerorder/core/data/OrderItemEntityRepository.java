package net.toregard.customerorder.core.data;

import net.toregard.customerorder.command.rest.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, String> {
   OrderItem findByOrderItemId(String orderItemId);
   List<OrderItemEntity> findOrderItemsByOrderId(String orderId);
}

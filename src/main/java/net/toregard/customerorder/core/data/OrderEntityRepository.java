package net.toregard.customerorder.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findByOrderId(String orderId);
    List<OrderEntity> findOrderEntitiesByCustomerId(String customerId);
}

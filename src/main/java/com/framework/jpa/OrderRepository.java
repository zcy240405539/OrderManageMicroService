package com.framework.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.framework.jpa.OrdersEntity;

public interface OrderRepository extends JpaRepository<OrdersEntity, String> {
	 @Query("SELECT o FROM OrdersEntity o WHERE o.orderid=?1")
	 public List<OrdersEntity> findByOrder(String orderid);
	 @Query("SELECT o FROM OrdersEntity o WHERE o.userid=?1")
	 public List<OrdersEntity> findByUser(String userid);
}

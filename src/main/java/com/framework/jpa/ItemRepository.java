package com.framework.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.framework.jpa.ItemsEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemsEntity, String> {
	 @Query("SELECT i FROM ItemsEntity i WHERE i.orderid=?1 and i.productid=?2")
	 public List<ItemsEntity> findByOrderAndProduct(String orderid,String productid);
	 @Query("SELECT i FROM ItemsEntity i WHERE i.orderid=?1")
	 public List<ItemsEntity> findByOrder(String orderid);
}

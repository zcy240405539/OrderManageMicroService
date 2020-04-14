package com.framework.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.framework.jpa.*;

@Component
public class OrderBean {

	@Autowired
	public OrderRepository orderRepository;
	public OrdersEntity order;
	@Autowired
	public ItemRepository itemRepository;
	public ItemsEntity item;
	
	public ProductsEntityDTO checkProduct(String productid) {
		RestTemplate getTest = new RestTemplate();
		ProductsEntityDTO product = getTest.getForObject("http://localhost:22222/checkProduct/"+productid, ProductsEntityDTO.class);
		return product;
	}
	
	public ItemsEntityDTO addItem(String orderid,String productid,String quantity) {
		ProductsEntityDTO product = checkProduct(productid);
		ItemsEntityDTO item = new ItemsEntityDTO(orderid, product, quantity);
		return item;		
	}
	
	public List<ItemsEntityDTO> findItemByOrder(String orderid) {
		List<ItemsEntity> items = itemRepository.findByOrder(orderid);
		List<ItemsEntityDTO> itemsEntityDTOs = new ArrayList<ItemsEntityDTO>();
		if(items.size()>0) {
			for(ItemsEntity item: items) {
				ProductsEntityDTO product = checkProduct(item.getProductid());
				ItemsEntityDTO itemsEntityDTO = new ItemsEntityDTO(item.getOrderid(), product, item.getQuantity());
				itemsEntityDTOs.add(itemsEntityDTO);
			}
		}
		
		return itemsEntityDTOs;
	}
	
	
	public OrdersEntityDTO checkOrderById(String orderid) {
		List<OrdersEntity> orders = orderRepository.findByOrder(orderid);
		OrdersEntity orderEntity = new OrdersEntity();
		OrdersEntityDTO order = new OrdersEntityDTO();
		
		if(orders.size()>0) {
			orderEntity = orders.get(0);
			List<ItemsEntityDTO> item = findItemByOrder(orderid);
			order = new OrdersEntityDTO(orderEntity.getOrderid(), orderEntity.getUserid(), orderEntity.getTotalPrice(), orderEntity.getOrderTime(), orderEntity.getIp(), item);
		}
		return order;
	}
	
	public List<OrdersEntityDTO> checkOrdersByUser(String userid){
		List<OrdersEntity> orders = orderRepository.findByUser(userid);
		List<OrdersEntityDTO> ordersEntityDTOs = new ArrayList<OrdersEntityDTO>();	
		
		if(orders.size()>0) {
			for(OrdersEntity order:orders) {
				List<ItemsEntityDTO> item = findItemByOrder(order.getOrderid());
				OrdersEntityDTO ordersEntityDTO = new OrdersEntityDTO(order.getOrderid(),order.getUserid(), order.getTotalPrice(), order.getOrderTime(), order.getIp(), item);
				ordersEntityDTOs.add(ordersEntityDTO);
			}
			
		}		
		return ordersEntityDTOs;
			
	}
	
	public String newOrder(String orderid, String userid, String totalPrice, String orderTime, String ip, List<ItemsEntityDTO> items) {
		try {
			OrdersEntity ordersEntity = new OrdersEntity(orderid, userid, totalPrice, orderTime, ip);
			orderRepository.save(ordersEntity);
			
			for(ItemsEntityDTO item:items) {
				String pid = item.getProduct().getPid();
				String itemid = orderid+pid;
				ItemsEntity itemEntity = new ItemsEntity(itemid, item.getOrderid(), pid, item.getQuantity());
				itemRepository.save(itemEntity);
			}
			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}

package com.framework.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.framework.business.*;
import com.framework.jpa.*;

@RestController
public class OrderRestMicroService {
	@Autowired
	OrderBean orderBean;
	
	@GetMapping(value="/checkOrderById/{orderid}")
	public OrdersEntityDTO checkOrderById(@PathVariable String orderid) {
		OrdersEntityDTO ordersEntityDTO = orderBean.checkOrderById(orderid);
		return ordersEntityDTO;
	}
	
	@GetMapping(value="/checkOrderByUser/{userid}")
	public List<OrdersEntityDTO> checkOrdersByUser(@PathVariable String userid) {
		List<OrdersEntityDTO> ordersEntityDTOs = new ArrayList<OrdersEntityDTO>();
		ordersEntityDTOs = orderBean.checkOrdersByUser(userid);
		return ordersEntityDTOs;
	}	
	
	@PostMapping(value="/checkOut")
	public String checkOut(@RequestBody OrdersEntityDTO order) {
		String orderid = order.getOrderid();
		String userid = order.getUserid();
		String totalPrice = order.getTotalPrice();
		String orderTime = order.getOrderTime();
		String ip = order.getIp(); 
		List<ItemsEntityDTO> items = order.getItem();
		
		System.out.println("Order ID: "+orderid);
		System.out.println("User ID: "+userid);
		System.out.println("Total Price: "+totalPrice);
		System.out.println("Order Time: "+orderTime);
		System.out.println("IP: "+ip);
		
		String result = orderBean.newOrder(order.getOrderid(), order.getUserid(), order.getTotalPrice(), order.getOrderTime(), order.getIp(), order.getItem());
		
		if(result.equals("success")) {
			return "success";
		}else {
			return "error";
		}
		
	}

}

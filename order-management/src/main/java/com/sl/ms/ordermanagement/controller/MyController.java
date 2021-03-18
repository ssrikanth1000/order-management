package com.sl.ms.ordermanagement.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sl.ms.ordermanagement.entity.Orders;
import com.sl.ms.ordermanagement.serviceimpl.OrderServiceImpl;

@RestController
public class MyController {
	private static final Logger LOGGER = LogManager.getLogger(MyController.class);
	@Autowired
	OrderServiceImpl orderServiceImpl;
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/orders/getHystrix")
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	@Cacheable(cacheNames="getHystrixOrders")
	public String getHystrixOrders() {
		LOGGER.info("entery into orders");
		URI uri = URI.create("http://localhost:8888/products/getHystrix");
		String response = this.restTemplate.getForObject(uri, String.class);
		System.out.println("Response Received as " + response + " -  " + new Date());
		return response;
	}
	
	@GetMapping("/orders")
	public List<Orders> orders() {
		LOGGER.info("entery into orders");
		return orderServiceImpl.listOfOrders();
	}

	@GetMapping("/orders/{orderId}")
	public Orders getOrders(@PathVariable String orderId) {
		return orderServiceImpl.getOrders(Integer.parseInt(orderId));
	}

	@PostMapping(path = "/orders", consumes = "application/json")
	public Orders addOrders(@RequestBody Orders orders) {
		return orderServiceImpl.addItems(orders);
	}

	@DeleteMapping(path = "/orders/{order_id}")
	public String deleteOrder(@PathVariable(name = "order_id") int orderid) {
		orderServiceImpl.deleteOrder(orderid);
		return "deleted order successfully";
	}

	public String getDataFallBack() {
		return "Sir,Server not reachable";
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

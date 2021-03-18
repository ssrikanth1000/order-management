package com.sl.ms.ordermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermanagement.dao.OrderItemDao;
import com.sl.ms.ordermanagement.entity.Orders;
import com.sl.ms.ordermanagement.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderItemDao orderItemDao;
	@Override
	public List<Orders> listOfOrders() {
		List<Orders> list =orderItemDao.findAll();
		System.out.println("list,,.."+list);
		return list;
	}
	@Override
	public Orders addItems(Orders order) {
		orderItemDao.save(order);
		return order;
		
	}
	@Override
	public Orders getOrders(int order) {
		return orderItemDao.getOne(order);
	}
	@Override
	public void deleteOrder(int orderid) {
		Orders order= orderItemDao.getOne(orderid);
		orderItemDao.delete(order);
		
	}

}

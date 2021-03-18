/**
 * 
 */
package com.sl.ms.ordermanagement.service;

import java.util.List;

import com.sl.ms.ordermanagement.entity.Orders;

/**
 * @author Srikanth
 *
 */
public interface OrderService {
	public List<Orders> listOfOrders();
	public Orders addItems(Orders order);
	public Orders getOrders(int order);
	void deleteOrder(int orderid);

}

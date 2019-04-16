package ivg.cn.sharding.jdbc.springboot.example.service;

import java.util.List;

import ivg.cn.sharding.jdbc.springboot.example.entity.Order;

public interface OrderService {

	int batchInser(List<Order> orders);
	
}

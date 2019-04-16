package ivg.cn.sharding.jdbc.springboot.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ivg.cn.sharding.jdbc.springboot.example.entity.Order;
import ivg.cn.sharding.jdbc.springboot.example.mapper.OrderMapper;
import ivg.cn.sharding.jdbc.springboot.example.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderMapper orderMapper;
	
	@Transactional
	@Override
	public int batchInser(List<Order> orders) {
		
		int count = orderMapper.insertList(orders);
		
		return count;
	}

}

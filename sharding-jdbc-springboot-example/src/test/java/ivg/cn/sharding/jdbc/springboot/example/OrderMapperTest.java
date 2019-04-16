package ivg.cn.sharding.jdbc.springboot.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ivg.cn.sharding.jdbc.springboot.example.entity.Order;
import ivg.cn.sharding.jdbc.springboot.example.mapper.OrderMapper;
import ivg.cn.sharding.jdbc.springboot.example.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Test
	public void testInsert() {
		
		List<Order> orders = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Order order = new Order();
			order.setOrder_id(i);
			order.setUser_id(i);
			order.setStatus("Normal");
			order.setEpc("EH"+i+"");
			orders.add(order);
			orderMapper.insert(order);
		}
	}
	
	
	
}

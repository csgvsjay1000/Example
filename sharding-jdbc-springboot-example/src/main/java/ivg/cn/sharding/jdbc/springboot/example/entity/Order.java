package ivg.cn.sharding.jdbc.springboot.example.entity;

import javax.persistence.Table;

@Table(name="t_order")
public class Order {
	private Integer user_id;
	
	private Integer order_id;
	
	private String epc;
	
	private String status;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEpc() {
		return epc;
	}

	public void setEpc(String epc) {
		this.epc = epc;
	}
	
}

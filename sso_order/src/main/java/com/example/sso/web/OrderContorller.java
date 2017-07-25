package com.example.sso.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sso.bean.Order;
import com.example.sso.bean.ResponseBean;
import com.example.sso.login.annotation.SSOAnnotation;

@RestController
public class OrderContorller {

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("order")
	@SSOAnnotation
	public ResponseBean getAllOrder(){
		ResponseBean bean=new ResponseBean();
		
		List<Order> list=new ArrayList<>();
		for (int i = 0; i < 50; i++) {
		list.add(new Order("oid"+i, "saleid"+i, "address"+i));
		}
		
		bean.setMsg("success");
		bean.setObj(list);
		bean.setCode("success");
		return bean;
		
	};
}

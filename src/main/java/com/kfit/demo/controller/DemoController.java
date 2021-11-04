package com.kfit.demo.controller;

import java.util.List;

import com.kfit.demo.bean.*;
import com.kfit.demo.service.SpzlService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoController {

	@Autowired
	private SpzlService spzlService;


	@RequestMapping("/getspbnew")
	public List<Spbnew> getspbnew() {
		return spzlService.getspbnew();
	}

	@RequestMapping("/getspbnewbyid")
	public Spbnew getspbnewbyid(@RequestParam("goods_id") @NotEmpty(message = "商品ID为空") String goods_id) {
		return spzlService.getspbnewById(goods_id);
	}

	@RequestMapping("/saveOrder")
	public String saveOrder(@RequestParam("orderDetail") @NotEmpty(message = "订单信息为空") String orderDetail) {
       return spzlService.saveOrder(orderDetail);
	}


}


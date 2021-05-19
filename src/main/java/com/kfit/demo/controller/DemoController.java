package com.kfit.demo.controller;

import java.util.List;

import com.kfit.demo.bean.*;
import com.kfit.demo.service.SpzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoController {

	@Autowired
	private SpzlService spzlService;


	@RequestMapping("/getspbnew")
	public List<Spbnew> getspbnew() {
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.getspbnew();
	}

	@RequestMapping(value ="/GetCustomerByTaxNo", method = RequestMethod.GET)
	public List<Custom> GetCustomerByTaxNo(@RequestParam String taxnumber) {
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.GetCustomerByTaxNo(taxnumber);
	}

}


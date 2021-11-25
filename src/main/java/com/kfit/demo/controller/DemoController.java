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
		return spzlService.getspbnew();
	}

	@RequestMapping(value ="/GetKCByIDNEWX", method = RequestMethod.GET)
	public List<Spbnew> GetKCByIDNEWX(@RequestParam String id) {
		return spzlService.GetKCByIDNEWX(id);
	}

	@RequestMapping(value ="/GetCustomerByTaxNo", method = RequestMethod.GET)
	public List<Custom> GetCustomerByTaxNo(@RequestParam String taxnumber) {
		return spzlService.GetCustomerByTaxNo(taxnumber);
	}

	@RequestMapping(value ="/SaveOrder", method = RequestMethod.POST)
	public String SaveOrder(@RequestParam String orderDetail) {
		return spzlService.SaveOrder(orderDetail);
	}

	@RequestMapping(value ="/GetCustomersCredit", method = RequestMethod.GET)
	public List<Custom> GetCustomersCredit() {
		return spzlService.GetCustomersCredit();
	}

}


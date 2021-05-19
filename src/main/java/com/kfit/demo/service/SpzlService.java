package com.kfit.demo.service;

import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;

	public List<Spbnew> getspbnew() {
		return spzlMappper.getspbnew();
	}
	public List<Custom> GetCustomerByTaxNo(String taxnumber) {
		return spzlMappper.GetCustomerByTaxNo(taxnumber);
	}

}

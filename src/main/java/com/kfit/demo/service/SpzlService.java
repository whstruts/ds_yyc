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
    public void intsertHZ(DDHZ ddhz){
		spzlMappper.insertHZ(ddhz);
	}
	public void intsertMX(DDMX ddmx){
		spzlMappper.insertMX(ddmx);
	}
}

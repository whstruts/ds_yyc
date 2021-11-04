package com.kfit.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;

	public List<Spbnew> getspbnew() {
		return spzlMappper.getspbnew();
	}
	public Spbnew getspbnewById(String id) {
		return spzlMappper.getspbnewById(id);
	}
	public Custom getCustomerByTaxNo(String taxNo) {
		return spzlMappper.getCustomerByTaxNo(taxNo);
	}
	@Transactional(rollbackFor = Exception.class)
	public String saveOrder(String order){
		OrderDetails orderDetails = JSONObject.parseObject(order,OrderDetails.class);
		double hj = 0;
		int index = 1;
		for (OrderDetail orderDetail:orderDetails.getData()) {
		   DDMX ddmx = new DDMX();
		   ddmx.setAPP_DD_ID(orderDetails.getOrder_id());
		   ddmx.setERP_SP_DJ(Double.parseDouble(orderDetail.getDj()));
		   ddmx.setERP_SP_SL(Integer.parseInt(orderDetail.getSL()));
           ddmx.setERP_SP_ID(orderDetail.getGoods_id_s());
           ddmx.setMX_ID(String.valueOf(index));
           intsertMX(ddmx);
           hj = hj+ddmx.getERP_SP_DJ()*ddmx.getERP_SP_SL();
           index++;
		}
		DDHZ ddhz = new DDHZ();
		ddhz.setAPP_DD_ID(orderDetails.getOrder_id());
		ddhz.setCreate_Time(new Date());
		ddhz.setERP_Custom_ID(orderDetails.getErpCustomerID());
		ddhz.setDD_HJ(hj);
		intsertHZ(ddhz);
		return String.valueOf(hj);
	}

    public void intsertHZ(DDHZ ddhz){
		spzlMappper.insertHZ(ddhz);
	}
	public void intsertMX(DDMX ddmx){
		spzlMappper.insertMX(ddmx);
	}
}

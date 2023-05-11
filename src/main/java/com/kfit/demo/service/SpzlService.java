package com.kfit.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;

	public List<Spbnew> getspbnew() {
		List<Spbnew> list = spzlMappper.getspbnew();
		list.addAll(spzlMappper.getspbnewst());
//		List<Spbnew> list = spzlMappper.getspbnewst();
		return list;
	}
	public Spbnew getspbnewById(String id) {
		if (spzlMappper.getspbnewById(id)==null)
			return spzlMappper.getspbnewstById(id);
		else
			return spzlMappper.getspbnewById(id);

	}
	public Custom getCustomerByTaxNo(String taxNo) {
		return spzlMappper.getCustomerByTaxNo(taxNo);
	}
	public void UpdateOrderStatusByID(String id) {
		spzlMappper.UpdateOrderStatusByID(id);
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
		ddhz.setDjbh(String.valueOf(getOrderNo()));
		intsertHZ(ddhz);
		return "订单保存成功:总金额 " + String.valueOf(hj) + " 元";
	}

	private int getOrderNo()
	{
		Random random = new Random();
		return random.nextInt(500000000) + 400000000;
	}

    public void intsertHZ(DDHZ ddhz){
		spzlMappper.insertHZ(ddhz);
	}
	public void intsertMX(DDMX ddmx){
		spzlMappper.insertMX(ddmx);
	}
}

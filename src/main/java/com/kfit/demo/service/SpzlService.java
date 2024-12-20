package com.kfit.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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


	@Transactional(rollbackFor = Exception.class)
	public String saveOrderH5(String order){
		OrderDetails orderDetails = JSONObject.parseObject(order,OrderDetails.class);
		double hj = 0;
		int index = 1;
		for (OrderDetail orderDetail:orderDetails.getData()) {
			DDMX ddmx = new DDMX();
			ddmx.setAPP_DD_ID(orderDetails.getOrder_code());
			ddmx.setERP_SP_DJ(Double.parseDouble(orderDetail.getDj()));
			ddmx.setERP_SP_SL(Integer.parseInt(orderDetail.getSL()));
			ddmx.setERP_SP_ID(orderDetail.getGoods_id_s());
			ddmx.setMX_ID(String.valueOf(index));
			intsertMXH5(ddmx);
			hj = hj+ddmx.getERP_SP_DJ()*ddmx.getERP_SP_SL();
			index++;
		}
		DDHZH5 ddhz = new DDHZH5();
		Date currentTime = new Date(); // 获取当前时间
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd"); // 定义日期格式
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss"); // 定义日期格式
		ddhz.setRq(d.format(currentTime));
		ddhz.setOntime(t.format(currentTime));
		ddhz.setAPP_DD_ID(orderDetails.getOrder_code());
		//ddhz.setCreate_Time(new Date());
		ddhz.setERP_Custom_ID(orderDetails.getErpCustomerID());
		ddhz.setDD_HJ(hj);
		ddhz.setDjbh(orderDetails.getOrder_id());
		intsertHZH5(ddhz);
		return "订单保存成功:总金额 " + String.valueOf(hj) + " 元";
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrderYSB(String order){
		System.out.println(order);
		OrderDetails orderDetails = JSONObject.parseObject(order,OrderDetails.class);
		double hj = 0;
		int index = 1;
		for (OrderDetail orderDetail:orderDetails.getData()) {
			DDMX ddmx = new DDMX();
			ddmx.setAPP_DD_ID(orderDetails.getOrder_id());
			ddmx.setERP_SP_DJ(Double.parseDouble(orderDetail.getDj()));
			ddmx.setERP_SP_SL(Integer.parseInt(orderDetail.getSL()));
			ddmx.setERP_SP_ID(orderDetail.getGoods_id_s());
			if(orderDetail.getGoods_id_s().length()>30) {
				ddmx.setERP_SP_CODE(spzlMappper.getDrugCodeById(orderDetail.getGoods_id_s()));
			}else
			{
				ddmx.setERP_SP_CODE(orderDetail.getGoods_id_s());
			}
			ddmx.setMX_ID(String.valueOf(index));
			intsertMXYSB(ddmx);
			hj = hj + ddmx.getERP_SP_DJ() * ddmx.getERP_SP_SL();
			index++;
		}
		DDHZH5 ddhz = new DDHZH5();
		Date currentTime = new Date(); // 获取当前时间
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd"); // 定义日期格式
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss"); // 定义日期格式
		ddhz.setRq(d.format(currentTime));
		ddhz.setOntime(t.format(currentTime));
		ddhz.setAPP_DD_ID(orderDetails.getOrder_id());
		ddhz.setERP_Custom_ID(orderDetails.getErpCustomerID());
		ddhz.setDD_HJ(hj);
		ddhz.setDjbh(orderDetails.getOrder_id());
		intsertHZYSB(ddhz);
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

	public void intsertHZH5(DDHZH5 ddhz){
		spzlMappper.insertHZH5(ddhz);
	}
	public void intsertMXH5(DDMX ddmx){
		spzlMappper.insertMXH5(ddmx);
	}
	public void intsertHZYSB(DDHZH5 ddhz){
		spzlMappper.insertHZYSB(ddhz);
	}
	public void intsertMXYSB(DDMX ddmx){
		spzlMappper.insertMXYSB(ddmx);
	}
	@Transactional(rollbackFor = Exception.class)
	public int updateThirdOrder(String orderId,int status) {
		if (status == 3) {
			spzlMappper.insertYSBHZ(orderId);
			spzlMappper.insertYSBMX(orderId);
		}
		return 0;
	}
}

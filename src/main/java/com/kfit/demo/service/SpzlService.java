package com.kfit.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;

	public List<Spbnew> getspbnew() {
		return spzlMappper.getspbnew();
	}

	public List<Spbnew> GetKCByIDNEWX(String id) {
		return spzlMappper.GetKCByIDNEWX(id);
	}

	public List<Custom> GetCustomerByTaxNo(String taxnumber) {
		return spzlMappper.GetCustomerByTaxNo(taxnumber);
	}

	public String SaveOrder(String orderDetail){
		OrderDetails orderDetails = JSON.parseObject(orderDetail,OrderDetails.class);
		BigDecimal total = new BigDecimal(0);
		for (OrderDetail order : orderDetails.getData()) {
			total = total.add(new BigDecimal(order.getDj()).multiply(new BigDecimal(order.getSL())));
			String[] goods_id = order.getGoods_id_s().split("_");
			order.setGoods_id_s(goods_id[0]);
			order.setMakeNo(goods_id[1]);
			order.setSalesVolume(order.getSL());
			order.setSuppliersPrice(order.getDj());
			order.setAppPrice(order.getDj());
			order.setOrderId(orderDetails.getOrder_id());
			spzlMappper.SaveThirdOrderMX(order);
		}
        orderDetails.setIsPay(true);
		orderDetails.setCountQty(orderDetails.getData().size());
		orderDetails.setTotal(Double.valueOf(total.toString()));

		spzlMappper.SaveThirdOrder(orderDetails);
		return "订单保存成功:总金额 " + orderDetails.getB2b_prom_ordertotal() + " 元";
	}

	public List<Custom> GetCustomersCredit() {
		return spzlMappper.GetCustomersCredit();
	}

	public int updateThirdOrder(String orderId,int status){
		if(status==3)
		  return spzlMappper.updateThirdOrderPay(orderId);
		else if(status==10)
			return spzlMappper.updateThirdOrderCancel(orderId);
		else
			return 0;
	}

}

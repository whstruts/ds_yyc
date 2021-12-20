package com.kfit.demo.bean;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {
    String order_id;
    List<OrderDetail> data;
    String erpCustomerID;
    int countQty;
    Double Total;
    /**
     * 活动ID
     */
    String b2b_prom_id;
    /**
     * 活动名称
     */
    String b2b_prom_name;
    /**
     * 活动类型
     */
    String b2b_prom_type;
    /**
     * 优惠券ID
     */
    String b2b_prom_copuno;
    /**
     * 优惠总金额
     */
    String b2b_prom_total;
    /**
     * 赠品
     */
    String b2b_prom_zp;
    /**
     * 微信支付金额
     */
    String b2b_prom_wx;
    /**
     * 支付宝支付金额
     */
    String b2b_prom_zfb;
    /**
     * 账期支付金额
     */
    String b2b_prom_acount;
    /**
     * 余额支付金额
     */
    String b2b_prom_yezf;
    /**
     * 实付金额
     */
    String b2b_prom_sjtotal;
    /**
     * 订单金额
     */
    String b2b_prom_ordertotal;
    /**
     * 订单备注
     */
    String Remarks;
    /**
     * 是否支付 1 未支付  2 已支付
     */
    String isPay;

    /**
     * 线下支付金额  APP中 支付方式为 线下支付 2 扫码支付 4
     */
    String b2b_offline_pay;
}

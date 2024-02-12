package com.kfit.demo.bean;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {
    String order_id;
    String order_code;
    List<OrderDetail> data;
    String erpCustomerID;
}

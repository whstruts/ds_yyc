package com.kfit.demo.bean;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {
    String order_id;
    List<OrderDetail> data;
    String erpCustomerID;
}

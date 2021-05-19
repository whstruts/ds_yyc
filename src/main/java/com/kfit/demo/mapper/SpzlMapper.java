package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SpzlMapper {

	@Select("select suppliers_name as suppliers_name,ypdm as ypdm,cddm as cddm,jx as jx,CONVERT(varchar(100), scrq, 23) as scrq," +
			"goods_id_s as goods_id_s,goods_sn as goods_sn,drug_common_name as drug_common_name,manufacturer as manufacturer," +
			"approve_numer as approve_number,drug_img as drug_img,specifications as specifications,package_unit as package_unit," +
			"medium_package as medium_package,large_package as large_package,'1' as is_retail," +
			"CONVERT(varchar(100), production_batch, 23) as production_batch,CONVERT(varchar(100), date_expiration, 23) as date_expiretion," +
			"repertory as repertory,bar_code as bar_code,suppliers_price as supplier_price,drugid as drugid," +
			"is_on_sale as is_on_sale,category as category from v_ware_b2b_tyt")
	public List<Spbnew> getspbnew();

	@Select("select erpCustomerID AS code,erpCustomerName AS name,custom AS type,taxnumber,categories from v_custom_b2b_tyt where taxnumber = #{taxnumber}")
	public List<Custom> GetCustomerByTaxNo(String taxnumber);

}

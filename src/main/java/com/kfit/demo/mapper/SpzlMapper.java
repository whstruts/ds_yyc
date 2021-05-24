package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SpzlMapper {

	@Select("select '' AS suppliers_name,PM_ZJM as ypdm,a.cd as cddm,jx,CONVERT(varchar(100),a.SCRQ,23) as scrq,"+
	        "a.guid as goods_id_s,a.hh as goods_sn,pm as drug_common_name,a.scdw as manufacturer,a.PZWH as approve_number,"+
			"'' as drug_img,GG as specifications,PDW as package_unit,ZBZL as medium_package,a.mjl as large_package,'1' as is_retail,"+
	        "PH as production_batch,CONVERT(varchar(100),a.XQ,23) as date_expiretion,a.sl as repertory,b.tm as bar_code,PFJ as suppliers_price,"+
	        "a.guid as drugid,'1' as is_on_sale,GSPSortID as category from phk a,yw_kck b where a.hh = b.hh and a.sl > 0")
	public List<Spbnew> getspbnew();

	@Select("select '同云堂' as suppliers_name,ypdm as ypdm,cddm as cddm,jx as jx,CONVERT(varchar(100), scrq, 23) as scrq," +
			"goods_id_s+'_'+production_batch as goods_id_s,goods_sn as goods_sn,drug_common_name as drug_common_name,manufacturer as manufacturer," +
			"approve_numer as approve_number,drug_img as drug_img,specifications as specifications,package_unit as package_unit," +
			"medium_package as medium_package,large_package as large_package,'1' as is_retail," +
			"CONVERT(varchar(100), production_batch, 23) as production_batch,CONVERT(varchar(100), date_expiration, 23) as date_expiretion," +
			"repertory as repertory,bar_code as bar_code,suppliers_price as supplier_price,drugid as drugid," +
			"is_on_sale as is_on_sale,category as category from v_ware_b2b_tyt where goods_id_s+'_'+production_batch = #{id}")
	public List<Spbnew> GetKCByIDNEWX(String id);

	@Select("select erpCustomerID AS code,erpCustomerName AS name,custom AS type,taxnumber,categories from v_custom_b2b_tyt where taxnumber = #{taxnumber}")
	public List<Custom> GetCustomerByTaxNo(String taxnumber);

}

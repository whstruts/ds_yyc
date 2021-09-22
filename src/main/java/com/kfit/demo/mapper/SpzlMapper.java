package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpzlMapper {

	@Select("select '恒安医药' AS suppliers_name,PM_ZJM as ypdm,a.cd as cddm,jx,CONVERT(varchar(100),a.SCRQ,23) as scrq,"+
	        "a.guid as goods_id_s,a.hh as goods_sn,pm as drug_common_name,a.scdw as manufacturer,a.PZWH as approve_number,"+
			"'' as drug_img,GG as specifications,PDW as package_unit,ZBZL as medium_package,a.mjl as large_package,'1' as is_retail,"+
	        "PH as production_batch,CONVERT(varchar(100),a.XQ,23) as date_expiration,a.sl as repertory,b.tm as bar_code,PFJ as supplier_price,"+
	        "a.guid as drugid,'1' as is_on_sale,GSPSortID as category from phk a,yw_kck b where a.hh = b.hh and a.sl > 0")
	public List<Spbnew> getspbnew();

	@Insert("insert into jk_xsddhead(APP_DD_ID,ERP_CUSTOM_ID,CREATE_TIME) values(#{APP_DD_ID},#{ERP_Custom_ID},#{Create_Time})")
	void insertHZ(DDHZ ddhz);

	@Insert("insert into jk_xsdd(APP_DD_ID,MX_ID,ERP_SP_ID,ERP_SP_DJ,ERP_SP_SL) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_ID},#{ERP_SP_DJ},#{ERP_SP_SL})")
	void insertMX(DDMX ddmx);

}

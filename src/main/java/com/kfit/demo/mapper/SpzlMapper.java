package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpzlMapper {

	@Select("select '恒安医药' AS suppliers_name,PM_ZJM as ypdm,a.cd as cddm,jx,CONVERT(varchar(100),a.SCRQ,23) as scrq,"+
	        "a.guid as goods_id_s,a.hh as goods_sn,pm as drug_common_name,a.scdw as manufacturer,a.PZWH as approve_number,"+
			"'' as drug_img,GG as specifications,PDW as package_unit,FLOOR(ZBZL) as medium_package,FLOOR(a.mjl) as large_package,'1' as is_retail,"+
	        "PH as production_batch,CONVERT(varchar(100),a.XQ,23) as date_expiration,a.sl as repertory,b.tm as bar_code,b.sj as supplier_price,"+
	        "a.guid as drugid,'1' as is_on_sale,GSPSortID as category from phk a,yw_kck b where a.hh = b.hh and a.sl > 0 and b.bz not like '%含特殊药品复方制剂%'")
	public List<Spbnew> getspbnew();

	@Select("select '恒安医药' AS suppliers_name,PM_ZJM as ypdm,a.cd as cddm,jx,CONVERT(varchar(100),a.SCRQ,23) as scrq,"+
			"a.guid as goods_id_s,a.hh as goods_sn,pm as drug_common_name,a.scdw as manufacturer,a.PZWH as approve_number,"+
			"'' as drug_img,GG as specifications,PDW as package_unit,ZBZL as medium_package,a.mjl as large_package,'1' as is_retail,"+
			"PH as production_batch,CONVERT(varchar(100),a.XQ,23) as date_expiration,a.sl as repertory,b.tm as bar_code,b.sj as supplier_price,"+
			"a.guid as drugid,'1' as is_on_sale,GSPSortID as category from phk a,yw_kck b where a.hh = b.hh and a.sl > 0 and b.bz not like '%含特殊药品复方制剂%' and a.guid = #{id}")
	public Spbnew getspbnewById(String id);

	@Select("select tjbh as code,mc as name,tel as telephone,qyfzr as linkman,yydz as address,YYZZ as taxnumber,YLJGXKZH as xkzh from gl_custom where (YYZZ = #{taxNo} or YLJGXKZH = #{taxNo}) and FDELETED=0 and STOPSELL=0")
	public Custom getCustomerByTaxNo(String taxNo);

	@Insert("insert into jk_xsddhead(APP_DD_ID,ERP_CUSTOM_ID,CREATE_TIME,IS_PAY) values(#{APP_DD_ID},#{ERP_Custom_ID},#{Create_Time},0)")
	void insertHZ(DDHZ ddhz);

	@Insert("insert into jk_xsdd(APP_DD_ID,MX_ID,ERP_SP_ID,ERP_SP_DJ,ERP_SP_SL) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_ID},#{ERP_SP_DJ},#{ERP_SP_SL})")
	void insertMX(DDMX ddmx);

	@Update("update jk_xsddhead set IS_PAY = 1 where APP_DD_ID = #{id}")
	public void UpdateOrderStatusByID(String id);

}

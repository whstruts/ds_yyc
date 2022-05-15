package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpzlMapper {

	@Select("select  '华中仓' as suppliers_name,ypdm,cddm,jx,scrq,txm,goods_id_s,goods_sn," +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"       '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,goods_thumb as drug_img," +
			" gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"       '' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"       goods_number as repertory, '' as supplier ,'' as left_view,'' as right_view,txm as bar_code," +
			"       '' as unpack_view,'' as specification_view,dj,zk,truncate(dj*zk*(select markUp from hykx_rd.lmsys where customNo = 'stapp'),3) as supplier_price,goods_id_s as drugid" +
			"            from hykx_rd.yzy_goods " +
			"            where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1  " +
			"            and ((ISRETAIL = 0 and goods_number+1 > bz*2) or (ISRETAIL = 1 and goods_number+1 > bz/2) )  " +
			"            and CONVERT(bz,DECIMAL) > CONVERT(zbz,DECIMAL) and shop_price > 0  " +
			"            and  locate('YSBBBC', goods_sn) and pzwh not like '%食%' and jx not like '%消毒%'")
	public List<Spbnew> getspbnew();

	@Select("select  '华中仓' as suppliers_name,ypdm,cddm,jx,scrq,txm,goods_id_s,goods_sn, " +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"       '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,goods_thumb as drug_img," +
			" gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"       '' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"       goods_number as repertory, '' as supplier ,'' as left_view,'' as right_view,txm as bar_code," +
			"       '' as unpack_view,'' as specification_view,dj,zk,truncate(dj*zk*(select markUp from hykx_rd.lmsys where customNo = 'stapp'),3) as supplier_price,goods_id_s as drugid" +
			"            from hykx_rd.yzy_goods " +
			"            where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1  " +
			"            and ((ISRETAIL = 0 and goods_number+1 > bz*2) or (ISRETAIL = 1 and goods_number+1 > bz/2) )  " +
			"            and CONVERT(bz,DECIMAL) > CONVERT(zbz,DECIMAL) and shop_price > 0  " +
			"            and  locate('YSBBBC', goods_sn) and pzwh not like '%食%' and jx not like '%消毒%' and goods_id_s = #{id}")
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

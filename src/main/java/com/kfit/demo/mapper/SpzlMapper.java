package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpzlMapper {

	@Select("select  '天尧仓' as suppliers_name,ypdm,cddm,jx,scrq,txm,goods_id_s,goods_sn," +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"       '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,REPLACE(REPLACE(goods_thumb,\"**\",\"/\"),\"good\",\"http://www.hyey.cn/uploadpic/600\") as drug_img," +
			" gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"       '' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"       goods_number as repertory, '' as supplier ,'' as left_view,'' as right_view,TRIM(txm) as bar_code," +
			"       '' as unpack_view,'' as specification_view,dj,zk,truncate(dj*zk*(select markUp from hykx_jxty.lmsys where customNo = 'thapp'),3) as supplier_price,goods_id_s as drugid" +
			"            from hykx_jxty.yzy_goods g " +
			"            where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1  " +
			"            and ((ISRETAIL = 0 and goods_number+1 > bz*2) or (ISRETAIL = 1 and goods_number+1 > bz/2) )  " +
			"            and CONVERT(bz,DECIMAL) > CONVERT(zbz,DECIMAL) and shop_price > 0  " +
			"            and NOT EXISTS (select * from hykx_jxty.lmsys_pzwh b where g.PZWH = b.pzwh) " +
			"            and  locate('YSBTYC', goods_sn) and pzwh not like '%食%' and jx not like '%消毒%'")
	public List<Spbnew> getspbnew();

	@Select("SELECT '泰衡医药' as suppliers_name,'' as YPDM,'' as JX, min(prodDate) as scrq,barcode as txm, drugCode as goods_id_s,drugCode as goods_sn, " +
			" drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			" '1' as is_retail,GROUP_CONCAT(batchNum) as production_batch,min(validity) as date_expiration,sum(stock) as repertory,price as supplier_price,drugCode as drugid " +
			" FROM hykx_jxty.thgoods where price > 0  and stock >0 " +
			" group by drugCode")
	public List<Spbnew> getspbnewst();

	@Select("select  '天尧仓' as suppliers_name,ypdm,cddm,jx,scrq,txm,goods_id_s,goods_sn, " +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"       '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,REPLACE(REPLACE(goods_thumb,\"**\",\"/\"),\"good\",\"http://www.hyey.cn/uploadpic/600\") as drug_img," +
			" gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"       '' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"       goods_number as repertory, '' as supplier ,'' as left_view,'' as right_view,TRIM(txm) as bar_code," +
			"       '' as unpack_view,'' as specification_view,dj,zk,truncate(dj*zk*(select markUp from hykx_jxty.lmsys where customNo = 'thapp'),3) as supplier_price,goods_id_s as drugid" +
			"            from hykx_jxty.yzy_goods g " +
			"            where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1  " +
			"            and ((ISRETAIL = 0 and goods_number+1 > bz*2) or (ISRETAIL = 1 and goods_number+1 > bz/2) )  " +
			"            and CONVERT(bz,DECIMAL) > CONVERT(zbz,DECIMAL) and shop_price > 0  " +
			"            and NOT EXISTS (select * from hykx_jxty.lmsys_pzwh b where g.PZWH = b.pzwh) " +
			"            and  locate('YSBTYC', goods_sn) and pzwh not like '%食%' and jx not like '%消毒%' and goods_id_s = #{id}")
	public Spbnew getspbnewById(String id);

	@Select("SELECT '泰衡医药' as suppliers_name,'' as YPDM,'' as JX, prodDate as scrq,barcode as txm, drugCode as goods_id_s,drugCode as goods_sn, " +
			" drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			" '1' as is_retail,batchNum as production_batch,validity as date_expiration,stock as repertory,price as supplier_price,inCode as drugid " +
			" FROM hykx_jxty.thgoods where price > 0 and inCode = #{id} ")
	public Spbnew getspbnewstById(String id);

	@Select("select custid as code,trim(custname) as name,contactphone as telephone,contactperson as linkman,address,taxno as taxnumber " +
			"from hykx_jxty.customer_erp " +
			"where custid = #{taxNo} or licenceno = #{taxNo} or taxno = #{taxNo}")
	public Custom getCustomerByTaxNo(String taxNo);

	@Insert("insert into hykx_jxty.jk_xsddhead(APP_DD_ID,ERP_CUSTOM_ID,CREATE_TIME,IS_PAY,DD_HJ,djbh) values(#{APP_DD_ID},#{ERP_Custom_ID},#{Create_Time},0,#{DD_HJ},#{djbh})")
	void insertHZ(DDHZ ddhz);

	@Insert("insert into hykx_jxty.jk_xsdd(APP_DD_ID,MX_ID,ERP_SP_ID,ERP_SP_DJ,ERP_SP_SL) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_ID},#{ERP_SP_DJ},#{ERP_SP_SL})")
	void insertMX(DDMX ddmx);

	@Update("update jk_xsddhead set IS_PAY = 1 where APP_DD_ID = #{id}")
	public void UpdateOrderStatusByID(String id);

}

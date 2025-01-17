package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpzlMapper {

	@Select("select  '源臻堂仓' as suppliers_name,drug_code as ypdm,factory_code as cddm,drug_type as jx, prod_date2 as scrq,drug_bar_code as txm,store_id as goods_id_s,store_id as goods_sn," +
			" drug_name as drug_common_name,drug_owner as manufacturer,approve_no as approve_number,'' as recipe_type,'' as type_code," +
			"       '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,main_url as drug_img," +
			" spec as specifications,unit as package_unit,middle_package as medium_package,pack as large_package," +
			"       '' as usage_dosage,is_retail,batch_num as production_batch,due_date2 as date_expiration," +
			"       store_num as repertory, '' as supplier ,'' as left_view,'' as right_view,drug_bar_code as bar_code," +
			"       '' as unpack_view,'' as specification_view,1 as dj,price as zk,truncate(price*(select markUp from hykx_hbyzt.lmsys where customNo = 'yztapp'),3) as supplier_price,store_id as drugid" +
			"            from hykx_hbyzt.drug_inventory_item g" +
			"            where RPAD(due_date2,10,'-15') >sysdate()  " +
			"            and ((is_retail = 0 and store_num+1 > pack*2) or (is_retail = 1 and store_num+1 > pack/2) )  " +
			"            and CONVERT(pack,DECIMAL) > CONVERT(middle_package,DECIMAL) and price > 0  " +
			"            and NOT EXISTS (select * from hykx_hbyzt.lmsys_pzwh b where g.approve_no = b.pzwh) " +
			"            and approve_no not like '%食%' and spec not like '%消毒%'" +
			"            and DATE(update_date)=CURDATE() " +
			"            and is_retail = 1 ")
	public List<Spbnew> getspbnew();

	@Select("SELECT  case ownerName" +
			" when '市场一部' then '市场一部'" +
			" when '市场二部' then '市场二部'" +
			" when '市场五部' then '市场五部'" +
			" when '市场三部' then '市场三部'" +
			" else '济万佳仓'" +
			" end as suppliers_name," +
			" '' as YPDM,'' as JX, min(prodDate) as scrq,barcode as txm, drugCode as goods_id_s,drugCode as goods_sn, " +
			" drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			" '1' as is_retail,GROUP_CONCAT(batchNum) as production_batch,min(validity) as date_expiration,sum(stock) as repertory,price as supplier_price,drugCode as drugid " +
			" FROM hykx_hbyzt.yztgoods where price > 0  and stock >0 " +
			" group by drugCode")
	public List<Spbnew> getspbnewst();

	@Select("select  '源臻堂仓' as suppliers_name,drug_code as ypdm,factory_code as cddm,drug_type as jx, prod_date2 as scrq,drug_bar_code as txm,store_id as goods_id_s,store_id as goods_sn, " +
			" drug_name as drug_common_name,drug_owner as manufacturer,approve_no as approve_number,'' as recipe_type,'' as type_code," +
			"      '' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"       '' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,main_url as drug_img," +
			" spec as specifications,unit as package_unit,middle_package as medium_package,pack as large_package," +
			"       '' as usage_dosage,is_retail,batch_num as production_batch,due_date2 as date_expiration," +
			"       store_num as repertory, '' as supplier ,'' as left_view,'' as right_view,drug_bar_code as bar_code," +
			"       '' as unpack_view,'' as specification_view,1 as dj,price as zk,truncate(price*(select markUp from hykx_hbyzt.lmsys where customNo = 'yztapp'),3) as supplier_price,store_id as drugid" +
			"            from hykx_hbyzt.drug_inventory_item g" +
			"            where RPAD(due_date2,10,'-15') >sysdate()  " +
			"            and ((is_retail = 0 and store_num+1 > pack*2) or (is_retail = 1 and store_num+1 > pack/2) )  " +
			"            and CONVERT(pack,DECIMAL) > CONVERT(middle_package,DECIMAL) and price > 0  " +
			"            and NOT EXISTS (select * from hykx_hbyzt.lmsys_pzwh b where g.approve_no = b.pzwh) " +
			"            and approve_no not like '%食%' and spec not like '%消毒%' and store_id = #{id}")
	public Spbnew getspbnewById(String id);

	@Select("SELECT  case ownerName" +
			" when '市场一部' then '市场一部'" +
			" when '市场二部' then '市场二部'" +
			" when '市场五部' then '市场五部'" +
			" when '市场三部' then '市场三部'" +
			" else '济万佳仓'" +
			" end as suppliers_name," +
			"'' as YPDM,'' as JX, min(prodDate) as scrq,barcode as txm, drugCode as goods_id_s,drugCode as goods_sn, " +
			" drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			" '1' as is_retail,GROUP_CONCAT(batchNum) as production_batch,min(validity) as date_expiration,sum(stock) as repertory,price as supplier_price,drugCode as drugid  " +
			" FROM hykx_hbyzt.yztgoods where price > 0  and stock >0 and drugCode = #{id}" +
			" group by drugCode")
	public Spbnew getspbnewstById(String id);

	@Select("select custid as code,trim(custname) as name,contactphone as telephone,contactperson as linkman,address,taxno as taxnumber " +
			"from hykx_hbyzt.customer_erp " +
			"where custid = #{taxNo} or licenceno = #{taxNo} or taxno = #{taxNo}")
	public Custom getCustomerByTaxNo(String taxNo);

	@Insert("insert into hykx_hbyzt.jk_xsddhead(APP_DD_ID,ERP_CUSTOM_ID,CREATE_TIME,IS_PAY,DD_HJ,djbh) values(#{APP_DD_ID},#{ERP_Custom_ID},#{Create_Time},0,#{DD_HJ},#{djbh})")
	void insertHZ(DDHZ ddhz);

	@Insert("insert into hykx_hbyzt.jk_xsdd(APP_DD_ID,MX_ID,ERP_SP_ID,ERP_SP_DJ,ERP_SP_SL) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_ID},#{ERP_SP_DJ},#{ERP_SP_SL})")
	void insertMX(DDMX ddmx);

	@Insert("insert into hykx_hbyzt.xyy_seller_order_info(order_id,order_no,total_amount,is_hy_run,rq,ontime) values(#{djbh},#{APP_DD_ID},#{DD_HJ},0,#{rq},#{ontime})")
	void insertHZH5(DDHZH5 ddhz);

	@Insert("insert into hykx_hbyzt.xyy_seller_order_detail(order_no,order_detail_id,HY_ID,sku_price,sku_purchase_price,purchase_num) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_ID},#{ERP_SP_DJ},#{ERP_SP_DJ},#{ERP_SP_SL})")
	void insertMXH5(DDMX ddmx);

	@Insert("insert into hykx_hbyzt.ysb_ddhz(djbh,rq,ontime,customerid,je) values(#{APP_DD_ID},#{rq},#{ontime},#{ERP_Custom_ID},#{DD_HJ})")
	void insertHZYSB(DDHZH5 ddhz);

	@Insert("insert into hykx_hbyzt.ysb_ddmx(djbh,dj_sn,drugcode,hy_id,shl,dj,je) values(#{APP_DD_ID},#{MX_ID},#{ERP_SP_CODE},#{ERP_SP_ID},#{ERP_SP_SL},#{ERP_SP_DJ},#{ERP_SP_DJ}*#{ERP_SP_SL})")
	void insertMXYSB(DDMX ddmx);

	@Update("update jk_xsddhead set IS_PAY = 1 where APP_DD_ID = #{id}")
	public void UpdateOrderStatusByID(String id);

	@Select("select case is_retail when 1 then concat('LMPPC',drug_num) " +
			"else concat('LMPPZ',drug_num) end as drug_code  from drug_inventory_item where store_id = #{id}")
	public String getDrugCodeById(String id);

	@Insert("insert into ysb_ddhz_hy(djbh,rq,ontime,je,is_run,customerId) " +
			"select djbh,rq,ontime,je,is_run,customerId from ysb_ddhz where djbh = #{orderId}")
	void insertYSBHZ(String orderId);

	@Insert("insert into ysb_ddmx_hy(djbh,dj_sn,drugCode,shl,dj,je,cgdj,cgje,hy_id) " +
			"select djbh,dj_sn,drugCode,shl,dj,je,cgdj,cgje,hy_id from ysb_ddmx where djbh = #{orderId}")
	void insertYSBMX(String orderId);


}

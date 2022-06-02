package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpzlMapper {

	@Select("SELECT CONCAT(b.product_id,b.batch_id) as goods_sn,a.search_code as ypdm,'' as cddm,a.medicine_style as jx,b.production_date as scrq, '' as txm ,a.product_id as  goods_id_s, " +
			" a.product_name as  drug_common_name,a.manufacturer as manufacturer,'' as appearance,'' as drug_img,a.medicine_spec as specifications,a.uom as package_unit,if(a.small_package=0,1,a.small_package) as medium_package," +
			" a.certificate_no as approve_number,a.large_package,1 as is_retail,b.lot as production_batch,b.guarantee_date as date_expiration," +
			" c.stock_qty as repertory," +
			" b.price_po as supplier_price," +
			" a.product_id as drugid," +
			" '南药恩施' AS suppliers_name " +
			" from ny_products a ,ny_batchs b,ny_product_batch_stocks c" +
			" where a.product_id = b.product_id" +
			" and a.product_id= c.product_id" +
			" and b.batch_id = c.batch_id")
	public List<Spbnew> getspbnewNY();

	@Select("SELECT drugid AS goods_sn,ypdm,cddm,jx,scrq,txm,goods_id_s,drug_common_name,manufacturer,appearance,drug_img," +
			" approve_number,specifications,package_unit,medium_package,large_package,is_retail," +
			" production_batch,date_expiration,repertory,supplier_price,drugid,'华中仓' AS suppliers_name " +
			" FROM " +
			" third_commodity_hy where is_retail = 1")
	public List<Spbnew> getspbnewHY();

	@Select("SELECT drugid AS goods_sn,ypdm,cddm,jx,scrq,txm,goods_id_s,drug_common_name,manufacturer,appearance,drug_img," +
			" approve_number,specifications,package_unit,medium_package,large_package,is_retail," +
			" production_batch,date_expiration,repertory,supplier_price,drugid,'华中仓' AS suppliers_name " +
			" FROM " +
			" third_commodity_hy"+
			" where Goods_id_s = #{id}"+
	        " union" +
			" SELECT CONCAT(b.product_id,b.batch_id) as goods_sn,a.search_code as ypdm,'' as cddm,a.medicine_style as jx,b.production_date as scrq, '' as txm ,a.product_id as  goods_id_s, " +
			" a.product_name as  drug_common_name,a.manufacturer as manufacturer,'' as appearance,'' as drug_img,a.medicine_spec as specifications,a.uom as package_unit,a.small_package as medium_package," +
			" a.certificate_no as approve_number,a.large_package,1 as is_retail,b.lot as production_batch,b.production_date as date_expiration," +
			" c.stock_qty as repertory, " +
			" b.price_po as supplier_price, " +
			" a.product_id as drugid, " +
			" '南药恩施' AS suppliers_name " +
			" from ny_products a ,ny_batchs b,ny_product_batch_stocks c " +
			" where a.product_id = b.product_id " +
			" and a.product_id= c.product_id " +
			" and b.batch_id = c.batch_id " +
	        " and b.product_id = LEFT(#{id},7)" +
			" and b.batch_id = RIGHT(#{id},7)")
	public List<Spbnew> GetKCByIDNEWX(String id);

	@Select("select bpartner_id as code,partner_name as name,'' as type,tax_number as taxnumber,'' as categories from ny_bpartners where tax_number = #{taxnumber} or bpartner_id = #{taxnumber}")
	public List<Custom> GetCustomerByTaxNo(String taxnumber);

}

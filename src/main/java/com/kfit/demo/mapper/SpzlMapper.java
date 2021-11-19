package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpzlMapper {

	@Select("SELECT CONCAT(b.product_id,b.batch_id) as goods_sn,a.search_code as ypdm,'' as cddm,a.medicine_style as jx,b.production_date as scrq, '' as txm ,a.product_id as  goods_id_s, " +
			" a.product_name as  drug_common_name,a.manufacturer as manufacturer,'' as appearance,'' as drug_img,a.medicine_spec as specifications,a.uom as package_unit,a.small_package as medium_package," +
			" a.large_package,1 as is_retail,b.lot as production_batch,b.production_date as date_expiration," +
			" c.stock_qty as repertory," +
			" b.price_po as supplier_price," +
			" a.product_id as drugid," +
			" '南药恩施' AS suppliers_name " +
			" from ny_products a ,ny_batchs b,ny_product_batch_stocks c" +
			" where a.product_id = b.product_id" +
			" and a.product_id= c.product_id" +
			" and b.batch_id = c.batch_id")
	public List<Spbnew> getspbnewNY();

	@Select("SELECT drugid AS goods_sn,ypdm,cddm,jx,scrq,txm,goods_id_s,drug_common_name,manufacturer,appearance,drug_img,\n" +
			" specifications,package_unit,medium_package,large_package,is_retail," +
			" production_batch,date_expiration,repertory,supplier_price,drugid,'华中仓' AS suppliers_name " +
			" FROM " +
			" third_commodity_hy")
	public List<Spbnew> getspbnewHY();

	@Select(" select * from (select a.ddwname as Suppliers_name,b.prvno as ypdm,b.dspcd as cddm,c.jxmc as jx,isnull(d.scrq,'') as scrq,"+
			" a.dspid+'_'+isnull(d.picih,'') as Goods_id_s,b.dspcode as Goods_sn,b.dspname as Drug_common_name,b.shenccj as manufacturer,c.pzwh as Approve_number,a.picurl as Drug_img,b.dspspgg as specifications,"+
			" b.danwei as Package_unit,a.zhongbz as Medium_package,b.jianbz as Large_package,a.is_chail as Is_retail,isnull(d.picih,'') as production_batch,isnull(d.baozhiqi,'') as date_expiration,isnull(d.kcsl,0) as repertory,"+
			" b.sptm as Bar_code,a.price as Supplier_price,a.is_zais as Is_on_sale,c.G_YPJYFW as Category,a.is_gengx from doc_sp_wz a"+
			" left join doc_sp b on a.dspid=b.dspid"+
			" left join doc_sp_gsp c on a.dspid=c.dspid"+
			" left join ("+
					" select dspid,picih,scrq,baozhiqi,SUM(kcsl-wcsl) as kcsl from sp_kfpc"+
					" where kcsl-wcsl>0 group by dspid,picih,scrq,baozhiqi) d on a.dspid=d.dspid) a"+
			" where Goods_id_s = #{id}")
	public List<Spbnew> GetKCByIDNEWX(String id);

	@Select("select erpCustomerID AS code,erpCustomerName AS name,'' AS type,Type_numbers as taxnumber,categories from dw_yxxx where taxnumber = #{taxnumber}")
	public List<Custom> GetCustomerByTaxNo(String taxnumber);

}

package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SpzlMapper {

	@Select("select a.ddwname as Suppliers_name,b.prvno as ypdm,b.dspcd as cddm,c.jxmc as jx,isnull(d.scrq,'') as scrq," +
			"       a.dspid+'#'+isnull(d.picih,'') as Goods_id_s,b.dspcode as Goods_sn,b.dspname as Drug_common_name,b.shenccj as manufacturer,c.pzwh as Approve_numer,a.picurl as Drug_img,b.dspspgg as specifications," +
			"       b.danwei as Package_unit,a.zhongbz as Medium_package,b.jianbz as Large_package,a.is_chail as Is_retail,isnull(d.picih,'') as production_batch,isnull(d.baozhiqi,'') as date_expiration,isnull(d.kcsl,0) as repertory," +
			"       b.sptm as Bar_code,a.price as Suppliers_price,a.is_zais as Is_on_sale,c.G_YPJYFW as Category,a.is_gengx from doc_sp_wz a" +
			"                                                                                                                        left join doc_sp b on a.dspid=b.dspid" +
			"                                                                                                                        left join doc_sp_gsp c on a.dspid=c.dspid" +
			"                                                                                                                        left join (" +
			"    select dspid,picih,scrq,baozhiqi,SUM(kcsl-wcsl) as kcsl from sp_kfpc" +
			"    where kcsl-wcsl>0 group by dspid,picih,scrq,baozhiqi) d on a.dspid=d.dspid")
	public List<Spbnew> getspbnew();

	@Select("select '同云堂' as suppliers_name,ypdm as ypdm,cddm as cddm,jx as jx,CONVERT(varchar(100), scrq, 23) as scrq," +
			"goods_id_s+'_'+production_batch as goods_id_s,goods_sn as goods_sn,drug_common_name as drug_common_name,manufacturer as manufacturer," +
			"approve_numer as approve_number,drug_img as drug_img,specifications as specifications,package_unit as package_unit," +
			"medium_package as medium_package,large_package as large_package,'1' as is_retail," +
			"CONVERT(varchar(100), production_batch, 23) as production_batch,CONVERT(varchar(100), date_expiration, 23) as date_expiration," +
			"repertory as repertory,bar_code as bar_code,suppliers_price as supplier_price,drugid as drugid," +
			"is_on_sale as is_on_sale,category as erp_category from v_ware_b2b_tyt where goods_id_s+'_'+production_batch = #{id}")
	public List<Spbnew> GetKCByIDNEWX(String id);

	@Select("select erpCustomerID AS code,erpCustomerName AS name,custom AS type,taxnumber,categories from v_custom_b2b_tyt where taxnumber = #{taxnumber}")
	public List<Custom> GetCustomerByTaxNo(String taxnumber);

}

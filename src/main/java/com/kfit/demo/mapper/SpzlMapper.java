package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SpzlMapper {
	
	//#{name}:参数占位符

	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1 " +
			"and ((ISRETAIL = 0 and goods_number+1 > bz * 20) or (ISRETAIL = 1 and goods_number+1 > bz * 1.5) )" +
			"and goods_sn like 'KXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 " +
			"or (goods_sn like 'YMD%'and is_on_sale = 1 and bz > 0)")
	public List<Spzl> getKC();

	@Select("select goods_id as g_in_sn,goods_sn as g_sn,txm as g_bar_code,goods_name as g_name,ypdm as g_name_code,'' as g_alias,pzwh as g_license_no," +
			"gg as g_specifications,dw as g_unit,zbz as  g_middle_package,bz as g_big_package,cdmc as g_manufacture,cddm as g_manufacture_code," +
			"shop_price*0.91 as g_price,shop_price_yyw as g_base_price,shop_price_yyw as g_cost_price,goods_number as g_realnum," +
			"'' as g_purview,isretail as g_can_split,'' as g_order_cate,'0' as g_area,'0' as g_control,'1' as g_status from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			"and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			"and goods_sn like 'KXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 " )
	public List<Spb> getspb();

	@Select("select IFNULL(suppliers_name,'亿诺医药') as suppliers_name,ypdm,cddm,jx,scrq,txm,case when isnull(goods_id_s) then goods_sn else goods_id_s end as goods_id_s,goods_sn," +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"'' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"'' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand, "+
			"case when goods_sn like 'YYN%' or goods_sn like 'NYY%' then CONCAT('http://www.hbyyn.com/',goods_img) else goods_img end as drug_img," +
			"gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"'' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"goods_number as repertory, '' as supplier ,'' as left_view,'' as right_view,txm as bar_code," +
			"'' as unpack_view,'' as specification_view, " +
			"TRUNCATE(DJ*ZK,2) as supplier_price,goods_id_s as drugid from yzy_goods g left JOIN yzy_suppliers s on " +
			"g.suppliers_id = s.suppliers_id " +
			" where (((RPAD(YXQ,10,'-15') >sysdate()    " +
			" and ((ISRETAIL = 1 and goods_number+1 > bz/2) or (ISRETAIL = 0 and goods_number+1 > bz*2)) " +
			" and goods_sn like 'HYYP%' and cast(bz as signed) > cast(zbz as signed))" +
			" and NOT EXISTS (select * from hykx_rd.lmsys_pzwh b where g.PZWH = b.pzwh)) " +
			" or (goods_sn like 'YYN%' or goods_sn like 'NYY%')) " +
			" and (shop_price > 0 and is_on_sale = 1 and is_delete = 0)" )
	public List<Spbnew> getspbnew();

	@Select("select goods_sn,case when isnull(goods_id_s) then goods_sn else goods_id_s end as id,ISRETAIL as isretail,g.ypbh,goods_name as ypmc, cdmc,cdmc as drugowner, " +
			"      gg,bz,zbz,ph,pch,yxq,goods_number as sl, market_price as lsj,TRUNCATE(DJ*ZK,2) as dj,dw,jx,0.13 as zzssl,pzwh,'' as ck,'' as ghdwlb, " +
			" case when goods_sn like 'YYN%' or goods_sn like 'NYY%' then CONCAT('http://www.hbyyn.com/',goods_img) else goods_img end as imgurl,txm as tm, " +
			"      ypdm,cddm,cddm as drugownercode,scrq,is_on_sale as isonsale,FROM_UNIXTIME(add_time) as updatedate " +
			" from yzy_goods g left JOIN yzy_suppliers s on  " +
			" g.suppliers_id = s.suppliers_id  " +
			"  where ((RPAD(YXQ,10,'-15') >sysdate()     " +
			"  and ((ISRETAIL = 1 and goods_number+1 > bz/2) or (ISRETAIL = 0 and goods_number+1 > bz*2))  " +
			"  and goods_sn like 'HYYP%' and cast(bz as signed) > cast(zbz as signed)))  " +
			"  and (shop_price > 0 and is_on_sale = 1 and is_delete = 0)  " +
			"  and LENGTH(goods_id_s) = 36" )
	public List<MyGoodsEntity> getHYGoods();

	@Select("select goods_sn,case when isnull(goods_id_s) then goods_sn else goods_id_s end as id,ISRETAIL as isretail,g.ypbh,goods_name as ypmc, cdmc,cdmc as drugowner,  " +
			" TRUNCATE(max(DJ)*ZK*1.06*(select markup from hykx.lmsys_seg b where g.dj > b.min and g.dj <= b.max and customNo = #{customNo}),2) as dj, " +
			" dw,jx,0.13 as zzssl,pzwh,'' as ck,'' as ghdwlb,  " +
			"  case when goods_sn like 'YYN%' or goods_sn like 'NYY%' then CONCAT('http://www.hbyyn.com/',goods_img) else goods_img end as imgurl,txm as tm,  " +
			"       ypdm,cddm,cddm as drugownercode,scrq,is_on_sale as isonsale,FROM_UNIXTIME(add_time) as updatedate  " +
			"  from yzy_goods g left JOIN yzy_suppliers s on   " +
			"  g.suppliers_id = s.suppliers_id  " +
			"  where ((RPAD(YXQ,10,'-15') >sysdate()      " +
			"   and ((ISRETAIL = 1 and goods_number+1 > bz/2) or (ISRETAIL = 0 and goods_number+1 > bz*2))   " +
			"   and goods_sn like 'HYYP%' and cast(bz as signed) > cast(zbz as signed)))   " +
			"   and (shop_price > 0 and is_on_sale = 1 and is_delete = 0)   " +
			" and not EXISTS (select * from hykx.lmsys_pzwh b where g.PZWH = b.pzwh) " +
			"   and LENGTH(goods_id_s) = 36 group by ypbh" )
	public List<MyGoodsEntity> getHYGoodsP(String customNo);

	@Select("SELECT 'ST仓' as suppliers_name,zjm as YPDM,jixing as JX, MAX(prodDate) as scrq,barcode as txm, inCode as goods_id_s,drugCode as goods_sn, " +
			"drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			"'1' as is_retail,MAX(batchNum) as production_batch,MAX(validity) as date_expiration,stock as repertory,price as supplier_price,inCode as drugid " +
			"FROM hykx_rd.st2yngoods where price > 0 and isdesc = '否' and isonsale = 1 GROUP BY inCode")
	public List<Spbnew> getspbnewst();

	@Select("SELECT 'ST仓' as suppliers_name,zjm as YPDM,jixing as JX, MAX(prodDate) as scrq,barcode as txm, inCode as goods_id_s,drugCode as goods_sn, " +
			" drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			" '1' as is_retail,MAX(batchNum) as production_batch,MAX(validity) as date_expiration,stock as repertory,price as supplier_price,inCode as drugid " +
			" FROM hykx_rd.st2yngoods where price > 0 and isdesc = '否' and isonsale = 1 and (inCode = #{id} or drugCode = #{id}) GROUP BY inCode ")
	public List<Spbnew> GetKCByIDNEWX(String id);

	@Select("select ypdm,cddm,jx,scrq,txm,case when isnull(goods_id_s) then goods_sn else goods_id_s end as goods_id_s,goods_sn," +
			" goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"'' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"'' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand, "+
			"case when goods_sn like 'YMD%' or goods_sn like 'MDY%' then CONCAT('http://www.ymdb2b.com/',goods_img) else goods_img end as drug_img," +
			"gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"'' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"goods_number as repertory,'' as supplier,'' as left_view,'' as right_view,txm as bar_code," +
			"'' as unpack_view,'' as specification_view, " +
			"DJ as supplier_price,goods_id_s as drugid from yzy_goods " +
			" where (  (goods_sn like 'YMD%' or goods_sn like 'MDY%')" +
			" and  (shop_price > 0 and is_on_sale = 1 and is_delete = 0) and cast(bz as signed) > cast(zbz as signed))" )
	public List<Spbnew> getspbnewzy();

	@Select("select goods_sn,goods_name as drug_common_name,cdmc as manufacturer,pzwh as approve_number,'' as recipe_type,'' as type_code," +
			"'' as dosage_form,'' as appearance,'' as bases,'' as major_functions,'' as untoward_effect," +
			"'' as taboo,'' as store,'' as warnings,'' as drug_interactions,'' as brand,goods_img as drug_img," +
			"gg as specifications,dw as package_unit,zbz as medium_package,bz as large_package," +
			"'' as usage_dosage,ISRETAIL as is_retail,ph as production_batch,yxq as date_expiration," +
			"goods_number as repertory,'' as supplier,'' as left_view,'' as right_view,txm as bar_code," +
			"'' as unpack_view,'' as specification_view, " +
			"shop_price as supplier_price,goods_id as drugid, " +
			"ypdm,cddm,jx,scrq,txm,goods_id_s from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			"and goods_sn like 'YMD%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 " )
	public List<Spbnew> getspbnewymd();

	@Select("SELECT 'ST仓' as suppliers_name,zjm as YPDM,jixing as JX, min(prodDate) as scrq,barcode as txm, inCode as goods_id_s,drugCode as goods_sn, " +
			"drugName as drug_common_name,factory as manufacturer,approval as approve_number,pack as specifications,unit as package_unit,midPack as medium_package,wholePack as large_package, " +
			"'1' as is_retail,batchNum as production_batch,validity as date_expiration,stock as repertory,price as supplier_price,inCode as drugid " +
			"FROM hykx_rd.st2yngoods where price > 0 and isdesc = '否' and (inCode = #{id} or drugCode = #{id})  GROUP BY inCode ")
	public Spbnew getspbnewstById(String id);

	@Select("select goods_id as g_in_sn,goods_id as gb_id_no,'' as gb_ku_no,'' as gb_hw_no,ph as gb_batch_no," +
			"yxq as gb_end_time, goods_number as gb_number from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			"and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			"and goods_sn like 'KXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 " )
	public List<kcb> getkcb();

	@Select("select code as  c_internal_code,dwbh as  c_code,name as  c_name ,jcpym as  c_name_code ,	'1' as  c_status ,"+
			"'1' as  c_isxs ,'0' as  c_is_wholesaler ,zzzch as  c_license_no,	zzyxqz as  c_license_endtime ,	xkzh as  c_allow_no,"+
			"xkzyxqz as  c_allow_endtime ,gspzh as  c_gsp_no ,gspzyxqz as  c_gsp_endtime ,dwjb as  c_customer_type,'' as  c_purview ,"+
			"'' as  c_area ,'' as  c_salesman,	qyfr as  c_legal,	'' as  c_legal_no,shr1 as  c_contact,email as  c_email,telephone as  c_tel,"+
			"fzrlxdh as  c_phone,'' as  c_province,'' as  c_city,'' as  c_address from zt_kh where xkzyxqz <> '0000-00-00' and zzyxqz <> '0000-00-00' LIMIT 58")
	public List<Khzl> getkhb();

	@Select("select code,amount,batchnumber,validdate,productdate,price from zt_ywkc where cwtz = #{cwtz}")
	public List<erp_stock> geterpkc(String cwtz);



	@Select("select goods_sn,case  when isretail = 1 then zbz else bz end goods_mpn,jx,gg,dw from hykx.yzy_goods ")
	public List<Spmpn> getmpn();

	@Select("select goods_sn,goods_name,gg,cdmc,case  when isretail = 1 then zbz else bz end mpn,"+
			" bz,shop_price,dw,jx,pzwh,txm from hykx.yzy_goods  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			" and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			" and goods_sn like 'KXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 LIMIT #{aaa},#{bbb}")
	public List<Spzl> getyycsjsp(hh h1);

    @Select("SELECT goods_id,goods_number,ROUND(DJ*1.15,2) as gyj_price,shop_price," +
			" goods_sn,goods_name,gg,case  when isretail = 1 then zbz else bz end mpn, "+
            " cdmc,bz,dw,jx,pzwh,txm,goods_sn,yxq,zbz,case  when isretail = 1 then '拆零' else '整件' end sfcl " +
			" FROM hykx.yzy_goods " +
			" WHERE RPAD( YXQ, 10, '-01' ) >  DATE_ADD(SYSDATE(),INTERVAL 365 DAY) " +
            " AND is_on_sale = 1  AND goods_sn LIKE 'YSBKXN%' AND shop_price > 0 ")
    public List<Spzl> getxyysp();

    @Select("SELECT goods_id,goods_number,ROUND(DJ*1.15,2) as gyj_price,shop_price," +
            " goods_sn,goods_name,gg,"+
            " cdmc,bz,dw,jx,pzwh,txm " +
            " FROM hykx.yzy_goods " +
           "  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
            " and  ((ISRETAIL = 1 and goods_number+1 > bz/2) or (ISRETAIL = 0 and goods_number+1 > bz*2))" +
            " and goods_sn like 'KXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 and from_unixtime(add_time,'%Y-%m-%d')= #{sdate}")
    public List<Spzl> getysbnew(String sdate);

	@Select("select goods_sn,goods_name,gg,cdmc,case  when isretail = 1 then zbz else bz end mpn,"+
			" bz,shop_price,dw,jx,pzwh,txm from hykx.yzy_goods  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			" and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			" and goods_sn like 'YSBKXN%' and cast(bz as signed) > cast(zbz as signed)  and shop_price > 0 ")
	public List<Spzl> getyycsjspa();

	@Select("select goods_name,dw,jx,gg,cdmc,bz,pzwh  from yzy_goods group by goods_name,dw,jx,gg,cdmc,bz ")
	public List<Spzl> getspzlx();


	@Select("SELECT u.user_name,u.user_id,k.name,u.user_money - u.frozen_money kyje FROM " +
			"huayuan_shop.yzy_reg_extend_info i,huayuan_shop.zt_kh k,huayuan_shop.yzy_users u, " +
			"(select user_id,sum(user_money) sum_money from yzy_account_log where change_time <= UNIX_TIMESTAMP(#{sum_date})+86400 GROUP BY user_id) x  " +
			"WHERE k. CODE = i.content AND i.reg_field_id = 12 AND i.user_id = u.user_id AND k. NAME IS NOT NULL " +
			"and u.user_id = x.user_id and x.sum_money - u.frozen_money > 0 ORDER BY u.user_money - u.frozen_money desc ")
	public List<User> getusers(String sum_date);


	@Select("select case  when isretail = 1 then zbz else bz end goods_mpn from hykx.yzy_goods where goods_sn = #{goods_sn}")
	public String getmpnbysn(String goods_sn);

	@Insert("INSERT INTO demo(name) VALUES(#{name})")
	void insert(User user);

	@Insert("replace INTO yyw_ddhz(mph_order_sn,erp_sn,pre_subtotal,subtotal,vouchers_price,number,price,pre_price,accept_name,mobile,address,internal_code,order_status,express_price,payment_method,order_addTime) "+
			" VALUES(#{mph_order_sn},#{erp_sn},#{pre_subtotal},#{subtotal},#{vouchers_price},#{number},"+
			"#{price},#{pre_price},#{accept_name},#{mobile},#{address},#{internal_code},#{order_status},#{express_price},#{payment_method},#{order_addTime})")
	void inserthz(yywddhz hz);

	@Insert("replace INTO yyw_ddmx(order_detail_id,mph_order_sn,erp_sn,name,manufacture,license_no,specifications,unit,middle_package,big_package,"+
			"can_split,pre_subtotal,subtotal,vouchers_price,number,send_number,price,pre_price,pd_sale_time,od_unit_price,preferential_unit_price) "+
			"VALUES(#{order_detail_id},#{mph_order_sn},#{erp_sn},#{name},#{manufacture},#{license_no},#{specifications},#{unit},#{middle_package},#{big_package},"+
			"#{can_split},#{pre_subtotal},#{subtotal},#{vouchers_price},#{number},#{send_number},#{price},#{pre_price},#{pd_sale_time},#{od_unit_price},#{preferential_unit_price}) ")
	void insertmx(yywddmx mx);

	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods where goods_id = #{goods_id}")
	public Spzl getById(long goods_id);
	
	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods where goods_name like #{goods_name}")
	public List<Spzl> getspByName(String goods_name);

	@Select("call YYW_AddHZ() ")
	void YYW_AddHZ();

	@Select("select i.order_id as webdjbh,djbh,rq,ontime,customerId,status,je,i.ahhy_ddid as hydjbh,beizhu,is_run,customerName" +
			" from huayuanyyn.yzy_order_info i,hykx.ysb_ddhz h where h.djbh = i.hyds_ddid and i.is_to_erp = #{is_run} and h.userName = #{userName}")
	public List<ysbddhz> getysbddhzs(ysbddhz hz);

	@Select("select m.*,g.goods_price_cg*1.06 as cgdj,g.goods_number*goods_price_cg*1.06 as cgje,g.lock_bzh as  beizhu from huayuanyyn.yzy_order_info i,hykx.ysb_ddmx m,huayuanyyn.yzy_order_goods g " +
			" where m.djbh = i.hyds_ddid and i.order_id = #{djbh} and i.order_id = g.order_id")
	public List<ysbddmx> getysbddmxbydjbh(String djbh);

	@Update("update huayuanyyn.yzy_order_info set is_to_erp = #{is_run} where hyds_ddid = #{djbh}")
	void updateysbddhz(ysbddhz hz);

}

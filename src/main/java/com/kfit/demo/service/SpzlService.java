package com.kfit.demo.service;

import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;
    public List<Spzl> getspzlx(){ return spzlMappper.getspzlx();}
	public String getmpnbysn(String goods_sn) {return  spzlMappper.getmpnbysn(goods_sn);}
	public List<Spzl> getyycsjsp(hh h1) {return  spzlMappper.getyycsjsp(h1);}
	public List<Spzl> getyycsjsp() {return  spzlMappper.getyycsjspa();}
	public List<Spzl> getxyysp() {return  spzlMappper.getxyysp();}
	public List<Spmpn> getmpn(){return spzlMappper.getmpn();}
	public List<Spzl> getKC() {
		return spzlMappper.getKC();
	}
	public List<Spb> getspb() {
		return spzlMappper.getspb();
	}
	public List<Spbnew> getspbnew() {
		return spzlMappper.getspbnew();
	}

	public List<MyGoodsEntity> getHYGoods() {
		return spzlMappper.getHYGoods();
	}

	public List<MyGoodsEntity> getHYGoodsP() {
		return spzlMappper.getHYGoodsP();
	}

	public List<Spbnew> GetKCByIDNEWX(String id) {
		return spzlMappper.GetKCByIDNEWX(id);
	}

	public List<Spbnew> getspbnewst() {
		return spzlMappper.getspbnewst();
	}
	public List<Spbnew> getspbnewzy() {
		return spzlMappper.getspbnewzy();
	}
	public List<Spbnew> getspbnewymd() {
		return spzlMappper.getspbnewymd();
	}
	public List<kcb> getkcb() {
		return spzlMappper.getkcb();
	}
	public List<Khzl> getkhb() {
		return spzlMappper.getkhb();
	}

	public List<erp_stock> geterpkc(String cwtz) {
		return spzlMappper.geterpkc(cwtz);
	}

	public List<User> getuser(String sum_date){ return spzlMappper.getusers(sum_date);} //20190426 whstruts

	public void ItoUsers(User user){
		spzlMappper.insert(user);
	}
	public void InToDDHZ(yywddhz hz){
		spzlMappper.inserthz(hz);
	}
	public void InToDDMX(yywddmx mx){
	    spzlMappper.insertmx(mx);
	}
	public void YYW_AddHZ(){
		spzlMappper.YYW_AddHZ();
	}
	public List<Spzl> getysbspnew(String sdate) {return  spzlMappper.getysbnew(sdate);}

	public List<ysbddhz> getysbddhzs(ysbddhz hz) {return  spzlMappper.getysbddhzs(hz);}

	public List<ysbddmx> getysbddmxbydjbh(String djbh) {return  spzlMappper.getysbddmxbydjbh(djbh);}
	public void updateysbddhz(ysbddhz hz){spzlMappper.updateysbddhz(hz);}
}

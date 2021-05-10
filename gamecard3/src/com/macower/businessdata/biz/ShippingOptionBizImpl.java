package com.macower.businessdata.biz;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.macower.businessdata.dao.ShippingOptionDaoImpl;
import com.macower.businessdata.entity.ShippingOption;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;

@Service
public class ShippingOptionBizImpl extends BaseBiz implements ShippingOptionBiz {

	@Autowired
	private ShippingOptionDaoImpl shippingOptionDao ;
	
	@Override
	public Page<ShippingOption> findPageBy(ShippingOption obj, Integer pageNo,
			Integer pageSize) {
		return shippingOptionDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<ShippingOption> findBy(ShippingOption obj) {
		if(obj == null){
			obj = new ShippingOption() ;
		}
		return shippingOptionDao.findBy(obj) ;
	}


	@Override
	public void save(ShippingOption obj) {
		shippingOptionDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.shippingOptionDao.deleteByIds(ids) ;
	}

	@Override
	public ShippingOption get(Long id) {
		return this.shippingOptionDao.get(id) ;
	}

	@Override
	public void update(ShippingOption obj) {
		this.shippingOptionDao.update(obj) ;
		
	}


}

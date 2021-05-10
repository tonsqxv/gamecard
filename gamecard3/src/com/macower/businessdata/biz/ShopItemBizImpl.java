package com.macower.businessdata.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.ShopItemDaoImpl;
import com.macower.businessdata.dto.ShopItemDto;
import com.macower.businessdata.entity.ShopItem;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;

@Service
public class ShopItemBizImpl extends BaseBiz implements ShopItemBiz {

	@Autowired
	private ShopItemDaoImpl shopItemDao ;
	

	@Override
	public List<ShopItem> findBy(ShopItem obj) {
		return shopItemDao.findBy(obj) ;
	}


	@Override
	public void save(ShopItem obj) {
		shopItemDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.shopItemDao.deleteByIds(ids) ;
	}

	@Override
	public ShopItem get(Long id) {
		return this.shopItemDao.get(id) ;
	}


	@Override
	public void updateShopItem(ShopItem obj) {
		ShopItem old = this.shopItemDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		old.setAmount(obj.getAmount()) ;
		old.setCreateTm(obj.getCreateTm()) ;
		this.shopItemDao.update(old) ;
		
	}


	@Override
	public void update(ShopItem obj) {
		this.shopItemDao.update(obj) ;
		
	}


	@Override
	public Page<ShopItem> findPageBy(ShopItemDto obj, int pageNo, int pageSize) {
		return this.shopItemDao.findPageBy(obj, pageNo, pageSize);
	}


	



}

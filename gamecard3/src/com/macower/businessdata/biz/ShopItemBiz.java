package com.macower.businessdata.biz;


import java.util.List;

import com.macower.businessdata.dto.ShopItemDto;
import com.macower.businessdata.entity.ShopItem;
import com.macower.core.entity.Page;

public interface ShopItemBiz {

	public List<ShopItem> findBy(ShopItem obj);

	public void save(ShopItem obj);

	public void updateShopItem(ShopItem obj);

	public void deletes(String ids);
	
	public ShopItem get(Long id) ;

	public void update(ShopItem item);

	public Page<ShopItem> findPageBy(ShopItemDto obj, int i, int limit);

}

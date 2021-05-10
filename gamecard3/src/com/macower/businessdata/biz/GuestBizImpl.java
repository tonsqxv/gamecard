package com.macower.businessdata.biz;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.GuestDaoImpl;
import com.macower.businessdata.dao.MemberDaoImpl;
import com.macower.businessdata.dao.OrderDaoImpl;
import com.macower.businessdata.entity.Guest;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.Order;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;

@Service
public class GuestBizImpl extends BaseBiz implements GuestBiz {

	@Autowired
	private GuestDaoImpl guestDao ;
	
	@Autowired
	private OrderDaoImpl orderDao ;
	
	@Autowired
	private MemberDaoImpl memberDao ;
	

	@Override
	public Page<Guest> findPageBy(Guest obj, Integer pageNo,
			Integer pageSize) {
		return guestDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Guest> findBy(Guest obj) {
		return guestDao.findBy(obj) ;
	}


	@Override
	public void save(Guest obj) {
		guestDao.save(obj) ;
	}

	@Override
	public void update(Guest obj) {
		this.guestDao.update(obj) ;
	}

	@Override
	public void deletes(String ids) {
		this.guestDao.deleteByIds(ids) ;
	}

	@Override
	public Guest get(Long id) {
		return this.guestDao.get(id) ;
	}


	/**
	 *同步数据 
	 */
	@Override
	public int sycnData() {
		List<Order> orders = this.orderDao.findGuestSycnData() ;
		if(orders == null || orders.size() == 0){
			return 0 ;
		}
		Guest guest = null ;
		int count = 0 ;
		for(Order o : orders){
			Guest param = new Guest() ;
			param.setEmail(o.getEmail()) ;
			int result = this.guestDao.countBy(param) ;
			if(result >0){
				if(o.getSycnFlag()== null || o.getSycnFlag() != 1){
					o.setSycnFlag(1) ;
					this.orderDao.update(o) ;
				}
				continue ;
			}
			guest = new Guest() ;
			guest.setEmail(o.getEmail()) ;
			guest.setFirstName(o.getFirstName()) ;
			guest.setLastName(o.getLastName()) ;
			guest.setCityName(o.getCity()) ;
			guest.setState(o.getState()) ;
			guest.setZipcode(o.getZipCode()) ;
			guest.setCountry(o.getCountry()) ;
			guest.setCountryName(o.getCountryName()) ;
			guest.setCreateTm(new Date()) ;
			guest.setStatus(0) ;
			guest.setTaskStatus(0) ;
			count ++ ;
			this.guestDao.save(guest) ;
			o.setSycnFlag(1) ;
			this.orderDao.update(o) ;
		}
		return count ;
	}


	/**
	 * 同步会员数据  
	 */
	@Override
	public int sycnMemberData() {
		Guest param = new Guest() ;
		param.setStatus(0) ;
		List<Guest> list = this.guestDao.findBy(param) ;
		int sum = 0 ;
		for(Guest g : list){
			Member m =new Member() ;
			m.setEmail(g.getEmail().trim()) ;
			int count =  this.memberDao.countBy(m) ;
			if(count > 0){
				sum ++ ;
				g.setStatus(1) ;
				this.guestDao.update(g) ;
			}
		}
		return sum ;
	}

	@Override
	public void init() {
		this.guestDao.executeUpdate("update tb_guest set task_status = 0 ,send_tm = null") ;
		
	}

	@Override
	public void resetOrderData() {
		this.guestDao.executeUpdate("update tb_order set sycn_flag = 0 where member_id is null and (sycn_flag is null or sycn_flag =1) and email is not null  and order_status != 1") ;
	}

}

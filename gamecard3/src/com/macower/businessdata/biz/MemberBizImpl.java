package com.macower.businessdata.biz;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.MemberDaoImpl;
import com.macower.businessdata.entity.Member;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;

@Service
public class MemberBizImpl extends BaseBiz implements MemberBiz {

	@Autowired
	private MemberDaoImpl memberDao ;
	

	@Override
	public Page<Member> findPageBy(Member obj, Integer pageNo,
			Integer pageSize) {
		return memberDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Member> findBy(Member obj) {
		return memberDao.findBy(obj) ;
	}


	@Override
	public void save(Member obj) {
		Member param = new Member() ;
		param.setEmail(obj.getEmail().trim()) ;
		int count = this.memberDao.countBy(param) ;
		if(count > 0 ){
			throw new BizException(obj.getEmail()+" 邮箱已经存在") ;
		}
		memberDao.save(obj) ;

	}

	@Override
	public void updateBy(Member obj) {
		Member old = memberDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		//如果邮箱有改动 需做唯一校验
		if(!old.getEmail().equals(obj.getEmail())){
			Member param = new Member() ;
			param.setEmail(obj.getEmail().trim()) ;
			int count = this.memberDao.countBy(param) ;
			if(count > 0 ){
				throw new BizException(obj.getEmail()+"邮箱已被注册") ;
			}
		}
		old.setPassword(obj.getPassword()) ;
		old.setLocked(obj.getLocked()) ;
		old.setEmail(obj.getEmail().trim()) ;
		old.setFirstName(obj.getFirstName()) ;
		old.setLastName(obj.getLastName()) ;
		old.setPhoneNumber(obj.getPhoneNumber()) ;
		

		memberDao.update(old) ;
		

	}
	
	@Override
	public void update(Member obj) {
		this.memberDao.update(obj) ;
	}

	@Override
	public void deletes(String ids) {
		this.memberDao.deleteByIds(ids) ;
	}

	@Override
	public Member get(Long id) {
		return this.memberDao.get(id) ;
	}


	@Override
	public void init() {
		this.memberDao.executeUpdate("update tb_member set task_status = 0 ,send_tm = null") ;
	}


	



}

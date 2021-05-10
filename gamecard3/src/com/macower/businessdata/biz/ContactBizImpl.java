package com.macower.businessdata.biz;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominicsayers.isemail.dns.DNSLookupException;
import com.macower.businessdata.dao.ContactDaoImpl;
import com.macower.businessdata.entity.Contact;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;
@Service
public class ContactBizImpl extends BaseBiz implements ContactBiz {

	@Autowired
	private ContactDaoImpl contactDao ;
	
	@Override
	public Page<Contact> findPageBy(Contact obj, Integer pageNo,
			Integer pageSize) {
		return contactDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Contact> findBy(Contact obj) {
		return contactDao.findBy(obj) ;
	}


	@Override
	public void save(Contact obj) {
		contactDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.contactDao.deleteByIds(ids) ;
	}

	@Override
	public Contact get(Long id) {
		return this.contactDao.get(id) ;
	}

	@Override
	public void replay(Contact obj) {
		Contact old = this.contactDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("record has been deleted") ;
		}
		//发送邮件
		String subject = "Reply to :"+old.getEmail() ;
		String emailto = obj.getEmail() ;
		
		EmailUtil email = new EmailUtil(null,null,null,null) ;
		try {
			if (!email.checkEmail(emailto)) {
				throw new BizException("发送失败，邮箱地址无效");
			}
		} catch (DNSLookupException e) {
			log.error(e.getMessage()) ;
			throw new BizException("发送邮件出现系统异常，请与系统管理员联系") ;
		}
		
		StringBuffer msg = new StringBuffer() ;
		msg.append(obj.getReplyMsg()) ;
		msg.append("<br/>") ;
		msg.append("<p><a href=\"http://www.n3ds-card.com\">http://n3ds-card.com</a></p>") ;
		
		boolean success = email.sendGmailEmail(subject, msg.toString(), emailto) ;
		if(success){
			old.setStatus(2) ; //已回复
			old.setReplyMsg(obj.getReplyMsg()) ;
			old.setReplyTm(new Date()) ;
			this.contactDao.update(old) ;
		}else{
			throw new BizException("邮件发送失败") ;
		}
		
		
	}
	


}

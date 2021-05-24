package com.macower.businessdata.biz;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.DiscodeDaoImpl;
import com.macower.businessdata.entity.Discode;
import com.macower.businessdata.util.MailTpl;
import com.macower.businessdata.util.UniqueStringGenerator;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;

@Service
public class DiscodeBizImpl extends BaseBiz implements DiscodeBiz {

	@Autowired
	private DiscodeDaoImpl discodeDao ;
	
	@Override
	public Page<Discode> findPageBy(Discode obj, Integer pageNo,
			Integer pageSize) {
		return discodeDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Discode> findBy(Discode obj) {
		return discodeDao.findBy(obj) ;
	}


	@Override
	public void createBatch(Integer count) {
		String discode = null ;
		Discode param = null ;
		for(int i = 0 ; i< count ; i++){
			discode =  new UniqueStringGenerator().generateCode() ;
			//保存前校验是否已存在
			param = new Discode() ;
			param.setDiscode(discode) ;
			int sum = this.discodeDao.countBy(param) ;
			if(sum == 0){
				Discode obj = new Discode() ;
				obj.setStatus(0) ; //新建
				obj.setCreateTm(new Date()) ;
				obj.setDiscode(discode) ;
				this.discodeDao.save(obj) ;
			}
		}
	}
	
	@Override
	public void deletes(String ids) {
		this.discodeDao.deleteByIds(ids) ;
	}

	@Override
	public Discode get(Long id) {
		return this.discodeDao.get(id) ;
	}


	@Override
	public void emailSendDiscode(String email) {
		//查找可用的折扣码 
		Discode param = new Discode() ;
		param.setStatus(0) ;
		Page<Discode> page = this.findPageBy(param, 0, 1) ;
		List<Discode> data = (List<Discode>)page.getData() ;
		if(data == null || data.size() == 0){
			throw new BizException("系统没有可用的折扣码，请先生成折扣码") ;
		}
		Discode code = data.get(0) ;
		//发送邮件
		String subject = "Coupon Code For Customer" ;
		String context = new MailTpl().getDiscodeSendTpl(code.getDiscode()) ;
		EmailUtil emailUtil = new EmailUtil(null, null);
		emailUtil.sendGmailEmail(subject, context, email);
		//更改折扣码状态
		code.setStatus(1) ; //未使用
		this.discodeDao.update(code) ;
	}


}

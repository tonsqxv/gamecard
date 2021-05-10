package com.macower.businessdata.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.macower.businessdata.dao.MemberDaoImpl;
import com.macower.businessdata.dao.TaskEmailDaoImpl;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.TaskEmail;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.StringUtils;

@Service
public class TaskEmailBizImpl extends BaseBiz implements TaskEmailBiz {
	

	@Autowired
	private TaskEmailDaoImpl taskEmailDao ;
	

	@Autowired
	private MemberDaoImpl memberDao ;

	@Override
	public Page<TaskEmail> findPageBy(TaskEmail obj, Integer pageNo,
			Integer pageSize) {
		return taskEmailDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public void save(TaskEmail obj) {
		taskEmailDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.taskEmailDao.deleteByIds(ids) ;
	}

	@Override
	public TaskEmail get(Long id) {
		return this.taskEmailDao.get(id) ;
	}


	@Override
	public void init() {
		this.taskEmailDao.executeUpdate("update tb_task_email set status = 0 ,send_tm = null") ;
		
	}


	@Override
	public void importExcel(HttpServletRequest request) {
		try {
			// 转型为MultipartHttpRequest
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile file = multipartRequest.getFile("uploadFile");
			// 获得文件名：
			//String realFileName = file.getOriginalFilename();
			List<String> emails = new ArrayList<String>() ;
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream()); 
			 for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			        if (hssfSheet == null) {
			            continue;
			        }
			        // 循环行Row
			        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			        	 HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			             if (hssfRow == null) {
			                 continue;
			             }
			             HSSFCell xh = hssfRow.getCell(0);
			             if(StringUtils.isNotEmpty(getValue(xh)) && getValue(xh).matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")){
			            	 emails.add(getValue(xh));
			             }
			            
			        }
			 }
			 TaskEmail taskEmail = null ;
			 TaskEmail param = null ;
			 for(String email : emails){
				 taskEmail = new TaskEmail() ;
				 taskEmail.setEmail(email) ;
				 taskEmail.setStatus(0) ; //初始化为等待执行
				 taskEmail.setIsMember(0) ;
				 //保存前先校验是否已存在
				 param = new TaskEmail() ;
				 param.setEmail(email) ;
				 int count =  this.taskEmailDao.countBy(param) ;
				 if(count > 0){
					 continue ;
				 }
				 this.taskEmailDao.save(taskEmail) ;
			 }
		} catch (Exception e) {
			e.printStackTrace() ;
			log.error(e.getMessage()) ;
			throw new BizException("导入出现异常") ;
		}
		
	}
	 @SuppressWarnings("static-access")
		private String getValue(HSSFCell hssfCell) {
		        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
		            // 返回布尔类型的值
		            return String.valueOf(hssfCell.getBooleanCellValue());
		        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		            // 返回数值类型的值
		            return String.valueOf(hssfCell.getNumericCellValue());
		        } else {
		            // 返回字符串类型的值
		            return String.valueOf(hssfCell.getStringCellValue());
		        }
		    }


	@Override
	public int sycnMemberData() {
		TaskEmail param = new TaskEmail() ;
		param.setIsMember(0) ;
		List<TaskEmail> list = this.taskEmailDao.findBy(param) ;
		int sum = 0 ;
		for(TaskEmail g : list){
			Member m =new Member() ;
			m.setEmail(g.getEmail().trim()) ;
			int count =  this.memberDao.countBy(m) ;
			if(count > 0){
				sum ++ ;
				g.setIsMember(1) ;
				this.taskEmailDao.update(g) ;
			}
			
		}
		
		return sum ;
	}


	@Override
	public void clear() {
		this.taskEmailDao.executeUpdate("delete from tb_task_email") ;
		
	}


	@Override
	public List<TaskEmail> findBy(TaskEmail obj) {
		return this.taskEmailDao.findBy(obj) ;
	}
		


}

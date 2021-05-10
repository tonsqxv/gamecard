package com.macower.businessdata.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.TaskEmailBiz;
import com.macower.businessdata.entity.TaskEmail;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@Controller
@RequestMapping(value = "/taskEmail")
public class TaskEmailAction {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaskEmailBiz taskEmailBiz;

	/**
	 * 后台管理-会员管理主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/taskEmail";
	}

	/**
	 * 后台管理-分页查询
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(TaskEmail obj, HttpServletResponse response) {
		List<TaskEmail> root = new ArrayList<TaskEmail>();
		Page<TaskEmail> page = taskEmailBiz.findPageBy(obj, obj.getStart()
				/ obj.getLimit(), obj.getLimit());
		root = (List<TaskEmail>) page.getData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-添加
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(TaskEmail obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskEmailBiz.save(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-AJAX请求批量删除
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskEmailBiz.deletes(ids);
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-初始化任务
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/init" })
	public void init(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskEmailBiz.init();
		} catch (Exception e) {
			// 捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-excel导入
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/importExcel" })
	public void importExcel(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskEmailBiz.importExcel(request);

			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");

		} catch (BizException e1) {
			e1.printStackTrace();
			map.put("msg", e1.getMessage());
		} catch (Exception e2) {
			e2.printStackTrace();
			map.put("error", e2.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-同步会员数据
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/sycnMemberData" })
	public void sycnMemberData(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			int count = taskEmailBiz.sycnMemberData();
			map.put("msg", count);
		} catch (Exception e) {
			// 捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-清空表数据
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/clear" })
	public void clear(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskEmailBiz.clear();
		} catch (Exception e) {
			// 捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-导出
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/export" })
	public void export(TaskEmail obj, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");

			List<TaskEmail> data = taskEmailBiz.findBy(obj);
			InputStream template = this.getClass().getResourceAsStream(
					"/com/macower/businessdata/tpl/taskEmail.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(template);
			HSSFSheet sheet = workbook.getSheetAt(0);
			writeDataToSheet(sheet, data);

			String filename = "目标邮箱.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ encodeFilename(filename, request));
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			// 捕获所有的异常
			e.printStackTrace();
		}

	}

	public static String encodeFilename(String filename,
			HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"),
							"ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				return MimeUtility.encodeText(filename, "UTF-8", "B");
			}
			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}

	@SuppressWarnings("deprecation")
	private void writeDataToSheet(HSSFSheet sheet, List<TaskEmail> data) {
		List<HSSFCellStyle> style = new ArrayList<HSSFCellStyle>(0);
		HSSFRow rowModel = sheet.getRow(1);
		for (int i = 0; i < 1; i++) {
			style.add(rowModel.getCell(i).getCellStyle());
		}
		sheet.removeRow(rowModel);

		int rownum = 2; // 从第二行开始写
		rownum = rownum - 1;
		for (TaskEmail obj : data) {
			HSSFRow row = sheet.createRow(rownum++);
			short column = 0;
			HSSFCell cell0 = row.createCell(column++);
			cell0.setCellStyle(style.get(column - 1));
			cell0.setCellValue(new HSSFRichTextString(obj.getEmail()));
		}

	}

}

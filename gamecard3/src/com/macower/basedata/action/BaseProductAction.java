package com.macower.basedata.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.macower.basedata.biz.ProductBiz;
import com.macower.basedata.entity.Product;
import com.macower.basedata.util.Config;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.FileUtils;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/baseProduct")
public class BaseProductAction {

	@Autowired
	private ProductBiz productBiz;


	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "basedata/product";
	}

	/**
	 * 后台管理 - 分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })  
	public void list(Product obj, HttpServletResponse response) {
		List<Product> root = new ArrayList<Product>();
		Page<Product> page = productBiz.findPageBy(obj,
				obj.getStart() / obj.getLimit(), obj.getLimit());
		root = ((List<Product>) page.getData());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理 -添加
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(Product obj,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			productBiz.save(obj,request);
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
	 * 后台管理-更新数据
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/update" })
	public void update(Product obj,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			productBiz.update(obj,request);
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
	 * 后台管理-更新数据
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/updateDesc" })
	public void updateDesc(Product obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			productBiz.updateDesc(obj) ;
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
	 * 后台管理-删除记录
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		String ctxPath = Config.getProductDir(request) ;
		try {
			productBiz.deletes(ctxPath, ids);
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-上传图片
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/upload" })
	public void upload(Product obj, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			String ctxPath = Config.getProductDir(request) ;
			File file = new File(ctxPath);
			if (!file.exists()) {
				file.mkdir();
				file.setExecutable(true);
				file.setWritable(true);
				file.setReadable(true);

			}
			String fileName = null;
			String pre = obj.getId() + "_"+obj.getCategoryId() + "_" ;
			String last = null;
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				// 上传文件名
				MultipartFile mf = entity.getValue();
				if (mf.isEmpty()) {
					continue;
				}
				last = "." + mf.getOriginalFilename().split("\\.")[1];

				if ("mainImg".equals(entity.getKey())) {
					fileName = pre + "_mainImg" + last;
					obj.setMainImgPath(fileName);
				}else if ("hotImg".equals(entity.getKey())) {
					fileName = pre + "_hotImg" + last;
					obj.setHotImgPath(fileName);
				}

				File uploadFile = new File(ctxPath + fileName);
				if (uploadFile.exists()) {
					uploadFile.delete();
				}
				// FileCopyUtils.copy(mf.getBytes(), uploadFile);
				mf.transferTo(uploadFile); // 保存上传的文件

			}

			this.productBiz.updateImg(obj);

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
	 * 后台管理-上传缩略图
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/zoomUpload" })
	public void uploadZoom(Long id,Long categoryId ,HttpServletRequest request,
			HttpServletResponse response) {
		Product obj = new Product();
		obj.setId(id) ;
		obj.setCategoryId(categoryId) ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {   
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			String ctxPath = Config.getProductDir(request) ;
			File file = new File(ctxPath);
			if (!file.exists()) {
				file.mkdir();
				file.setExecutable(true);
				file.setWritable(true);
				file.setReadable(true);
			}
			String fileName = null;
			String pre =  obj.getId()+"_" +obj.getCategoryId() + "_";
			String last = null;
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				// 上传文件名
				MultipartFile mf = entity.getValue();
				if (mf.isEmpty()) {
					continue;
				}
				last = "." + mf.getOriginalFilename().split("\\.")[1];
				if ("zoomImg1".equals(entity.getKey())) {
					fileName = pre + "_zoomImg1" + last;
					obj.setZoomImg1(fileName) ;
				} else if ("zoomImg2".equals(entity.getKey())) {
					fileName = pre + "_zoomImg2" + last;
					obj.setZoomImg2(fileName) ;
				} else if ("zoomImg3".equals(entity.getKey())) {
					fileName = pre + "_zoomImg3" + last;
					obj.setZoomImg3(fileName) ;
				} else if ("zoomImg4".equals(entity.getKey())) {
					fileName = pre + "_zoomImg4" + last;
					obj.setZoomImg4(fileName) ;
				} else if ("zoomImg5".equals(entity.getKey())) {
					fileName = pre + "_zoomImg5" + last;
					obj.setZoomImg5(fileName) ;
				} else if ("zoomImg6".equals(entity.getKey())) {
					fileName = pre + "_zoomImg6" + last;
					obj.setZoomImg6(fileName) ;
				} else if ("zoomImg7".equals(entity.getKey())) {
					fileName = pre + "_zoomImg7" + last;
					obj.setZoomImg7(fileName) ;
				}else if ("zoomImg8".equals(entity.getKey())) {
					fileName = pre + "_zoomImg8" + last;
					obj.setZoomImg8(fileName) ;
				}else if ("zoomImg9".equals(entity.getKey())) {
					fileName = pre + "_zoomImg9" + last;
					obj.setZoomImg9(fileName) ;
				}else if ("zoomImg10".equals(entity.getKey())) {
					fileName = pre + "_zoomImg10" + last;
					obj.setZoomImg10(fileName) ;
				}

				File uploadFile = new File(ctxPath + fileName);
				if (uploadFile.exists()) {
					uploadFile.delete();
				}
				// FileCopyUtils.copy(mf.getBytes(), uploadFile);
				mf.transferTo(uploadFile); // 保存上传的文件

			}

			this.productBiz.updateZoomImg(obj);

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
	 * 下载图片
	 * 
	 * @param obj
	 * @param downloadType
	 *            下载类型 0：主图片下载
	 * @param response
	 */
	@RequestMapping({ "/downloadImg" })
	public void downloadImg(Product obj, Integer downloadType,
			HttpServletRequest request, HttpServletResponse response) {
		String ctxPath = Config.getProductDir(request) ;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");

			
			Product old = this.productBiz.get(obj.getId());
			String imgName = null;
			switch (downloadType) {
			case 0://主图片
				imgName = old.getMainImgPath() ;
				break;
			case -1://首页推介
				imgName = old.getHotImgPath() ;
				break;	
			case 101://缩略图1
				imgName = old.getZoomImg1() ;
				break;	
			case 102://缩略图2
				imgName = old.getZoomImg2() ;
				break;	
			case 103://缩略图3
				imgName = old.getZoomImg3() ;
				break;	
			case 104://缩略图4
				imgName = old.getZoomImg4() ;
				break;	
			case 105://缩略图5
				imgName = old.getZoomImg5() ;
				break;	
			case 106://缩略图6
				imgName = old.getZoomImg6() ;
				break;	
			case 107://缩略图7
				imgName = old.getZoomImg7() ;
				break;	
			case 108://缩略图8
				imgName = old.getZoomImg8() ;
				break;	
			case 109://缩略图9
				imgName = old.getZoomImg9() ;
				break;	
			case 110://缩略图10
				imgName = old.getZoomImg10() ;
				break;	
				default:
					break ;
			}
			String downLoadPath = ctxPath + imgName;
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(imgName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (BizException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	/**
	 * 后台管理-图片删除
	 * @param ids
	 * @param request
	 * @param response
	 */
	@RequestMapping({ "/deleteImg" })
	public void deleteImg( Product obj,Integer type, HttpServletRequest request,
			HttpServletResponse response) {
		Product old = this.productBiz.get(obj.getId()) ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		String ctxPath = Config.getProductDir(request) ;
		try {
			String imgName = null ;
			switch (type) {
			case 0://主图片
				imgName = old.getMainImgPath() ;
				old.setMainImgPath(null) ;
				old.setMainImgDesc(null) ;
				break;
			case -1://首页推介
				imgName = old.getHotImgPath() ;
				old.setHotImgPath(null) ;
				old.setHotDesc(null) ;
				break;	
			case 101://缩略图1
				imgName = old.getZoomImg1() ;
				old.setZoomImg1(null) ;
				break;	
			case 102://缩略图2
				imgName = old.getZoomImg2() ;
				old.setZoomImg2(null) ;
				break;	
			case 103://缩略图3
				imgName = old.getZoomImg3() ;
				old.setZoomImg3(null) ;
				break;	
			case 104://缩略图4
				imgName = old.getZoomImg4() ;
				old.setZoomImg4(null) ;
				break;	
			case 105://缩略图5
				imgName = old.getZoomImg5() ;
				old.setZoomImg5(null) ;
				break;	
			case 106://缩略图6
				imgName = old.getZoomImg6() ;
				old.setZoomImg6(null) ;
				break;	
			case 107://缩略图7
				imgName = old.getZoomImg7() ;
				old.setZoomImg7(null) ;
				break;	
			case 108://缩略图8
				imgName = old.getZoomImg8() ;
				old.setZoomImg8(null) ;
				break;	
			case 109://缩略图9
				imgName = old.getZoomImg9() ;
				old.setZoomImg9(null) ;
				break;	
			case 110://缩略图10
				imgName = old.getZoomImg10() ;
				old.setZoomImg10(null) ;
				break;	
				
				default:
					break ;
			}
			FileUtils.deleteFile(ctxPath+imgName) ; 
			this.productBiz.update(old) ;
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}
	
	/**
	 * 后台管理-设置排序
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/updateSortNo" })
	public void updateSortNo(Product obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			productBiz.updateSortNo(obj);
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
}

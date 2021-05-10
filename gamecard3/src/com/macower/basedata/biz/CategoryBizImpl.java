package com.macower.basedata.biz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dto.CategoryDto;
import com.macower.basedata.dao.CategoryDaoImpl;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.CategoryTree;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;

@Service
public class CategoryBizImpl extends BaseBiz implements CategoryBiz {

	@Autowired
	private CategoryDaoImpl categoryDao ;
	
	@Override
	public Page<Category> findPageBy(Category obj, Integer pageNo,
			Integer pageSize) {
		return categoryDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	public List<Category> findBy(Category obj){
		return this.categoryDao.findBy(obj) ;
	}

	@Override
	public void save(Category obj) {
		Category param = new Category() ;
		param.setName(obj.getName()) ;
		int count  = this.categoryDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getName()+"已经存在") ;
		}
		
		categoryDao.save(obj) ;

	}

	@Override
	public void update(Category obj) {
		Category old = this.categoryDao.get(obj.getId()) ;
		if(!old.getName().equals(obj.getName())){
			Category param = new Category() ;
			param.setName(obj.getName()) ;
			int count  = this.categoryDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getName()+"已经存在") ;
			}
		}
		old.setName(obj.getName()) ;
		old.setSort(obj.getSort()) ;
		old.setProductDetailTpl(obj.getProductDetailTpl()) ;
		categoryDao.update(old) ;

	}

	@Override
	public void deletes(String ids) {
		this.categoryDao.deleteByIds(ids) ;
	}

	/**
	 * 获取前台展示分类列表
	 */
	@Override
	public List<Category> getCategoryList(Integer size) {
		//分类
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class) ;
		Page<Category> page =  this.categoryDao.findPageBy(dc, 0, size,"sort",true) ;
		return (List<Category>)page.getData() ;
	}

	@Override
	public Category get(Long id) {
		return this.categoryDao.get(id) ;
	}

	/**
	 * 后台查询分类树
	 */
	@Override
	public List<CategoryTree> findCategoryTree() {
		List<Category> categoryList = this.categoryDao.findAll() ;
		List<CategoryTree> tree =  getChildren(null,categoryList);
		setLeaf(tree) ;
		
		return tree;
	}
	
	@SuppressWarnings("unchecked")
	private void setLeaf(List<CategoryTree> tree){
		for(CategoryTree t : tree){
			if(t.getChildren() == null || t.getChildren().size() == 0){
				t.setLeaf(true) ;
			}else{
				setLeaf((List<CategoryTree>)t.getChildren()) ;
			}
		}
	}

	/**
	 * 构建节点树
	 * @param object
	 * @return
	 */
	private List<CategoryTree> getChildren(Long parentId,List<Category> categoryList) {
		List<CategoryTree> childTree = new ArrayList<CategoryTree>();
		 for (Category tmp : categoryList) {
			 if (parentId == null) {
	                if (tmp.getParentId() == null) {
	                	CategoryTree node = new CategoryTree() ;
	                	node.setId(tmp.getId()) ;
	                	node.setText(tmp.getName()) ;
	                    node.setChildren(getChildren(tmp.getId(), categoryList));
	                    childTree.add(node);
	                }
	            } else {
	                if (tmp.getParentId() != null && tmp.getParentId().equals(parentId)) {
	                	CategoryTree node = new CategoryTree();
	                	node.setId(tmp.getId()) ;
	                	node.setText(tmp.getName()) ;
	                    node.setChildren(getChildren(tmp.getId(), categoryList));
	                    childTree.add(node);
	                }
	            }
	        }
		
		return childTree;
	}

	/**
	 * 前台-查询分类树
	 */
	@Override
	public List<CategoryDto> findCategoryDtoTree() {
		List<Category> categoryList = this.categoryDao.findAll() ;
		List<CategoryDto> tree =  getChildren2(null,categoryList);
		return tree;
	}

	private List<CategoryDto> getChildren2(Long parentId,
			List<Category> categoryList) {
		List<CategoryDto> childTree = new ArrayList<CategoryDto>();
		 for (Category tmp : categoryList) {
			 if (parentId == null) {
	                if (tmp.getParentId() == null) {
	                	CategoryDto node = new CategoryDto() ;
	                	node.setId(tmp.getId()) ;
	                	node.setName(tmp.getName()) ;
	                	node.setSort(tmp.getSort()) ;
	                	node.setParentId(tmp.getParentId()) ;
	                	node.setProductDetailTpl(tmp.getProductDetailTpl()) ;
	                    node.setChildren(getChildren2(tmp.getId(), categoryList));
	                    childTree.add(node);
	                }
	            } else {
	                if (tmp.getParentId() != null && tmp.getParentId().equals(parentId)) {
	                	CategoryDto node = new CategoryDto();
	                	node.setId(tmp.getId()) ;
	                	node.setName(tmp.getName()) ;
	                	node.setSort(tmp.getSort()) ;
	                	node.setParentId(tmp.getParentId()) ;
	                	node.setProductDetailTpl(tmp.getProductDetailTpl()) ;
	                    node.setChildren(getChildren2(tmp.getId(), categoryList));
	                    childTree.add(node);
	                }
	            }
	        }
		
		 return childTree;
	}
}

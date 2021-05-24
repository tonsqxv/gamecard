package com.macower.news.biz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.news.dao.NewsCategoryDaoImpl;
import com.macower.news.entity.NewsCategory;
import com.macower.news.entity.NewsCategoryTree;

@Service
public class NewsCategoryBizImpl extends BaseBiz implements NewsCategoryBiz {

	@Autowired
	private NewsCategoryDaoImpl newsCategoryDao ;
	
	@Override
	public Page<NewsCategory> findPageBy(NewsCategory obj, Integer pageNo,
			Integer pageSize) {
		return newsCategoryDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	public List<NewsCategory> findBy(NewsCategory obj){
		return this.newsCategoryDao.findBy(obj) ;
	}

	@Override
	public void save(NewsCategory obj) {
		NewsCategory param = new NewsCategory() ;
		param.setName(obj.getName()) ;
		int count  = this.newsCategoryDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getName()+"已经存在") ;
		}
		if(obj.getParentId() != null && obj.getParentId() == 0){
			obj.setParentId(null) ;
		}
		newsCategoryDao.save(obj) ;

	}

	@Override
	public void update(NewsCategory obj) {
		NewsCategory old = this.newsCategoryDao.get(obj.getId()) ;
		if(!old.getName().equals(obj.getName())){
			NewsCategory param = new NewsCategory() ;
			param.setName(obj.getName()) ;
			int count  = this.newsCategoryDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getName()+"已经存在") ;
			}
		}
		old.setName(obj.getName()) ;
		newsCategoryDao.update(old) ;

	}

	@Override
	public void deletes(String ids) {
		this.newsCategoryDao.deleteByIds(ids) ;
	}

	/**
	 * 获取前台展示分类列表
	 */
	@Override
	public List<NewsCategory> getNewsCategoryList(Integer size) {
		//分类
		DetachedCriteria dc = DetachedCriteria.forClass(NewsCategory.class) ;
		Page<NewsCategory> page =  this.newsCategoryDao.findPageBy(dc, 0, size,"sort",true) ;
		return (List<NewsCategory>)page.getData() ;
	}

	@Override
	public NewsCategory get(Long id) {
		return this.newsCategoryDao.get(id) ;
	}

	/**
	 * 后台查询分类树
	 */
	@Override
	public List<NewsCategoryTree> findNewsCategoryTree() {
		List<NewsCategory> NewsCategoryList = this.newsCategoryDao.findAll() ;
		List<NewsCategoryTree> tree =  getChildren(null,NewsCategoryList);
		setLeaf(tree) ;
		
		return tree;
	}
	
	@SuppressWarnings("unchecked")
	private void setLeaf(List<NewsCategoryTree> tree){
		for(NewsCategoryTree t : tree){
			if(t.getChildren() == null || t.getChildren().size() == 0){
				t.setLeaf(true) ;
			}else{
				setLeaf((List<NewsCategoryTree>)t.getChildren()) ;
			}
		}
	}

	/**
	 * 构建节点树
	 * @param object
	 * @return
	 */
	private List<NewsCategoryTree> getChildren(Long parentId,List<NewsCategory> NewsCategoryList) {
		List<NewsCategoryTree> childTree = new ArrayList<NewsCategoryTree>();
		 for (NewsCategory tmp : NewsCategoryList) {
			 if (parentId == null) {
	                if (tmp.getParentId() == null) {
	                	NewsCategoryTree node = new NewsCategoryTree() ;
	                	node.setId(tmp.getId()) ;
	                	node.setText(tmp.getName()) ;
	                    node.setChildren(getChildren(tmp.getId(), NewsCategoryList));
	                    childTree.add(node);
	                }
	            } else {
	                if (tmp.getParentId() != null && tmp.getParentId().equals(parentId)) {
	                	NewsCategoryTree node = new NewsCategoryTree();
	                	node.setId(tmp.getId()) ;
	                	node.setText(tmp.getName()) ;
	                    node.setChildren(getChildren(tmp.getId(), NewsCategoryList));
	                    childTree.add(node);
	                }
	            }
	        }
		
		return childTree;
	}

}

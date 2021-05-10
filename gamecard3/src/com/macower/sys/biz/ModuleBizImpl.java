package com.macower.sys.biz;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.entity.Page;
import com.macower.sys.dao.ModuleDaoImpl;
import com.macower.sys.entity.AsyncTree;
import com.macower.sys.entity.Module;

@Service
public class ModuleBizImpl implements ModuleBiz {

	@Autowired
	private ModuleDaoImpl moduleDao ;
	

	@Override
	public Page<Module> findPageBy(Module obj, Integer pageNo,
			Integer pageSize) {
		return moduleDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Module> findBy(Module obj) {
		return moduleDao.findBy(obj) ;
	}


	/**
	 * 异步树
	 */
	public List<AsyncTree> findByParentModuleId(Long id) {
		Module param = new Module() ;
		param.setParentId(id) ;
		List<Module> modules = this.moduleDao.findBy(param) ;
		
		//
		List<AsyncTree> tree = new ArrayList<AsyncTree>() ;
		for(int i = 0 ; i< modules.size() ; i++){
			Module m = modules.get(i) ;
			AsyncTree node = new AsyncTree() ;
			node.setId(m.getModuleId()) ;
			node.setText(m.getModuleName()) ;
			if(m.getIsLeaf() == null){
				node.setLeaf(false) ;
			}else{
				if("1".equals(m.getIsLeaf().toString())){
					node.setLeaf(true) ;
				}else{
					node.setLeaf(false) ;
				}
			}
			tree.add(node) ;
		}
		
		return tree;
	}

	


}

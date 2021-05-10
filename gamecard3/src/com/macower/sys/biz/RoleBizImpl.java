package com.macower.sys.biz;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.sys.dao.ModuleDaoImpl;
import com.macower.sys.dao.RoleDaoImpl;
import com.macower.sys.dao.RoleModuleDaoImpl;
import com.macower.sys.entity.Module;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.RoleModule;
import com.macower.sys.entity.SyncTree;

@Service
public class RoleBizImpl implements RoleBiz {

	@Autowired
	private RoleDaoImpl roleDao ;
	
	@Autowired
	private ModuleDaoImpl moduleDao ;
	
	@Autowired
	private RoleModuleDaoImpl roleModuleDao ;
	

	@Override
	public Page<Role> findPageBy(Role obj, Integer pageNo,
			Integer pageSize) {
		return roleDao.findPageBy(obj, pageNo, pageSize) ;
	}
	

	@Override
	public List<Role> findBy(Role obj) {
		return roleDao.findBy(obj) ;
	}


	@Override
	public void save(Role obj) {
		Role param = new Role() ;
		param.setRoleName(obj.getRoleName().trim()) ;
		int count  = this.roleDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getRoleName()+"已经存在") ;
		}
		obj.setRoleName(obj.getRoleName().trim()) ;
		roleDao.save(obj) ;

	}

	@Override
	public void update(Role obj,Long[] roleModuleIds) {
		Role old = roleDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		if(!old.getRoleName().equals(obj.getRoleName().trim())){
			Role param = new Role() ;
			param.setRoleName(obj.getRoleName().trim()) ;
			int count  = this.roleDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getRoleName()+"已经存在") ;
			}
		}
		old.setRoleName(obj.getRoleName().trim()) ;
		old.setDesc(obj.getDesc()) ;
		
		roleDao.update(old) ;
		
		if(roleModuleIds.length == 0){
			return ;
		}
		List<RoleModule> roleModuleLisat = new ArrayList<RoleModule>();
		for(int i = 0 ; i <roleModuleIds.length ; i++){
			RoleModule rm = new RoleModule() ;
			rm.setRoleId(old.getId()) ;
			rm.setModuleId(roleModuleIds[i]) ;
			roleModuleLisat.add(rm) ;
		}
		RoleModule param = new RoleModule() ;
		param.setRoleId(old.getId()) ;
		List<RoleModule> oldRoleModuleList = this.roleModuleDao.findBy(param) ;
		
		//删除原有的role-module
		this.roleModuleDao.deleteBatch(oldRoleModuleList) ;
		//添加新的role-module
		for(RoleModule m :roleModuleLisat){
			this.roleModuleDao.save(m) ;
		}
		
	}

	@Override
	public void deletes(String ids) {
		
		this.roleDao.deleteByIds(ids) ;
	}

	@Override
	public Role get(Long id) {
		return this.roleDao.get(id) ;
	}

	/**
	 * 根据角色id查找同步树
	 */
	public List<SyncTree> findRoleModulesByModuleAndRole(Long roleId) {
		List<Module> modules = this.moduleDao.findBy(new Module()) ;
		List<Long> roleModuleIds = this.roleModuleDao.findRoleModuleIds(roleId) ;
		return getChildren(null, modules, roleModuleIds);
	}


	/**
	 * 同部树构建节点数
	 * @param parentId
	 * @param modules
	 * @param checkedIds
	 * @return
	 */
	private List<SyncTree> getChildren(Long parentId, List<Module> modules,
			List<Long> checkedIds) {
		List<SyncTree> childTree = new ArrayList<SyncTree>();
		 for (Module moduleTmp : modules) {
	            if (parentId == null) {
	                if (moduleTmp.getParentId() == null) {
	                	SyncTree node = new SyncTree() ;
	                	node.setId(moduleTmp.getModuleId()) ;
	                	node.setText(moduleTmp.getModuleName()) ;
	                	if(moduleTmp.getIsLeaf() == null){
	        				node.setLeaf(false) ;
	        			}else{
	        				if("1".equals(moduleTmp.getIsLeaf().toString())){
	        					node.setLeaf(true) ;
	        				}else{
	        					node.setLeaf(false) ;
	        				}
	        			}
	                	node.setChecked(checkedIds.contains(moduleTmp.getModuleId()));
	                    node.setChildren(getChildren(moduleTmp.getModuleId(), modules, checkedIds));
	                    childTree.add(node);
	                }
	            } else {
	                if (moduleTmp.getParentId() != null && moduleTmp.getParentId().equals(parentId)) {
	                	SyncTree node = new SyncTree();
	                	node.setId(moduleTmp.getModuleId()) ;
	                	node.setText(moduleTmp.getModuleName()) ;
	                	if(moduleTmp.getIsLeaf() == null){
	        				node.setLeaf(false) ;
	        			}else{
	        				if("1".equals(moduleTmp.getIsLeaf().toString())){
	        					node.setLeaf(true) ;
	        				}else{
	        					node.setLeaf(false) ;
	        				}
	        			}
	                    node.setChecked(checkedIds.contains(moduleTmp.getModuleId()));
	                    node.setChildren(getChildren(moduleTmp.getModuleId(), modules, checkedIds));
	                    childTree.add(node);
	                }
	            }
	        }
		
		return childTree;
	}

}

package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.RoleConstant;
import com.lawencon.community.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao<Role>{
	
	public String getRoleMemberId() {
		String sql = "SELECT id FROM roles WHERE role_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", RoleConstant.MEMBER.getRoleCode()).getSingleResult();
		String id = result.toString();
		return id;
	}
	
	public String getRoleAdminId() {
		String sql = "SELECT id FROM roles WHERE role_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", RoleConstant.ADMIN.getRoleCode()).getSingleResult();
		String id = result.toString();
		return id;
	}
	
	public String getRolePremiumId() {
		String sql = "SELECT id FROM roles WHERE role_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", RoleConstant.PREMIUM.getRoleCode()).getSingleResult();
		String id = result.toString();
		return id;
	}
	
	public Integer isRoleCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM roles where role_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isRoleNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM roles where role_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}

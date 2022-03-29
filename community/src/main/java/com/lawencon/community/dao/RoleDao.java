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
}

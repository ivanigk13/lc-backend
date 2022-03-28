package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.RoleConstant;
import com.lawencon.community.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao<Role>{
	
	public String getRoleMemberId() {
		String sql = "SELECT id FROM roles WHERE role_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", RoleConstant.MEMBER.getRoleCode());
		Object[] obj = (Object[]) result;
		String id = obj[0].toString();
		return id;
	}
}

package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User>{

	public User getByEmail(String email) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u.id, u.role_id, r.role_code, u.email, u.version, u.is_active, u.password ");
		sb.append("FROM users as u ");
		sb.append("INNER JOIN roles as r ON u.role_id = r.id ");
		sb.append("WHERE u.email = :email");
		Object result = createNativeQuery(sb.toString()).setParameter("email", email).getSingleResult();
		Object[] obj = (Object[]) result;
		User user = new User();
		user.setId(obj[0].toString());
		
		Role role = new Role();
		role.setId(obj[1].toString());
		role.setRoleCode(obj[2].toString());
		user.setRole(role);
		
		user.setEmail(obj[3].toString());
		user.setPassword(obj[6].toString());
		user.setVersion(Integer.valueOf(obj[4].toString()));
		user.setIsActive(Boolean.valueOf(obj[5].toString()));
		
		return user;
	}
}

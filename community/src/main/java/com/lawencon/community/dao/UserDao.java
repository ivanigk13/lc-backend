package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.RoleConstant;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Repository
public class UserDao extends AbstractJpaDao<User>{

	public User getByEmail(String email) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u.id, u.role_id, r.role_code, u.email, u.version, u.is_active, u.password, u.created_at ");
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
		user.setCreatedAt(((Timestamp) obj[7]).toLocalDateTime());
		
		return user;
	}
	
	public boolean updateSubscription (String id, Integer duration, String updateUser) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE users ");
		sb.append("SET subcription_end = now() + INTERVAL ");		
		sb.append(String.format("'%s MONTH', ", duration));
		sb.append("role_id = (SELECT id FROM roles WHERE role_code = :code), ");
		sb.append("updated_by = :userId, ");
		sb.append("updated_at = now() ");
		sb.append("WHERE id = :id");
		
		Integer result = createNativeQuery(sb.toString())
						.setParameter("code", RoleConstant.PREMIUM.getRoleCode())
						.setParameter("userId", updateUser)
						.setParameter("id", id)
						.executeUpdate();
		
		return result > 0 ;
	}
	
	
	
	public Integer isEmailExist(String email) {
		String sql = "SELECT COUNT(id) FROM users WHERE email = :email";
		Object result = createNativeQuery(sql).setParameter("email", email).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public SearchQuery<User> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<User> sq = new SearchQuery<>();
		List<User> data = null;

		if (startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		} else {
			if (query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();

				sq.setData(data);
				sq.setCount(count);
			} else {
				return getAll(query, startPage, maxPage, "email");
			}
		}

		return sq;
	}
}

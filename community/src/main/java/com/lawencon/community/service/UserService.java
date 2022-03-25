package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.user.DeleteUserDtoRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByIdUserDtoRes;
import com.lawencon.community.dto.user.GetUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoDataRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends BaseCommunityService implements UserDetailsService{
	
	private final UserDao userDao;
	private final String idCreate = "1";
	
	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
		User user = new User();
		Role role = new Role();
		role.setId(data.getRoleId());
		user.setRole(role);
		user.setEmail(data.getEmail());
		user.setPassword(data.getPassword());
		user.setCreatedBy(idCreate);
		
		begin();
		user = userDao.save(user);
		commit();
		
		InsertUserDtoDataRes userDataRes = new InsertUserDtoDataRes();
		userDataRes.setId(user.getId());
		
		InsertUserDtoRes userRes = new InsertUserDtoRes();
		userRes.setMsg("Insert Successfully");
		userRes.setData(userDataRes);
		
		return userRes;
	}

	public UpdateUserDtoRes update(UpdateUserDtoReq data) throws Exception {
		User user = userDao.getById(data.getId());
		Role role = new Role();
		user.setRole(role);
		user.setPassword(data.getPassword());
		user.setUpdatedBy(getId());
		user.setVersion(data.getVersion());
		user.setIsActive(data.getIsActive());
		
		begin();
		user = userDao.save(user);
		commit();
		
		UpdateUserDtoDataRes userDataRes = new UpdateUserDtoDataRes();
		userDataRes.setVersion(user.getVersion());
		
		UpdateUserDtoRes userRes = new UpdateUserDtoRes();
		userRes.setMsg("Update Successfully");
		userRes.setData(userDataRes);
		
		return userRes;
	}
	
	public GetByIdUserDtoRes getById(String id) throws Exception {
		User user = userDao.getById(id);
		GetUserDtoDataRes userDataRes = new GetUserDtoDataRes();
		userDataRes.setId(user.getId());
		userDataRes.setRoleId(user.getRole().getId());
		userDataRes.setRoleName(user.getRole().getRoleName());
		userDataRes.setEmail(user.getEmail());
		userDataRes.setPassword(user.getPassword());
		userDataRes.setVersion(user.getVersion());
		userDataRes.setIsActive(user.getIsActive());
		
		GetByIdUserDtoRes userRes = new GetByIdUserDtoRes();
		userRes.setData(userDataRes);
		
		return userRes;
	}
	
	public GetAllUserDtoRes getAll(Integer start, Integer max) throws Exception {
		List<User> users;
		if(start==null) users = userDao.getAll();
		else users = userDao.getAll(start, max);
		
		List<GetUserDtoDataRes> data = new ArrayList<GetUserDtoDataRes>();
		
		users.forEach(user -> {
			GetUserDtoDataRes userDataRes = new GetUserDtoDataRes();
			userDataRes.setId(user.getId());
			userDataRes.setRoleId(user.getRole().getId());
			userDataRes.setRoleName(user.getRole().getRoleName());
			userDataRes.setEmail(user.getEmail());
			userDataRes.setPassword(user.getPassword());
			userDataRes.setVersion(user.getVersion());
			userDataRes.setIsActive(user.getIsActive());
			
			data.add(userDataRes);
		});
		
		GetAllUserDtoRes userRes = new GetAllUserDtoRes();
		userRes.setData(data);
		
		return userRes;
	}
	
	public DeleteUserDtoRes deleteById(String id) throws Exception {
		begin();
		userDao.deleteById(id);
		commit();
		
		DeleteUserDtoRes userRes = new DeleteUserDtoRes();
		userRes.setMsg("Delete Successfully");
		
		return userRes;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username==null) {
			throw new UsernameNotFoundException("invalid email and password");
		}
		User user = userDao.getByEmail(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
 }

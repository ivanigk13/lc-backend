package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dto.role.DeleteRoleDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByIdRoleDtoRes;
import com.lawencon.community.dto.role.GetRoleDtoDataRes;
import com.lawencon.community.dto.role.InsertRoleDtoDataRes;
import com.lawencon.community.dto.role.InsertRoleDtoReq;
import com.lawencon.community.dto.role.InsertRoleDtoRes;
import com.lawencon.community.dto.role.UpdateRoleDtoDataRes;
import com.lawencon.community.dto.role.UpdateRoleDtoReq;
import com.lawencon.community.dto.role.UpdateRoleDtoRes;
import com.lawencon.community.model.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService extends BaseCommunityService {
	
	private final RoleDao roleDao;
	
	public InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception {
		Role role = new Role();
		role.setRoleCode(data.getRoleCode());
		role.setRoleName(data.getRoleName());
		role.setCreatedBy(getId());
		
		begin();
		role = roleDao.save(role);
		commit();
		
		InsertRoleDtoDataRes roleDataRes = new InsertRoleDtoDataRes();
		roleDataRes.setId(role.getId());
		
		InsertRoleDtoRes roleRes = new InsertRoleDtoRes();
		roleRes.setMsg("Insert Successfully");
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception {
		Role role = roleDao.getById(data.getId());
		role.setRoleName(data.getRoleName());
		role.setVersion(data.getVersion());
		role.setIsActive(data.getIsActive());
		role.setUpdatedBy(getId());
		
		begin();
		role = roleDao.save(role);
		commit();
		
		UpdateRoleDtoDataRes roleDataRes = new UpdateRoleDtoDataRes();
		roleDataRes.setVersion(role.getVersion());
		
		UpdateRoleDtoRes roleRes = new UpdateRoleDtoRes();
		roleRes.setMsg("Update Successfully");
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public GetByIdRoleDtoRes getById(String id) throws Exception {
		Role role = roleDao.getById(id);
		GetRoleDtoDataRes roleDataRes = new GetRoleDtoDataRes();
		roleDataRes.setId(role.getId());
		roleDataRes.setRoleCode(role.getRoleCode());
		roleDataRes.setRoleName(role.getRoleName());
		roleDataRes.setVersion(role.getVersion());
		roleDataRes.setIsActive(role.getIsActive());
		
		GetByIdRoleDtoRes roleRes = new GetByIdRoleDtoRes();
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public GetAllRoleDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Role> roles;
		if(start == null) roles = roleDao.getAll();
		else roles = roleDao.getAll(start, max);
		
		List<GetRoleDtoDataRes> data = new ArrayList<GetRoleDtoDataRes>();
		
		roles.forEach(role -> {
			GetRoleDtoDataRes roleDataRes = new GetRoleDtoDataRes();
			roleDataRes.setId(role.getId());
			roleDataRes.setRoleCode(role.getRoleCode());
			roleDataRes.setRoleName(role.getRoleName());
			roleDataRes.setVersion(role.getVersion());
			roleDataRes.setIsActive(role.getIsActive());
			
			data.add(roleDataRes);
		});
		
		GetAllRoleDtoRes roleRes = new GetAllRoleDtoRes();
		roleRes.setData(data);
		
		return roleRes;
	}
	
	

	public DeleteRoleDtoRes deleteById(String id) throws Exception {
		begin();
		roleDao.deleteById(id);
		commit();
		
		DeleteRoleDtoRes roleRes = new DeleteRoleDtoRes();
		roleRes.setMsg("Delete Successfully");
		
		return roleRes;
	} 

}

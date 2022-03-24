package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dto.role.DeleteRoleDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByRoleIdDtoRes;
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
public class RoleService extends BaseService {
	
	private final RoleDao roleDao;
	
	public InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception {
		Role role = new Role();
		role.setRoleCode(data.getRoleCode());
		role.setRoleName(data.getRoleName());
		role.setCreatedBy("Created By");
		
		begin();
		role = roleDao.save(role);
		commit();
		
		InsertRoleDtoDataRes roleDataRes = new InsertRoleDtoDataRes();
		roleDataRes.setId(role.getId());
		
		InsertRoleDtoRes roleRes = new InsertRoleDtoRes();
		roleRes.setMsg("Insert Success!");
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception {
		Role role = roleDao.getById(data.getId());
		role.setRoleName(data.getRoleName());
		role.setVersion(data.getVersion());
		role.setIsActive(data.getIsActive());
		role.setUpdatedBy("Role Updated By");
		
		begin();
		role = roleDao.save(role);
		commit();
		
		UpdateRoleDtoDataRes roleDataRes = new UpdateRoleDtoDataRes();
		roleDataRes.setVersion(role.getVersion());
		
		UpdateRoleDtoRes roleRes = new UpdateRoleDtoRes();
		roleRes.setMsg("Update Success!");
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public GetByRoleIdDtoRes getById(String id) throws Exception {
		Role role = roleDao.getById(id);
		GetRoleDtoDataRes roleDataRes = new GetRoleDtoDataRes();
		roleDataRes.setId(role.getId());
		roleDataRes.setRoleCode(role.getRoleCode());
		roleDataRes.setRoleName(role.getRoleName());
		roleDataRes.setVersion(role.getVersion());
		roleDataRes.setIsActive(role.getIsActive());
		
		GetByRoleIdDtoRes roleRes = new GetByRoleIdDtoRes();
		roleRes.setData(roleDataRes);
		
		return roleRes;
	}

	public GetAllRoleDtoRes getAll() throws Exception {
		List<Role> roles = roleDao.getAll();
		
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
		roleRes.setMsg("Delete Success!");
		
		return roleRes;
	} 

}

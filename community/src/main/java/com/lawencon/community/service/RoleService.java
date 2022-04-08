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

	public GetByIdRoleDtoRes getById(String id) throws Exception {
		Role role = roleDao.getById(id);
		if(role != null) {
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
		
		throw new RuntimeException("Role Id doesn't exist");
	}
	
	public InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception {
		Role role = new Role();
		valBkNotExist(data.getRoleCode(), data.getRoleName());
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
		valBkNotExist(data.getRoleName());
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

	public DeleteRoleDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = roleDao.deleteById(id);
			commit();
			
			DeleteRoleDtoRes roleRes = new DeleteRoleDtoRes();
			if(isDeleted) {
				roleRes.setMsg("Delete Successfully");
			} else {
				roleRes.setMsg("Delete Unsuccessfully");
			}
			
			return roleRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	private void valBkNotExist(String roleCode, String roleName) {
		Integer flagCode = roleDao.isRoleCodeExist(roleCode);
		Integer flagName = roleDao.isRoleNameExist(roleName);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("Role Code or Role Name has existed");
		}
	}
	
	private void valBkNotExist(String roleName) {
		Integer flagName = roleDao.isRoleNameExist(roleName);
		if(flagName == 1) {
			throw new RuntimeException("Role Name has existed");
		}
	}

}

package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.email.EmailTemplate;
import com.lawencon.community.dto.user.DeleteUserDtoRes;
import com.lawencon.community.dto.user.ForgotPasswordReq;
import com.lawencon.community.dto.user.ForgotPasswordRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByIdUserDtoRes;
import com.lawencon.community.dto.user.GetUserDtoDataRes;
import com.lawencon.community.dto.user.GetVerificationCodeRes;
import com.lawencon.community.dto.user.InsertUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.SubscriptionRes;
import com.lawencon.community.dto.user.UpdateUserDtoDataRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends BaseCommunityService implements UserDetailsService{
	
	private final UserDao userDao;
	private final RoleDao roleDao; 
	private final PasswordEncoder encoder;
	private final EmailSenderService emailSenderService;
	private final String idCreate = "1";

	
	public GetAllUserDtoRes getAll(String query, Integer start, Integer max) throws Exception {
		SearchQuery<User> users = userDao.findAll(query, start, max);
		
		List<GetUserDtoDataRes> data = new ArrayList<GetUserDtoDataRes>();
		
		users.getData().forEach(user -> {
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
		userRes.setRows(userDao.countAll());
		
		return userRes;
	}
	
	public GetByIdUserDtoRes getById(String id) throws Exception {
		User user = userDao.getById(id);
		if(user != null) {
			GetUserDtoDataRes userDataRes = new GetUserDtoDataRes();
			userDataRes.setId(user.getId());
			userDataRes.setRoleId(user.getRole().getId());
			userDataRes.setRoleName(user.getRole().getRoleName());
			userDataRes.setEmail(user.getEmail());
			userDataRes.setPassword(user.getPassword());
			userDataRes.setCreatedAt(user.getCreatedAt());
			userDataRes.setVersion(user.getVersion());
			userDataRes.setIsActive(user.getIsActive());
			
			GetByIdUserDtoRes userRes = new GetByIdUserDtoRes();
			userRes.setData(userDataRes);
			
			return userRes;
		}
		
		throw new RuntimeException("User Id doesn't exist");
	}
	
	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
		User user = new User();
		Role role = new Role();
		role.setId(roleDao.getRoleMemberId());
//		role.setId(roleDao.getRoleAdminId());
		user.setRole(role);
		valBkNotExist(data.getEmail());
		user.setEmail(data.getEmail());
		user.setPassword(encoder.encode(data.getPassword()));
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
		role.setId(data.getRoleId());
		user.setRole(role);
		user.setPassword(encoder.encode(data.getPassword()));
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
	
	public SubscriptionRes updateSubscription(String id, Integer duration, String updateUser) throws Exception {
		begin();
		boolean result = userDao.updateSubscription(id, duration, updateUser);
		commit();
		SubscriptionRes subscriptionRes = new SubscriptionRes();
		if(result) {
			subscriptionRes.setMsg("Update Successfully");	
		} else {
			subscriptionRes.setMsg("Update Failed");
		}				
		return subscriptionRes;
	}
	
	public DeleteUserDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = userDao.deleteById(id);
			commit();
			
			DeleteUserDtoRes userRes = new DeleteUserDtoRes();
			if(isDeleted) {
				userRes.setMsg("Delete Successfully");
			} else {
				userRes.setMsg("Delete Unsuccessfully");
			}
			
			return userRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public ForgotPasswordRes forgotPassword(ForgotPasswordReq data) throws Exception, UsernameNotFoundException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		User user = userDao.getByEmail(data.getEmail());
		
		ForgotPasswordRes forgotPasswordRes = new ForgotPasswordRes();
		if(user != null) {
			String passwordGenerate = generateCode(8);
			String encodePass = encoder.encode(passwordGenerate);
			user.setPassword(encodePass);
			user.setUpdatedBy(user.getCreatedBy());
			
			begin();
			User userSave = userDao.save(user);
			commit();
			
			EmailTemplate emailTemplate= new EmailTemplate();
			emailTemplate.setFrom("lawversproject@gmail.com");
			emailTemplate.setSubject("New Password for Login");
			emailTemplate.setTo(userSave.getEmail());
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("newPassword", passwordGenerate);
			emailTemplate.setModel(model);
			
			executorService.submit(() -> {
				emailSenderService.sendMessage("EmailSendNewPassword.flth", emailTemplate);			
			});
			executorService.shutdown();
			
			forgotPasswordRes.setMsg("Check Your Email");
		}
		
		return forgotPasswordRes;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username==null) {
			throw new UsernameNotFoundException("invalid email and password");
		}
		User user = userDao.getByEmail(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
	
	public GetVerificationCodeRes getVerificationCode(String email) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		String code = generateVerificationCode(6);
		EmailTemplate emailTemplate= new EmailTemplate();
		emailTemplate.setFrom("lawversproject@gmail.com");
		emailTemplate.setSubject("Verification Code For Register");
		emailTemplate.setTo(email);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("newPassword", code);
		emailTemplate.setModel(model);
		executorService.submit(() -> {
			emailSenderService.sendMessage("EmailSendNewPassword.flth", emailTemplate);			
		});
		executorService.shutdown();
		GetVerificationCodeRes getCodeRes = new GetVerificationCodeRes();
		getCodeRes.setVerificationCode(Integer.valueOf(code));
		getCodeRes.setMsg("Check your email for verification code");
		return getCodeRes;
	}
	
	private void valBkNotExist(String email) {
		Integer flag = userDao.isEmailExist(email);
		if(flag==1) {
			throw new RuntimeException("Email has existed");
		}
	}
	
 }

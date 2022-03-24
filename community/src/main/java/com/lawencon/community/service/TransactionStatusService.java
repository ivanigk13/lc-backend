package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.TransactionStatusDao;
import com.lawencon.community.dto.transactionstatus.GetAllTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.GetByIdTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.GetTransactionStatusDtoDataRes;
import com.lawencon.community.dto.transactionstatus.InsertTransactionStatusDtoDataRes;
import com.lawencon.community.dto.transactionstatus.InsertTransactionStatusDtoReq;
import com.lawencon.community.dto.transactionstatus.InsertTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.UpdateTransactionStatusDtoDataRes;
import com.lawencon.community.dto.transactionstatus.UpdateTransactionStatusDtoReq;
import com.lawencon.community.dto.transactionstatus.UpdateTransactionStatusDtoRes;
import com.lawencon.community.model.TransactionStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionStatusService extends BaseService{

	private final TransactionStatusDao transactionStatusDao;
	
	public InsertTransactionStatusDtoRes insert(InsertTransactionStatusDtoReq data) throws Exception {
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setStatusName(data.getStatusName());
		transactionStatus.setStatusCode(data.getStatusCode());
		
		begin();
		TransactionStatus transactionStatusInsert = transactionStatusDao.save(transactionStatus);
		InsertTransactionStatusDtoDataRes transactionStatusId = new InsertTransactionStatusDtoDataRes();
		transactionStatusId.setId(transactionStatusInsert.getId());
		
		InsertTransactionStatusDtoRes result = new InsertTransactionStatusDtoRes();
		result.setData(transactionStatusId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}
	
	public UpdateTransactionStatusDtoRes update(UpdateTransactionStatusDtoReq data) throws Exception{
		TransactionStatus transactionStatus = transactionStatusDao.getById(data.getId());
		transactionStatus.setStatusName(data.getStatusName());
		transactionStatus.setVersion(data.getVersion());
		transactionStatus.setIsActive(data.getIsActive());
		
		begin();
		TransactionStatus transactionStatusUpdate = transactionStatusDao.save(transactionStatus);
		
		UpdateTransactionStatusDtoDataRes transactionStatusVersion = new UpdateTransactionStatusDtoDataRes();
		transactionStatusVersion.setVersion(transactionStatusUpdate.getVersion());
		
		UpdateTransactionStatusDtoRes result = new UpdateTransactionStatusDtoRes();
		result.setData(transactionStatusVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public GetAllTransactionStatusDtoRes getAll() throws Exception{
		List<TransactionStatus> transactionStatuses = transactionStatusDao.getAll();
		List<GetTransactionStatusDtoDataRes> data = new ArrayList<>();
		
		transactionStatuses.forEach(list -> {
			GetTransactionStatusDtoDataRes transactionStatus = new GetTransactionStatusDtoDataRes();
			transactionStatus.setId(list.getId());
			transactionStatus.setStatusName(list.getStatusName());
			transactionStatus.setStatusCode(list.getStatusCode());
			transactionStatus.setVersion(list.getVersion());
			transactionStatus.setIsActive(list.getIsActive());
			data.add(transactionStatus);
		});
		
		GetAllTransactionStatusDtoRes result = new GetAllTransactionStatusDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetByIdTransactionStatusDtoRes getById(String id) throws Exception{
		TransactionStatus TransactionStatus = transactionStatusDao.getById(id);
		
		GetTransactionStatusDtoDataRes transactionStatusData = new GetTransactionStatusDtoDataRes();
		transactionStatusData.setId(TransactionStatus.getId());
		transactionStatusData.setStatusName(TransactionStatus.getStatusName());
		transactionStatusData.setStatusCode(TransactionStatus.getStatusCode());
		transactionStatusData.setVersion(TransactionStatus.getVersion());
		transactionStatusData.setIsActive(TransactionStatus.getIsActive());
		
		GetByIdTransactionStatusDtoRes result = new GetByIdTransactionStatusDtoRes();
		result.setData(transactionStatusData);		
		
		return result;		
	}
}

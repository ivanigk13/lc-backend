package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingVoterDao;
import com.lawencon.community.dto.pollingvoter.DeletePollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.GetAllPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.GetByIdPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.GetPollingVoterDtoDataRes;
import com.lawencon.community.dto.pollingvoter.InsertPollingVoterDtoDataRes;
import com.lawencon.community.dto.pollingvoter.InsertPollingVoterDtoReq;
import com.lawencon.community.dto.pollingvoter.InsertPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.UpdatePollingVoterDtoDataRes;
import com.lawencon.community.dto.pollingvoter.UpdatePollingVoterDtoReq;
import com.lawencon.community.dto.pollingvoter.UpdatePollingVoterDtoRes;
import com.lawencon.community.model.PollingDetail;
import com.lawencon.community.model.PollingVoter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollingVoterService extends BaseCommunityService {

	private final PollingVoterDao pollingVoterDao;

	public InsertPollingVoterDtoRes insert(InsertPollingVoterDtoReq data) throws Exception {
		PollingVoter pollingVoter = new PollingVoter();
		PollingDetail pollingDetail = new PollingDetail();
		pollingDetail.setId(data.getPollingDetailId());
		pollingVoter.setPollingDetail(pollingDetail);
		pollingVoter.setCreatedBy(getId());

		begin();
		pollingVoter = pollingVoterDao.save(pollingVoter);
		commit();

		InsertPollingVoterDtoDataRes pollingVoterDataRes = new InsertPollingVoterDtoDataRes();
		pollingVoterDataRes.setId(pollingVoter.getId());

		InsertPollingVoterDtoRes pollingVoterRes = new InsertPollingVoterDtoRes();
		pollingVoterRes.setMsg("Insert Successfully");
		pollingVoterRes.setData(pollingVoterDataRes);

		return pollingVoterRes;
	}

	public UpdatePollingVoterDtoRes update(UpdatePollingVoterDtoReq data) throws Exception {
		PollingVoter pollingVoter = pollingVoterDao.getById(data.getId());
		PollingDetail pollingDetail = new PollingDetail();
		pollingDetail.setId(data.getPollingDetailId());
		pollingVoter.setPollingDetail(pollingDetail);
		pollingVoter.setUpdatedBy(getId());
		pollingVoter.setVersion(data.getVersion());
		pollingVoter.setIsActive(data.getIsActive());

		begin();
		pollingVoter = pollingVoterDao.save(pollingVoter);
		commit();

		UpdatePollingVoterDtoDataRes pollingVoterDataRes = new UpdatePollingVoterDtoDataRes();
		pollingVoterDataRes.setVersion(pollingVoter.getVersion());

		UpdatePollingVoterDtoRes pollingVoterRes = new UpdatePollingVoterDtoRes();
		pollingVoterRes.setMsg("Update Successfully");
		pollingVoterRes.setData(pollingVoterDataRes);

		return pollingVoterRes;
	}

	public GetByIdPollingVoterDtoRes getById(String id) throws Exception {
		PollingVoter pollingVoter = pollingVoterDao.getById(id);
		GetPollingVoterDtoDataRes pollingVoterDataRes = new GetPollingVoterDtoDataRes();
		pollingVoterDataRes.setId(pollingVoter.getId());
		pollingVoterDataRes.setPollingDetailId(pollingVoter.getPollingDetail().getId());
		pollingVoterDataRes.setVersion(pollingVoter.getVersion());
		pollingVoterDataRes.setIsActive(pollingVoter.getIsActive());

		GetByIdPollingVoterDtoRes pollingVoterRes = new GetByIdPollingVoterDtoRes();
		pollingVoterRes.setData(pollingVoterDataRes);

		return pollingVoterRes;
	}

	public GetAllPollingVoterDtoRes getAll(Integer start, Integer max) throws Exception {
		List<PollingVoter> pollingVoters;
		if (start == null) pollingVoters = pollingVoterDao.getAll();
		else pollingVoters = pollingVoterDao.getAll(start, max);

		List<GetPollingVoterDtoDataRes> data = new ArrayList<GetPollingVoterDtoDataRes>();

		pollingVoters.forEach(pollingVoter -> {
			GetPollingVoterDtoDataRes pollingVoterDataRes = new GetPollingVoterDtoDataRes();
			pollingVoterDataRes.setId(pollingVoter.getId());
			pollingVoterDataRes.setPollingDetailId(pollingVoter.getPollingDetail().getId());
			pollingVoterDataRes.setVersion(pollingVoter.getVersion());
			pollingVoterDataRes.setIsActive(pollingVoter.getIsActive());

			data.add(pollingVoterDataRes);
		});

		GetAllPollingVoterDtoRes pollingVoterRes = new GetAllPollingVoterDtoRes();
		pollingVoterRes.setData(data);

		return pollingVoterRes;
	}

	public GetAllPollingVoterDtoRes getPollingVoterByUserId(String userId) throws Exception {
		List<PollingVoter> pollingVoters = pollingVoterDao.getPollingVoterByUserId(userId);

		List<GetPollingVoterDtoDataRes> data = new ArrayList<GetPollingVoterDtoDataRes>();

		pollingVoters.forEach(pollingVoter -> {
			GetPollingVoterDtoDataRes pollingVoterDataRes = new GetPollingVoterDtoDataRes();
			pollingVoterDataRes.setId(pollingVoter.getId());
			pollingVoterDataRes.setPollingDetailId(pollingVoter.getPollingDetail().getId());
			pollingVoterDataRes.setVersion(pollingVoter.getVersion());
			pollingVoterDataRes.setIsActive(pollingVoter.getIsActive());

			data.add(pollingVoterDataRes);
		});

		GetAllPollingVoterDtoRes pollingVoterRes = new GetAllPollingVoterDtoRes();
		pollingVoterRes.setData(data);

		return pollingVoterRes;
	}
	
	public Integer getCountIdByHeaderId(String headerId, String userId) throws Exception {
		return pollingVoterDao.getCountIdByHeaderId(headerId, userId);		
	}

	public DeletePollingVoterDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = pollingVoterDao.deleteById(id);
			commit();
			
			DeletePollingVoterDtoRes pollingVoterRes = new DeletePollingVoterDtoRes();
			if(isDeleted) {
				pollingVoterRes.setMsg("Delete Successfully");
			} else {
				pollingVoterRes.setMsg("Delete Unsuccessfully");
			}
			
			return pollingVoterRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}

package com.lawencon.community.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.ActivityTypeConstant;
import com.lawencon.community.constant.TransactionStatusConstant;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.ActivityType;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.File;
import com.lawencon.community.model.TransactionStatus;

@Repository
public class ActivityDao extends AbstractJpaDao<Activity>{

	public List<Activity> getAllByUserIdActivity(String userId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE a.created_by = :id ");
		builder.append("ORDER BY a.id");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("id", userId)
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getPendingActivity() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, at.activity_type_name, a.transaction_status_id, ts.status_name, a.payment_file_id, ");
		builder.append("a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("WHERE ts.status_code = :status_code ");
		builder.append("ORDER BY a.id");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.PENDING.getStatusCode())
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[1].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[2].toString());
			transactionStatus.setStatusName(obj[3].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[4].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[5].toString());
			activity.setDateStart((Date)obj[6]);
			activity.setDateEnd((Date)obj[7]);
			activity.setTimeStart((Time)obj[8]);
			activity.setTimeEnd((Time)obj[9]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[10].toString())));
			activity.setLocation(obj[11].toString());
			activity.setVersion(Integer.valueOf(obj[12].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getApprovedEventActivity() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE ts.status_code = :status_code AND at.activity_type_code = :type_code ");
		builder.append("ORDER BY a.id");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.APPROVED.getStatusCode())
							.setParameter("type_code", ActivityTypeConstant.EVENT.getActivityTypeCode())
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getApprovedUserActivity(String userId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE ts.status_code = :status_code AND a.created_by = :user_id ");
		builder.append("ORDER BY a.id");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.APPROVED.getStatusCode())
							.setParameter("user_id", userId)
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getApprovedCourseActivity() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE ts.status_code = :status_code AND at.activity_type_code = :type_code ");
		builder.append("ORDER BY a.id");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.APPROVED.getStatusCode())
							.setParameter("type_code", ActivityTypeConstant.COURSE.getActivityTypeCode())
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getLastTwoEventActivity() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE ts.status_code = :status_code AND at.activity_type_code = :type_code ");
		builder.append("ORDER BY a.created_at DESC LIMIT 2");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.APPROVED.getStatusCode())
							.setParameter("type_code", ActivityTypeConstant.EVENT.getActivityTypeCode())
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
	
	public List<Activity> getLastTwoCourseActivity() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT a.id, a.file_id, c.category_name, at.activity_type_name, a.transaction_status_id, ts.status_name, ");
		builder.append("a.payment_file_id, a.activity_name, a.date_start, a.date_end, a.time_start, a.time_end, a.price, ");
		builder.append("a.location, a.version ");
		builder.append("FROM activity as a ");
		builder.append("INNER JOIN activity_type as at ON a.activity_type_id = at.id ");
		builder.append("INNER JOIN transaction_status as ts ON a.transaction_status_id = ts.id ");
		builder.append("INNER JOIN category as c ON a.category_id = c.id ");
		builder.append("WHERE ts.status_code = :status_code AND at.activity_type_code = :type_code ");
		builder.append("ORDER BY a.created_at DESC LIMIT 2");
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("status_code", TransactionStatusConstant.APPROVED.getStatusCode())
							.setParameter("type_code", ActivityTypeConstant.COURSE.getActivityTypeCode())
							.getResultList();
		List<Activity> activities = new ArrayList<Activity>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Activity activity = new Activity();
			activity.setId(obj[0].toString());
			
			File file = new File();
			file.setId(obj[1].toString());
			activity.setFile(file);
			
			Category category = new Category();
			category.setCategoryName(obj[2].toString());
			activity.setCategory(category);
			
			ActivityType activityType = new ActivityType();
			activityType.setActivityTypeName(obj[3].toString());
			activity.setActivityType(activityType);
			
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(obj[4].toString());
			transactionStatus.setStatusName(obj[5].toString());
			activity.setTransactionStatus(transactionStatus);
			
			File paymentFile = new File();
			paymentFile.setId(obj[6].toString());
			activity.setPaymentFile(paymentFile);
			
			activity.setActivityName(obj[7].toString());
			activity.setDateStart((Date)obj[8]);
			activity.setDateEnd((Date)obj[9]);
			activity.setTimeStart((Time)obj[10]);
			activity.setTimeEnd((Time)obj[11]);
			activity.setPrice(BigDecimal.valueOf(Long.valueOf(obj[12].toString())));
			activity.setLocation(obj[13].toString());
			activity.setVersion(Integer.valueOf(obj[14].toString()));
			
			activities.add(activity);
		}
		return activities;
	}
}

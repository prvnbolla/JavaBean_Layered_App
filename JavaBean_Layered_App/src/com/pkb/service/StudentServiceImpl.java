package com.pkb.service;

import com.pkb.bo.StudentBO;
import com.pkb.dao.StudentDao;
import com.pkb.dao.StudentDaoImpl;
import com.pkb.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {
	public String generateResult(StudentDTO dto)throws Exception {
		int total = 0;
		String result = null;
		float avg = 0.0f;
		StudentBO bo = null;
		StudentDao dao=null;
		int cnt=0;
		
		//use business logic to calculate total , avg, result 
		total=dto.getM1()+dto.getM2()+dto.getM3();
		avg=total/3;
		if(avg<35)
			result=dto.getSname()+"fail";
		else
			result=dto.getSname()+"Pass";
		
		//Prepare BO Class Object having persistable data
		bo=new StudentBO();
		bo.setSno(dto.getSno());
		bo.setSname(dto.getSname());
		bo.setTotal(total);
		bo.setAvg(avg);
		bo.setResult(result);
		//use DAO 
		
		dao=new StudentDaoImpl();
		cnt=dao.insert(bo);
		if(cnt==0)
			return result+"    registration failed";
		else
			return result+"    registration succeded";
	
	}

}

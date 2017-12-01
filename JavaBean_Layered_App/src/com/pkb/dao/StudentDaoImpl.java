package com.pkb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.pkb.bo.StudentBO;

public class StudentDaoImpl implements StudentDao {
	private static final String INSERT_LAYERED_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?,?)";
	//private static final String  GET_SNO_SEQ="SELECT LAYERED_SNO_SEQ.NEXTVAL FROM DUAL";
	
private Connection getPooledConnection()throws Exception{
		  InitialContext ic=null;
		  DataSource ds=null;
		  Connection con=null;
		  //create InitaialContext object
		  ic=new InitialContext();
		  //get DataSource obj from Jndi Registry
		  ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");
		  System.out.println(ds);
		  //get Pooled con obj from jdbc con pool
		  con=ds.getConnection();
		  System.out.println(con);
		  return con;
		  }

	@Override
	public int insert(StudentBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//Get Pooled JDBC Connection
		con=getPooledConnection();
		//con=ds.getConnection();
		//Create data source object
		ps=con.prepareStatement(INSERT_LAYERED_STUDENT_QUERY);
		
		//Set values to Uuery param
		
		ps.setInt(1, bo.getSno());
		ps.setString(2, bo.getSname());
		ps.setInt(3, bo.getTotal());
		ps.setFloat(4, bo.getAvg());
		ps.setString(5,bo.getResult());
		
		//execute query 
		
		result=ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

}

package com.pkb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pkb.dto.StudentDTO;
import com.pkb.service.StudentService;
import com.pkb.service.StudentServiceImpl;
import com.pkb.vo.StudentVO;

@WebServlet("/stdurl")
public class StudentControllerServlet extends HttpServlet {
 	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 		StudentVO vo=null;
 		String no=null,name=null,m1=null,m2=null,m3=null;
 		StudentService service=null;
 		StudentDTO dto=null;
 		String result=null;
 		PrintWriter pw=null;
 		
 		//General Setings ;
 		pw=res.getWriter();
 		res.setContentType("text/html");
 		
 		//read form data
 		no=req.getParameter("sno");
 		name=req.getParameter("sname");
 		m1=req.getParameter("m1");
 		m2=req.getParameter("m2");
 		m3=req.getParameter("m3");
 		
 		//store form data into studentvo class object 
 		
 		vo=new StudentVO();
 		vo.setM1(m1);
 		vo.setM2(m2);
 		vo.setM3(m3);
 		vo.setSname(name);
 		vo.setSno(no);
 		
 		//convert VO Object to DTO Object
 		
 		dto=new StudentDTO();
 		
 		dto.setSno(Integer.parseInt(vo.getSno()));
 		dto.setSname(vo.getSname());
 		dto.setM1(Integer.parseInt(vo.getM1()));
 		dto.setM2(Integer.parseInt(vo.getM2()));
 		dto.setM3(Integer.parseInt(vo.getM3()));
 		
 		//Create and use Service calss object
 		
 		service=new StudentServiceImpl();
 		try {
 			result=service.generateResult(dto);
 			pw.println("<h1> Student Result::"+result+"</h1>");
 			pw.println("<br><a href='input.html'>home</a>");
 		}
 		catch(Exception e) {
 			pw.println("<h1 style='color:red;text-align:center'>Internal Problem </h1>");
			pw.println("<hr>"+e.getMessage());
	
 		}
	pw.println("<br><a href='input.html'>home</a>");
	pw.close();
	
 	}
 	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

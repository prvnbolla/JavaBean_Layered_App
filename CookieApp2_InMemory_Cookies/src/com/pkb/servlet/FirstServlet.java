package com.pkb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/furl")
public class FirstServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=null;
	String name=null, fname=null,job=null;
	Cookie ck1=null,ck2=null,ck3=null;
	//general settings
	pw=response.getWriter();
	response.setContentType("text/html");
	//read fornm 1 data
	name=request.getParameter("name");
	fname=request.getParameter("fname");
	job=request.getParameter("job");
	
	//Add form 1 request 1 form data to In Memory Cookies 
	ck1=new Cookie("name", name);
	ck2=new Cookie("fname", fname);
	ck3=new Cookie("job", job);
	
	//Add cookies to response 
	response.addCookie(ck1);
	response.addCookie(ck2);
	response.addCookie(ck3);
	
	//generate form 2 data from here
	if(job.equals("salaried")) {
		pw.println("<b><form action='surl' method='post'></b>");
		pw.println("<b>Job Title:<input type='text' name='jt'</b><br>");
		pw.println("<b>Income Per Year:<input type='text' name='ipr'</b><br>");
		pw.println("<b>Tax Paid:<input type='text' name='tp'</b><br>");
		pw.println("<b><input type='submit' value='continue'</b>");
		pw.println("</form>");
	}
	else if(job.equals("business")) {
		pw.println("<b><form action='surl' method='post'></b>");
		pw.println("<b>Business Name:<input type='text' name='bn'</b><br>");
		pw.println("<b>Income Per Year:<input type='text' name='ipr'</b><br>");
		pw.println("<b>Tax Paid:<input type='text' name='tp'</b><br>");
		pw.println("<b><input type='submit' value='continue'</b>");
		pw.println("</form>");
	}
	else{
		pw.println("<b><form action='surl' method='post'></b>");
		pw.println("<b>Targetted Job:<input type='text' name='tdj'</b><br>");
		pw.println("<b>Expected Annual Income:<input type='text' name='eai'</b><br>");
		pw.println("<b>Prefered Location:<input type='text' name='pl'</b><br>");
		pw.println("<b><input type='submit' value='continue'</b>");
		pw.println("</form>");
	
	}
	pw.println("<br><a href=input.html>HOME</a>");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

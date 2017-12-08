package com.pkb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/s4url")
public class Servler4 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		RequestDispatcher res=null;
		//general settings
		pw=response.getWriter();
		response.setContentType("text/html");
		
		//read and display request attribute value 
				pw.println("<h1>Servlet 4 Request Attribute is="+request.getAttribute("attr1"));
				
		//read and display session atribute value 
			HttpSession	ses=request.getSession();
				pw.println("<h1>Servlet 4 session Attribute is="+ses.getAttribute("attr2"));
		
		//read and display Servlet Context atribute value 
		ServletContext sc=request.getServletContext();
		pw.println("<h1>Servlet 4 ServletContext Attribute is="+sc.getAttribute("attr3"));
					
		
		pw.close();
	}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

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
@WebServlet("/s1url")
public class Servler1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		RequestDispatcher res=null;
		HttpSession ses=null;
		ServletContext sc=null;
		//general settings
		pw=response.getWriter();
		response.setContentType("text/html");
		
		//create request attribute 
		request.setAttribute("attr1", "val1");
		
		//create session attribute
		ses=request.getSession();
		ses.setAttribute("attr2", "val2");
		
		//create Servlet Context attribute
		sc=request.getServletContext();
		sc.setAttribute("attr3", "val3");
		
		//forward request to second servlet
		res=request.getRequestDispatcher("/s2url");
		res.forward(request, response);
	}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

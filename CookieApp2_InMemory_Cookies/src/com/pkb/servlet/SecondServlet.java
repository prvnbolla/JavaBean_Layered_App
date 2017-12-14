package com.pkb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/surl")
public class SecondServlet extends HttpServlet {
	public static final String INSERT_TAX_TAB="INSERT INTO TAX_TAB VALUES(?,?,?,?,?,?,?,?,?,?)";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		PrintWriter pw=null;
		String jt=null,bn=null,tdj=null,pl=null;
		String name=null, fname=null,job=null;
		int ipr=0,tp=0,eai=0,result=0;
		Cookie ck[]=null;
		PreparedStatement ps=null;
		Connection con=null;
		
		//General Settings 
		pw=response.getWriter();
		response.setContentType("text/html");
		
		//Read form2 request 2 data from first servlet 
		jt=request.getParameter("jt");
		bn=request.getParameter("bn");
		tdj=request.getParameter("tdj");
		pl=request.getParameter("pl");
		System.out.println(jt+"..."+bn+"..."+tdj+"..."+pl+"..."+ipr+"..."+tp+"..."+eai);
		
		ipr=Integer.parseInt(request.getParameter("ipr"));
		tp=Integer.parseInt(request.getParameter("tp"));
		eai=Integer.parseInt(request.getParameter("eai"));
		
		//read request1/form1 data from inmemory cookies (session tracking)
		if(ck!=null) {
			name=ck[0].getValue();
			fname=ck[1].getValue();
			job=ck[3].getValue();
		}
		//write form1/request1 form2/request1 data to db table record 
		try {
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","praveen","praveen");
			//create prepaire statement 
			ps=con.prepareStatement(INSERT_TAX_TAB);
			//set values to query param
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.setString(3, job);
			ps.setString(4, jt);
			ps.setString(5, tdj);
			ps.setString(6, bn);
			ps.setString(7, pl);
			ps.setInt(8, tp);
			ps.setInt(9, ipr);
			ps.setInt(10, eai);
			
			//execute query 
			result=ps.executeUpdate();
			//process the resule
					if(result==0)
						pw.println("<h1 style='color:red;text-align:red'>Registration Failed</h1>");
					else
						pw.println("<h1 style='color:red;text-align:green'>Registration Sucessfull</h1>");
					//display form1/request1
					pw.println("<h1>Consultant form1 data</h1>");
					pw.println("<b>Consultant name=</b>"+name);
					pw.println("<br><b>Consultant Fathers name=</b>"+fname);
					pw.println("<br><b>Consultant woking Status=</b>"+job);
					//display form2/request 2 data
					pw.println("<br><h1>Consultant form1 data</h1>");
					
					if(job.equals("salaried")) {		
					pw.println("<b>Consultant Job Title=<br>"+jt);
					pw.println("<b>Consultant Annual Income=<br>"+ipr);
					pw.println("<b>Consultant paid tax=<br>"+tp);
					}
					else if(job.equals("business")) {
						pw.println("<b>Consultant business Title=<br>"+bn);
						pw.println("<b>Consultant Annual Income=<br>"+ipr);
						pw.println("<b>Consultant paid tax=<br>"+tp);	
					}
					else {
						pw.println("<b>Consultant Targetted job title=<br>"+tdj);
						pw.println("<b>Consultant Annual Targetted income Income=<br>"+eai);
						pw.println("<b>Consultant Prefered location=<br>"+pl);
						
					}
					//add hyperlink
					pw.println("<br><a href=input.html>HOME</a>");
		}
		catch(SQLException se)
		{
				se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc connection
			try {
				if(ps!=null)
					ps.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//close pw
		pw.close();
		}
			
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

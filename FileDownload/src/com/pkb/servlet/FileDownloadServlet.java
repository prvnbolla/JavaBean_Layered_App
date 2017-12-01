package com.pkb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	ServletContext sc=null;
	File file=null;
	long length=0;
	String mimeType=null;
	InputStream is=null;
	ServletOutputStream sos=null;
	byte[] buffer=new byte[4096];
	int BytesRead=0;
	
	// Get Servlet Context object
	sc=getServletContext();
	
	// Get path of file to be downloaded 
	
	String path = sc.getRealPath("/james.jpg");
	
	//get length of the file and make it as the response content length
	
	file=new File(path);
	length=file.length();
	res.setContentLengthLong(length);
	//get Mimetype of the file and make it as response mime type
	mimeType=sc.getMimeType("/james.jpg");
	res.setContentType(mimeType);
	//set content disposition response header
	res.setHeader("content-disposition","attachment;fileName=james.png");
	//create inputstream pointing to the file
	is=sc.getResourceAsStream("/james.jpg");
	
	//get outputsteeam pointing to response obj
	sos=res.getOutputStream();
	//write buffer based logic to complete file copy activity (file dowmloading)
	while((BytesRead=is.read(buffer))!=1)
	{
		sos.write(buffer,0,BytesRead);
	}
	is.close();
	sos.close();
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
	
	}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtbeacon.HttpPostData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此类接收CB上传的Beacon扫描数据
 * @author admin
 */
public class HttpPostDataServlet  extends HttpServlet 
{
	public static String beaconData = "未接收到数据";
//	public static String beaconData = "Test File Str";
	private static long currentTime = System.currentTimeMillis();
	public static String fileName = String.format("%d.txt", currentTime);
	
    @Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
                
		try 
        {
			//接收前端数据
//			beaconData = StreamToString(request);
			beaconData = String.format("%d", System.currentTimeMillis());

			response.setContentType("text/html; charset=UTF-8");  
	        //设置页面的编码方式，即以什么样的编码方式来保存页面  
	        response.setCharacterEncoding("utf-8");
	        
	        String filePath = this.getServletContext().getRealPath(fileName);//文件的绝对路径
//	        beaconData = filePath;
	        //使用文件的绝对路径打开文件  
	        File file = new File(filePath);  
	        //使用打开的文件对象，创建FileWriter类的实例  
	        FileWriter writer = new FileWriter(file, true);  
	        //使用打开文件对应的writer对象，创建BufferderWriter类的实例  
	        BufferedWriter bufferWriter = new BufferedWriter(writer);  
	        bufferWriter.write(beaconData);
	        bufferWriter.newLine();
	        bufferWriter.flush();  
	        bufferWriter.close();  
	        writer.close();
			
			//转发到index.jsp
			response.sendRedirect("/JavaHttpSample/index.jsp");
			  
		} 
		catch (IOException e) 
		{
			println(e.getMessage()); 
		} 
	}
	
	private String StreamToString(HttpServletRequest request) throws IOException     
	{
		int totalbytes = request.getContentLength(); 
		if(totalbytes <= 0)
			return "";

		byte[] bytes = new byte[1024 * 1024];
		InputStream is = request.getInputStream();
		int nRead = 1; 
		int nTotalRead = 0;
		while (nRead > 0)
		{ 
			nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
			if (nRead > 0) 
				nTotalRead = nTotalRead + nRead; 
		}

		String reqcontent = new String(bytes, 0, nTotalRead, "utf-8");            
		return reqcontent;
	}		
	
	public static void println(String strOut) 
	{
		if(strOut == null)
			return;

		try 
		{
			System.out.write(strOut.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException ex) 
		{
			System.out.println(strOut);
		}
		catch (IOException ex) 
		{
			System.out.println(strOut);
		}
	}
}

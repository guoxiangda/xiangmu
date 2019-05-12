package com.Gxd.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");//设置输出内容的编码格式
		 response.setCharacterEncoding("UTF-8");
		 String user=request.getParameter("userName");
		Connection con=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载成功");
			con=(Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/studb","root","12345");
			System.out.println("sql连接成功");
			Statement sta=(Statement) con.createStatement();
			
			ResultSet res=(ResultSet) sta.executeQuery("select yhm from xm where yhm='"+user+"'");
			if(res.next()){
				 request.getRequestDispatcher("/success").forward(request, response);
			}else{
				 response.sendRedirect("/Gxd/zhuce.html");  
			}
		}catch(ClassNotFoundException e){
			System.out.println("没有找到此用户名");
		}catch(SQLException sqle){
			System.out.println("sql连接失败");
		}
		

		
	}
}

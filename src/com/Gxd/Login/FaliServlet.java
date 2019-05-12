package com.Gxd.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class FaliServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setCharacterEncoding("UTF-8");//设置输出内容的编码格式
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user=request.getParameter("userName");
		String pwd=request.getParameter("pwd");

		Connection con=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载成功");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studb","root","12345");
			System.out.println("sql连接成功");
			
			
			Statement sta= (Statement)con.createStatement();
		int n=sta.executeUpdate("insert xm values ('"+user+"','"+pwd+"') ");
			if(n>0){
				System.out.println("注册成功");
			}
		}catch(ClassNotFoundException e){
			System.out.println("没有找到此用户名");
		}catch(SQLException sqle){
			System.out.println("sql连接失败");
		}
		
	}

}


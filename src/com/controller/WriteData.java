package com.controller;



import java.io.IOException;


import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBConnection;

/**
 * Servlet implementation class WriteData
 */
@WebServlet("/WriteData")
public class WriteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteData() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response){
    	java.sql.Connection c=DBConnection.getConnection();
    	String email=(String)request.getParameter("email");
    	String query=(String)request.getParameter("query");
    	Statement stmt=null;
    	try {
			stmt=c.createStatement();
			boolean execute = stmt.execute("insert into queries values ('"+email+"','"+query+"');");
			System.out.println(execute);
			c.commit();
		} catch (SQLException e) {
			try {
				stmt.execute("create table queries (email varchar(255),query text);");
				stmt.execute("insert into queries values ('"+email+"','"+query+"');");
				c.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

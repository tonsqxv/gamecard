package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;

public class SvEnvironment extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SvEnvironment() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		
		 HttpSession session =request.getSession();
		
		try{
		APIProfile profile = null;
		
		/*Hashtable pars = new Hashtable();
		if (FileUpload.isMultipartContent(request)) {
			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(request);
	
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					pars.put(item.getFieldName(), item.getString());
					
				} else {
					if ((item.getName() != null) && (item.getName().length() > 0)) {
						File cfile = new File(item.getName()); 
						File tosave = new File(getServletContext().getRealPath("/cert/"),cfile.getName());
						item.write(tosave);
						pars.put("certificateFile", tosave.getAbsolutePath());
					}
				}
			}
		}
		*/
	  //      String unipay = (String)pars.get("subjectEmail");
		
		
		
		profile = ProfileFactory.createSSLAPIProfile();
		profile.setAPIUsername("dashitou_api1.qq.com");
		profile.setAPIPassword("C2CHWFA5HY26UY9D");
		profile.setCertificateFile(getServletContext().getRealPath("WEB-INF/cert/paypal_cert.p12"));
		profile.setPrivateKeyPassword("skychen");
		profile.setEnvironment("sandbox");
		profile.setSubject("123390255@qq.com");
	
	 	session.setAttribute("environment","sandbox");
		
	 
	 	CallerServices caller = new CallerServices();
		
		caller.setAPIProfile(profile);
    	session.setAttribute("caller", caller);
    	response.sendRedirect("../Calls.html");
	} catch (Exception e) {
				session.setAttribute("exception", e);
				response.sendRedirect("Error.jsp");
	}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

package com.trch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.trch.service.BandService;

@WebServlet(name = "Bands", urlPatterns = {"/bands/*"})
public class BandController extends HttpServlet {
	
private BandService bandService;
	
	public BandController() {
		bandService = new BandService();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String requestUri = request.getRequestURI();
		
		String[] requestUriArray = requestUri.split("/");
		
		Arrays.stream(requestUriArray).forEach(s -> System.out.println("_" + s.toString() + "_"));
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			if (requestUriArray.length <= 3 ) {
				out.print(bandService.getJsonStringForAll());
				out.flush();
			} else {
				out.print(bandService.getJsonStringForOne(requestUriArray[3]));
				out.flush();
			}
			
		} catch (IOException e) {
			Logger logger = Logger.getLogger(AlbumController.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
	}

}

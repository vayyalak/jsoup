package com.jsoup.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet("/main")
public class Main extends HttpServlet  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Document doc;
		Document doc1;
		Document doc2;
		
		try {
			// need http protocol
			ReadProperties readProperties =  new ReadProperties();
			doc = Jsoup.connect("https://dev-mstr-mws01.gridpoint.com/GPReportWeb/asp/Main.aspx?evt=4001&src=Main.aspx.shared.fb.2001&ssotoken=T5IN1R2uhVhAHGbbeHvWXAK14zoKjvLFRo2vPTGJMONGQR1iKJej5TywCvwRrtkSZzI2clAlld7j%0D%0A86qtsAM4aRkbdX0AzVhSqxA4OBQDpO1fhU1O82R6Q4lhz9QquvsTZHQQvm3BGevKBDZ2VOxCn5q0%0D%0A7fHx37fEKSlOPLYpL4zk3oBOQBR2XPugJtwHSEPBkSS6fNUVWkQ%2FuB3X%2Fehnrb4amGhlNkTFvntO%0D%0AYTnDF7vzkXuw%2Bj9tOVHYWGm8k%2FiBfBFMa7a7Ar2dUoPzS2G4G2p8hZXRg8hFSZmtSf1KC7kiVSbb%0D%0ALktSrzCZdi6V81k9PftX9Q8VRuA%2FS10LNthZPw%3D%3D%0D%0A").timeout(10*1000).get();
			System.out.println(doc);
			// get page title
			/*String title = doc.title();
			System.out.println("title : " + title);*/
			
			// get all links
			 Elements links = doc.select("div.mstrLargeIconViewItemName");
			 //List<String> alllinks = new ArrayList<String>();
			 List<Reports> alllink = new ArrayList<Reports>();
			
			 String baseUrl = "http://dev-mstr-mws01.gridpoint.com/GPReportWeb/asp/";
			 String ssotoken = doc.baseUri().substring(78);
			 System.out.println(ssotoken);
			for (Element element : links) {
				if (element.siblingIndex() > 0) {
					Elements link = element.select("a[href]");
					String sourceLink = link.attr("href");
					String title = link.attr("title");
					String stringurl = baseUrl + sourceLink + ssotoken;
					
					Reports mainReports=getConvertReport(title, stringurl);
					List<Reports> subReportsLink = new ArrayList<Reports>();
					
					if (stringurl.contains("folderID")) {
						doc1 = Jsoup.connect(stringurl).timeout(10 * 1000).get();
						Elements sublinks = doc1.select("div.mstrLargeIconViewItemName");
						
						for (Element subelement : sublinks) {
							
							if (subelement.siblingIndex() > 0) {
								Reports subReports = getSubReports(subelement, baseUrl, ssotoken);
								List<Reports> secondSubReportsLink = new ArrayList<Reports>();
								
								if (subReports.getLink().contains("folderID")) {
									doc2 = Jsoup.connect(subReports.getLink()).timeout(10 * 1000).get();									
									Elements secondsublinks = doc2.select("div.mstrLargeIconViewItemName");
									
									for (Element secondsublink : secondsublinks) {
										if (secondsublink.siblingIndex() > 0) {
											secondSubReportsLink.add(getSubReports(secondsublink, baseUrl, ssotoken));
										}
									}
								}
								subReports.setNextLink(secondSubReportsLink);
								subReportsLink.add(subReports);
							}
						}
					}
					mainReports.setNextLink(subReportsLink);
					alllink.add(mainReports);
				}
			}
			System.out.println(links);
			request.setAttribute("links", alllink);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/allLinks.jsp");
			requestDispatcher.forward(request, response);
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	  }

	private Reports getSubReports(Element secondsublink, String baseUrl, String ssotoken) throws IOException{
		Elements secondsub = secondsublink.select("a[href]");
		String secondsubsourceLink = secondsub.attr("href");
		String secsubtitle = secondsub.attr("title");
		String secsubstringurl = baseUrl+ secondsubsourceLink+ ssotoken;
		return getConvertReport(secsubtitle, secsubstringurl);
	}
	
	private Reports getConvertReport(String name,String link){
		Reports subReports = new Reports();
		subReports.setName(name);
		subReports.setLink(link);
		if(link.contains("folderID")){
			subReports.setFolder(true);
		}else{
			subReports.setReport(true);
		}
		return subReports;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
	


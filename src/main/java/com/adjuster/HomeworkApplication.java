package com.adjuster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adjuster.domain.Campaign;
import com.adjuster.domain.Creative;

@SpringBootApplication
public class HomeworkApplication implements CommandLineRunner{
	  private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		URL url;
		HttpURLConnection conn;
		BufferedReader br;
		JSONObject json;
		Iterator i;
		
		int id = 1;
		
		try {
	        JSONParser parser = new JSONParser();
	        /////////////////////////////////////////////////////////////////////////////////////
	        // Problem 1: Write an HTTP Client that pull the client's data from the API and saves
	        //            it locally to a database of choice.
	        // 
	        // Solution : After the retrived JSON object is parsed (line 71), a JSON Array of the
	        //            campaigns is created (line 72) and we iterate over the array to create 
	        //            a campaign object and store it in a MySQL database through the storeData
	        //            method (line 77).
			url = new URL("http://localhost:8080/campaigns");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			json = (JSONObject) parser.parse(br);
            JSONArray campaigns  = (JSONArray)((JSONObject) json.get("_embedded")).get("campaigns");
            i = campaigns.iterator();
            while (i.hasNext()) {
            	JSONObject campaign = (JSONObject)i.next();
            	Campaign cmp = new Campaign(id, (String)campaign.get("campaignName"), ((Long)(campaign.get("customerId"))).intValue());
            	storeData(cmp); 
            	id++;
            }
			conn.disconnect();
			
	        /////////////////////////////////////////////////////////////////////////////////////
			// Problem 2: Write a database command query to calculate total clicks and views
			//            at the campaign level per child creatives.
			//
			// Solution : Clicks and views are displayed in the console as part of the logging. 
			//            See line 106.
			id = 1;
			List<Creative> crtveList = new ArrayList<Creative>();
			url = new URL("http://localhost:8080/creatives"); 
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			json = (JSONObject) parser.parse(br);
            JSONArray creatives  = (JSONArray)((JSONObject) json.get("_embedded")).get("creatives");
            i = creatives.iterator();
            while (i.hasNext()) {
            	JSONObject creative = (JSONObject)i.next();
            	Creative crtve = new Creative(id, ((Long)creative.get("clicks")).intValue(), ((Long)creative.get("views")).intValue(), ((Double)creative.get("cpm")).doubleValue());
            	log.info(crtve.toString());
            	crtveList.add(crtve);
            	id++;
            }
			conn.disconnect();
			
	        /////////////////////////////////////////////////////////////////////////////////////
			// Problem 3: Output the campaign data results from Problem 2 in a CSV file. 
			//
			// Solution : The file is created and stored in the root directory of the Eclipse
			//            project.
	        /////////////////////////////////////////////////////////////////////////////////////
			// Extra Credit: Revenue is calculated using CPM and views. Using the formula 
			// CPM*views/1000 please include revenue data at the campaign level in the CSV file.
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("creative.csv")));
			for (Creative c:crtveList) {
				pw.printf("%d,%d,%d,%f%n", c.getId(), c.getViews(), c.getClicks(), c.getCpm()*c.getViews()/1000.);
			}
			log.info("File 'creative.csv' was written to directory.");
			pw.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void storeData(Campaign cmp) {
		try {
			// Store in MySQL database campaign information
			ResultSet rs = null;
			Connection conn = null;
			String dburl = "jdbc:mysql://localhost/adjuster";
			String userName = "adjuster";
			String password = "homework";
			
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(dburl, userName, password);
			log.info("Connected to MariaDB adjuster database.");
			
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO campaign VALUES(?,?,?)");
				ps.setInt(1, cmp.getId());
				ps.setString(2, cmp.getCampaignName());
				ps.setInt(3, cmp.getCustomerId());
				rs = ps.executeQuery();
				
				conn.close();
				log.info("Disconnected from MariaDB database.");
			}
		}
		catch (SQLException e) {
			log.debug("SQL Exception: " + e.getMessage());
		}
		catch (Exception e) {
			log.error("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}

package com.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testbase.TestBase;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class GetAllUser extends TestBase {
	public JsonPath jp;
	@BeforeMethod
	public void getAllUser()
	{
		resp=httpReq.request(Method.GET,"users");
	}
	

	@Test(priority=0,enabled=false)
	public void validateStatusCode()
	{
		System.out.println("Statuscode :"+resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	@Test(priority=1,enabled=false)
	public void getStatusLine()
	{
		System.out.println("StatusLine :"+resp.getStatusLine());
		Assert.assertEquals(resp.getStatusLine(), "HTTP/1.1 200 OK");
	}
	@Test(priority=3,enabled=false)
	public void validateResponsetime()
	{
		System.out.println("ResponseTime :"+resp.getTime());
		Assert.assertTrue(resp.getTime()< 3000);
	}
	@Test(priority=4,enabled=false)
	public void validateRespBody()
	{
		//System.out.println("StringBody :"+resp.getBody().prettyPeek());
		jp=resp.jsonPath();
	
		System.out.println("\nJsonBody :"+jp.get("data[?(@.id==1)].name"));
//		List<String> l=jp.get("data.name");
//		Iterator<String> i=l.iterator();
//		while(i.hasNext())
//		{
//			System.out.print(i.next());
//		}
		}
	@Test(priority=3,enabled=true)
	public void validateResponseHeaders()
	{
		//System.out.println("ResponseTime :"+resp.getHeaders());
		Headers headers=resp.getHeaders();
		for(Header head:headers)
		{
			if(head.getName().contains("Content-Type"))
			{
				System.out.println("Header : "+head.getValue());
				break;
			}
		}
		
		Assert.assertTrue(headers.equals("Content-Type"));
	}
}

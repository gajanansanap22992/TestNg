package com.testcases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.testbase.TestBase;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


public class CreateUser extends TestBase {
	public JSONObject queryParam;
	public String id;
	//public HashMap<String,String> hm=new HashMap<String,String>();
	@Test(priority=1)
	public void createUser()
	{
		
		queryParam=new JSONObject();
		queryParam.put("name", "naresh");
		queryParam.put("job", "Engineer");		
		httpReq.body(queryParam.toJSONString());
		httpReq.header("Content-Type","application/json");
		resp=httpReq.request(Method.POST,"user/1");
		System.out.print(resp.getBody().toString());
		JsonPath jp=resp.jsonPath() ;
		id=jp.get("id");
		System.out.println("name :"+id);
		Assert.assertEquals(resp.getStatusCode(),201);		
	}
	@Test(priority=2,enabled=false)
	public void updateUser()
	{
		
		queryParam=new JSONObject();
		queryParam.put("name", id);
		queryParam.put("job", "SoftewareEngineer");		
		httpReq.body(queryParam.toJSONString());
		httpReq.header("Content-Type","application/json");
		resp=httpReq.request(Method.POST,"user/1");
		System.out.print(resp.getBody().toString());
		
		Assert.assertEquals(resp.getStatusCode(),200);
		Assert.assertTrue(resp.getBody().toString().contains("updatedAt"));
	}
	@Test(priority=2)
	public void deleteUser()
	{
		
		
		resp=httpReq.request(Method.POST,"user/"+id);
		System.out.print(resp.getBody().toString());
		
		Assert.assertEquals(resp.getStatusCode(),204);
		Assert.assertTrue(resp.getBody().toString().contains("userDeletedAt"));
	}
	
	

}

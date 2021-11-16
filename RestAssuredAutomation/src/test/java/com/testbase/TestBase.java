package com.testbase;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpReq;
	public static Response resp;
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI="https://reqres.in/api/";
		httpReq=RestAssured.given();
	}

}

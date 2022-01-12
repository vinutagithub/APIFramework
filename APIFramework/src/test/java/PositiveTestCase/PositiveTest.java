package PositiveTestCase;

import BaseServices.BaseServices;
import EndPoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PositiveTest extends BaseServices {

    //@BeforeMethod
    String fname="";
    String lname="";
    String checkin="";
    String checkout="";


    @Test(description = "Ping-Health Check")
    public void tc_pingAPI(){
        RestAssured.registerParser("text/plain",Parser.TEXT);

        Response response= request(EndPoints.Ping, Method.GET);

        String rbody=response.body().asString();
        System.out.println(rbody);
        response.then()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .body(equalTo("Created"));
    }


    @DataProvider(name="getbookingid")
    public Object[][]getbookingid(){
        return new Object[][]{
                {"1"},{"2"}};
    }

    @Test(description = "Returns a specific booking based upon the booking id provided.",dataProvider = "getbookingid")
    public  void tc_GetBookingId(String id){
       Response response= request(EndPoints.GetBookingId,"id",id,Method.GET);

       response.then()
               .assertThat()
               .spec(checkStatusCodeAndContentType);

       /* Accessing the firstname, lastname, checkin, checkout from the last dataprovider id=3*/

        fname=response.body().path("firstname");
        lname=response.body().path("lastname");
        checkin=response.body().path("bookingdates.checkin");
        checkout=response.body().path("bookingdates.checkout");

    //   System.out.println(checkin + " "+checkout);

    }

    /**
     * Can take optional query strings to search and return a subset of booking ids.
     *
     * GET
     */
    @Test(dependsOnMethods = {"tc_GetBookingId"})
    public void tc_GetBookingIdByName(){
        Response response=request(EndPoints.GetBookingIdByOptional,"firstname","lastname",fname,lname,Method.GET);
        response.body().prettyPrint();

        response.then().assertThat().body("bookingid",hasItem(2));


    }

    @Test(dependsOnMethods = {"tc_GetBookingId"})
    public void tc_GetBookingIdByTime(){
        Response response=request(EndPoints.GetBookingIdByOptional,"checkin","checkout",checkin,checkout,Method.GET);
        response.body().prettyPrint();
                /*.then()
                .assertThat()
                .body("bookingid",contains(3,4,7)).log().all();*/

    }

    @Test
    public void tc_datapersistence(){

        String etag="";

        Response response= request(EndPoints.GetBookingId,"id","3",Method.GET);

        etag= response.header("Etag");

        given()
                .baseUri(EndPoints.BASE_URL)
                .header("If-None-Match",etag)
                .pathParam("id","3")
                .when()
                .get(EndPoints.GetBookingId)
                .then()
                .log().status();


    }


}

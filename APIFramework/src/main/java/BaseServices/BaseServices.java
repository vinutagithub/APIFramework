package BaseServices;

import EndPoints.EndPoints;
import Util.ConfigUtil;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public  class BaseServices {

   public static RequestSpecification reqspec;
   public static RequestSpecBuilder requestBuilder;

    /**
     * @checkStatusCodeAndContentType
     * Response Specification builder
     * building Response check for statusCode and ContentType
     * */
   public static  ResponseSpecification checkStatusCodeAndContentType =
            new ResponseSpecBuilder().
                    expectStatusCode(200).
                    expectContentType("application/json").
                    build();



    /**
     * building RequestSpecification in order to avoid repetition.
     */
   public RequestSpecification getRequestSpec(){
       requestBuilder = new RequestSpecBuilder();
       requestBuilder.setBaseUri(EndPoints.BASE_URL);
       requestBuilder.setContentType("application/json");
       requestBuilder.setAccept("application/json");
       reqspec=requestBuilder.build();
       return reqspec;
   }



    /**
     * Simple check for Api up and running
     * Method - Get , URl , response should be 201 Created
     * @param url
     * @param method
     * @return response
     */
   protected Response request(String url, Method method){
      Response response=
              given()    // getRequestSpec() -gives error java.lang.NullPointerException: Cannot get property 'assertionClosure' on null object. if used with out given()
               .spec(getRequestSpec())
               .when()
               .request(method,url)
               .then()
               .extract().response();
      return response;
   }

    /**
     *
     * @param url -GetBookingId="/booking/{id}"
     * @param pathparam - id
     * @param pathValue - 1
     * @param method - get
     * @return
     */

   protected Response request(String url, String pathparam,String pathValue,Method method ){
    return
       given()
               .spec(getRequestSpec())
               .pathParam(pathparam,pathValue)
               .when()
               .request(method,url)
               .then()
               .extract().response();
   }

    /**
     * GetBookingIdByName="/booking?firstname={fname}&lastname={lname}"
     */

    protected Response request(String url, String qparam1,String qparam2,String qval1,String qval2,Method method ){

        return
                given()
                .spec(getRequestSpec())
                .queryParam(qparam1,qval1)
                .queryParam(qparam2,qval2)
                .when()
                .request(method,url)
                .then()
                .extract().response();
    }

    protected String createToken(){
        ConfigUtil util=new ConfigUtil("config.properties");
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",util.getproperty("username"));
        hashMap.put("password",util.getproperty("password"));

        return given()
                .spec(getRequestSpec())
                .body(hashMap)
                .when()
                .post("/auth")
                .then()
                .extract().body().path("token");
    }

   /* protected Response CreateBookingServices(String url,String token){

        return given()
                .
    }*/

}

package Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@JsonPropertyOrder({

        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdate",
        "additionalneeds"

})

public class CreateBooking {

    /*
    curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/json' \
  -d '{
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
     */

 //   @JsonSerialize(using = CustomeDateSerialize.class)

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private float totalprice;

    @JsonProperty("depositpaid")
    private Boolean depositpaid;

    @JsonProperty("bookingdate")
    BookingDate bookingdate;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("additionalneeds")
    private String additionalneeds;

  //  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }


   public CreateBooking(String fname, String lname, float totalprice, Boolean depositpaid,BookingDate bookingDate) throws ParseException {
        bookingdate=new BookingDate();
        this.firstname=fname;
        this.lastname=lname;
        this.totalprice=totalprice;
        this.depositpaid=depositpaid;
        this.bookingdate=bookingDate;

    }

    public CreateBooking(String fname, String lname, float totalprice, Boolean depositpaid,BookingDate bookingDate,String additionalneeds) throws ParseException {
        bookingdate=new BookingDate();
        this.firstname=fname;
        this.lastname=lname;
        this.totalprice=totalprice;
        this.depositpaid=depositpaid;
        this.bookingdate=bookingDate;
        this.additionalneeds=additionalneeds;

    }


    @Override
    public String toString() {
        return( "{" +
                "firstName :'" + firstname + '\'' +
                ", lastName :'" + lastname + '\'' +
                ", totalprice :'" + totalprice + '\'' +
                ", depositpaid :'" + depositpaid + '\'' +
                ", bookingdates : {"+
                "checkin : '"+ bookingdate.getCheckin()+'\'' +
                "checkout : '"+bookingdate.getCheckout() +'\''+
                "    },"+'\''+
                ", additionalneeds :'"+additionalneeds+'\''+
                '}'
        );
    }




}

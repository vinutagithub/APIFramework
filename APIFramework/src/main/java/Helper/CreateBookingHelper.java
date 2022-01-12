package Helper;

import Model.BookingDate;
import Model.CreateBooking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class CreateBookingHelper {

    CreateBooking createBooking;
    BookingDate bookingDate;

    public void createSerialization() throws IOException, ParseException {
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        bookingDate= new BookingDate();
        bookingDate.setCheckin("2018-01-01");
        bookingDate.setCheckout("2019-01-01");


        createBooking =new CreateBooking("jim","brown",111,true,bookingDate);
        om.writeValue(new File(System.getProperty("user.dir")+"/src/main/resources/jsonfile/post.json"),createBooking);
        System.out.println(om.writeValueAsString(createBooking));

        createBooking =new CreateBooking("jim1","brown",111,true,bookingDate,"Dinner");
        om.writeValue(new File(System.getProperty("user.dir")+"/src/main/resources/jsonfile/postWithadditionalneeds.json"),createBooking);
    }


    /**
     * Method to
     */
    public static void main(String[] args) throws IOException, ParseException {
        CreateBookingHelper obj= new CreateBookingHelper();
        obj.createSerialization();

    }
}

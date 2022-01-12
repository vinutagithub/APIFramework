package Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingDate {
 //   @JsonSerialize(using = CustomeDateSerialize.class)
    private Date checkin;
    private Date checkout;

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");

    public String getCheckin() {
     //   System.out.println(sdf1.format(this.checkin));
        return sdf1.format(this.checkin);
    }

    public void setCheckin(String checkin) throws ParseException {
        this.checkin = sdf1.parse(checkin);
    }

    public String getCheckout() {
        return sdf1.format(checkout);
    }

    public void setCheckout(String checkout) throws ParseException {
        this.checkout = sdf1.parse(checkout);
    }
/*  public static void main(String[] args) throws ParseException {
        BookingDate bd= new BookingDate();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
        String date="2012-10-13";
        Date date1= sdf.parse(date);

        bd.setCheckin(date);
    }*/
}

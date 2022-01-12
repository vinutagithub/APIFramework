package EndPoints;

public interface EndPoints {

    String BASE_URL="https://restful-booker.herokuapp.com";

    String Ping="/ping"; //to confirm APi is up and running.

    String GetBookingId="/booking/{id}";
    String GetBookingIdByOptional="/booking"; //?firstname={fname}&lastname={lname}

    String CreateBooking="/booking"; //post

    String UpdateBooking="/booking/{id}"; //put

    String PartialUpdateBooking="/booking/{id}"; //patch

}

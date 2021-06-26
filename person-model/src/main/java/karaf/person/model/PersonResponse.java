package karaf.person.model;

public class PersonResponse {
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean status;
    private String message;

    public PersonResponse() {

    }

    private static final PersonResponse personResponse = new PersonResponse();

    public static PersonResponse getPersonResponse() {
        return personResponse;
    }

    public PersonResponse successPerson(String message) {
        setStatus(true);
        setMessage(message);
        return getPersonResponse();
    }

    public PersonResponse errorPerson(String message) {
        setStatus(false);
        setMessage(message);
        return getPersonResponse();
    }
}

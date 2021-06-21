package au.com.user.access.service;

public class UserBean {
    private String message;

    public UserBean() {}
    public UserBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

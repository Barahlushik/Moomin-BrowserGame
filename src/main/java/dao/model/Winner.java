package dao.model;

public class Winner {

    private Long id;

    private String login;
    private long seconds;


    public Winner(String login, long seconds) {
        this.login = login;
        this.seconds = seconds;
    }

    public Winner(Long id, String login, long seconds) {
        this.id = id;
        this.login = login;
        this.seconds = seconds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}

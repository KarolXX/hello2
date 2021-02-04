package io.github.mat3e;

public class Lang {
    private Long id;
    private String welcomeMsg;
    private String code;

    public Lang(Long id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }

    public Lang(Long id) {
        this.id = id;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String getCode() {
        return code;
    }
}

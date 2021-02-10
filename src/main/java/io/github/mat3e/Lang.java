package io.github.mat3e;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
class Lang {
    @Id
    @GeneratedValue(generator="incr")
    @GenericGenerator(name="incr", strategy = "increment")
    private Integer id;
    private String welcomeMsg;
    private String code;

    /*
    * Hibernate (JPA) needs it
     */
    @SuppressWarnings("unused")
    Lang() {
    }

    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }

    public Lang(Integer id) {
        this.id = id;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String getCode() {
        return code;
    }
}

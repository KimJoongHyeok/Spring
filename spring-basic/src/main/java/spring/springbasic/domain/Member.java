package spring.springbasic.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 ID값을 알아서 넣어줌
    private Long id;
    private String name;
    //@Column(name = "username") //db의 컬럼값이 username 이라면


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

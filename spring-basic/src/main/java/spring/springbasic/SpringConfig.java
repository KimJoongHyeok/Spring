package spring.springbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.springbasic.repository.JdbcMemberRepository;
import spring.springbasic.repository.JdbcTemplateMemberRepository;
import spring.springbasic.repository.MemberRepository;
import spring.springbasic.repository.MemoryMemberRepository;
import spring.springbasic.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }

}

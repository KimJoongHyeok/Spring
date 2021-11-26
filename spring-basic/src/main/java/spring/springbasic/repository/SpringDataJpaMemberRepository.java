package spring.springbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.springbasic.domain.Member;

import java.util.Optional;

//JpaRepository<Member,Long> <t,식별자 pk type>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{
    //인터페이스만 만들어 놓으면 인터페이스에 대한 구현체를 알아서 만들어주고 Spring Bean 에 등록

    //select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

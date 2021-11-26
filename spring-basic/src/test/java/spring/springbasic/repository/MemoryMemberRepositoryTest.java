package spring.springbasic.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.springbasic.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Junit 활용 testcase
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 서로 의존관계 없이 설정되어야하는데 순서는 정할 수 없음
    //그래서 아래와 같이 초기화 설정필수
    //테스트 케이스 이후마다 repository 초기화
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member,null); //기대값,실제값이 같다면 정상수행, 같지 않다면 error
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("spring1");
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }



}


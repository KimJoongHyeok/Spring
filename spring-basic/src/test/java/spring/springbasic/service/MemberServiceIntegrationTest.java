package spring.springbasic.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import spring.springbasic.domain.Member;
import spring.springbasic.repository.MemberRepository;
import spring.springbasic.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //테스트케이스에 붙었을때만 @Transactional 어노테이션이 있으면 테스트 시작전에 트랜잭션을 시작하고 테스트 완료 후 항상 롤백
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; //필드기반 인젝션
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() { //TEST는 한글로 바꿔도 상관없음
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//member2 join 햇을때 IllegalStateException 예외가 터져야함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

/*        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e ){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/

        //then
    }

}
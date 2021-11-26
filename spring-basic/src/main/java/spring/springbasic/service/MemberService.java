package spring.springbasic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.springbasic.domain.Member;
import spring.springbasic.repository.MemberRepository;
import spring.springbasic.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//테스트케이스 단축키 : ctrl shift t
//@Service

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복회원 x
       /* Optional<Member> result = memberRepository.findByName(member.getName()); //ctrl alt v
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });*/
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //ctrl alt v
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member>findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}

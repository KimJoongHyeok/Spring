package spring.springbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.springbasic.domain.Member;
import spring.springbasic.service.MemberService;

import java.util.List;

/*
       DI 3가지 방법
       1. 생성자 주입
       2. 필드주입
       3. Setter 주입
           @Autowired
            public void setMemberController(MemberService memberService) {
               this.memberService = memberService;
           }
    */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  //MemberController에 MemberService 를 연결 ,DI,의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member>members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}

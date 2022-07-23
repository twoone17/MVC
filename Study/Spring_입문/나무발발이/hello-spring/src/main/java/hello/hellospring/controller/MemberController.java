package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm.html";
    }

    //postmapping은 뭘까? html method가 post 방식이기 때문에 url은 똑같지만 이게 선택이된다
    @PostMapping("/members/new")
    public String create(MemberForm form){
        //어떤 방식으로 html id name이 create의 파라미터인 MemberForm로 넘어가는걸까?
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //home화면으로 돌림
    }
//
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}

package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//여기에 뒤에는 아무것도 없다 그러나 어떤일이 벌어지냐면
    //spring 통(컨테이너)이 생기는데 MemberController 가 생겨서 넣어둔다
    //그러면 넣어서 관리 해둔다
@Controller
public class MemberController {

    //    private MemberService memberService = new MemberService();
        //new해서도 쓸수있지만 이제 스프링 컨테이너로 부터 받아서 쓸수있게 해야함
        //왜냐 new하면 다른 컨트롤러에서도 서비스 가져다가쓸수있음
        //하나만 생성해서 공유하면 안되겠냐?

    @Autowired
    final private MemberService memberService;

//    @Autowired
    //'hello.hellospring.service.MemberService' that could not be found.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //다른하는거 없이 템플릿으로 보내줌
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //
    @PostMapping("/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        //form 안에 name에 입력한 그 값이 들어오게 됨
        member.setName(form.getName());

        memberService.join(member);

        System.out.println(member);

        //hmoe으로 보내주기
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();

        model.addAttribute("members", members);
        return "members/memberList";
    }


}

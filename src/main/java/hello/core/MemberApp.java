package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//
//        //기존에 직접넣는게 아니라 생성한것을 불러와서 의존관계 주입한다
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);
        //나는 memberSevice이름 객체 찾을거야
        //타입은 MemberService 로 할거야
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member =  " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

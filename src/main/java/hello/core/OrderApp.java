package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //각각의 Bean에 등록된 이름을 찾을것이다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null ,null);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 12000);

        System.out.println("order = " + order);
        System.out.println("order = " + order.calculatePrice());
    }
}

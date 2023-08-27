package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//공연기획자 자리
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepsitory());
    }

    @Bean
    //타입은 인터페이스로
    public MemberRepository memberRepsitory() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                disocuntPolicy(),
                memberRepsitory());
    }

    @Bean
    public DiscountPolicy disocuntPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}

package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService
                = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService
                = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepsitory = ac.getBean("memberRepsitory", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("MemberRepository -> memberRepsitory = " + memberRepsitory);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepsitory);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepsitory);


    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new
                AnnotationConfigApplicationContext(AppConfig.class);
//AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }

}

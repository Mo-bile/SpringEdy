package hello.hellospring;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//스프링이 이 어노 읽고 @bean을 스프링에 등록하라는 의미네 라고 스프링이인식함
@Configuration
public class SpringConfig {


    //이 어노 읽고 스프링빈에 등록함
    @Bean
    public MemberService memberService() {
                            //커맨드 p 누르니 뭐가 필요한지 알려줌 (@atuo 한것과 비슷)
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


}

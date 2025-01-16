package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(//읽기위해서 설정함

    basePackages = "hello.core",
    //뺴줌 왜냐 Confiuration @어노에는 @Component가 붙어있다
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {

/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/

}

package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

//    MemberService memberService = new MemberServiceImpl();
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        //기존에 직접넣는게 아니라 생성한것을 불러와서 의존관계 주입한다
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}

package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //테스트는 편하게 그냥 여기다가 주입하자! 여기서만 주입하고 쓰는것이 끝이기때문임
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

//방금 each도 다 지움 ->트랜잭션 때문에

    @Test
    void 회원가입() {
        //given : 데이터
        Member member = new Member();
    //성공적으로 들어옴, 그런데 테스트는 반복할수있어야함 -> 롤백까지 해주는것!!!
        member.setName("spring5");
        //when : 어떤경우
        Long saveId = memberService.join(member);

        //then : 결과
        Member findMember  = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring4");

        Member member2 = new Member();
        member2.setName("spring4");

        //when
        memberService.join(member1);
        //예외 발생하면, 람다로 넘어가야함
        // 터지면 join으로 간다
        //즉 member2 join은 예외 터져야한다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //NullPointerException 하면은 예상, 실제 불일치로 에러가 발생함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try{
//            memberService.join(member2);
//            fail(); //
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }

        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService(memberRepository);
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //다른대서 온건데 애매함
    // 사실 서비스것과 여기서 레포 객체는 서로 다른것임
    //멤버서비스로 가자

    //의존성 주입 방식으로 변경함
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

//    clear위해서 들고와줌
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //한국어로 가능함! 그리고 빌드될때 어차피 포함이 안됨
    @Test
    void 회원가입() {
        //given : 데이터
        Member member = new Member();
        member.setName("hello");
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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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
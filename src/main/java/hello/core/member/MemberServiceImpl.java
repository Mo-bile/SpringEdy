package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //인터페이스와 구현체 모두 의존하고 있는중 -> DIP 위배
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //지우고나서 직접 대입해주는 방식으로 해준다. -> 이제 추상화의존 즉 DIP준수
    // 구체화관련된것은 다른곳에서 설정
    // 생성자로 주입방식이라고 함
    private final MemberRepository memberRepository;

    //MemberRepository 타입에 맞는 애 찾아와서 자동으로 주입해줌
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

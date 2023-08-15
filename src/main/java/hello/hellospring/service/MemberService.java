package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {


//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //외부에서 넣어주게 바꿈
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    회원가입

    public Long join(Member member){

//        그런데 옵셔널 바로 꺼내는거 권장안됨
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        //옵셔널이기 때문에 가능함
//        //기존에는 if로 감쌋음
//        //ifPresent 있는지 여부
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

//    전체회원조회 -> 기획자가 이해할수 있는 용어로!
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}

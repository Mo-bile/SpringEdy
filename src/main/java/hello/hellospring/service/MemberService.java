package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//어노테이션 없어서 순수 자바임 그래서 어노 넣어줌
//@Service
public class MemberService {
    //스프링이 생성 될때 @service 보고 스프링컨테이너에 등록함
    //그러고 생성자를 호출함 Autowired가 있으면 넌 repo가필요하구나 하고
    // 컨테이너 있던 repo를 딱 넣어줌

    private final MemberRepository memberRepository;

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //외부에서 넣어주게 바꿈

//    @Autowired
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

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); //중복회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timsMs = finish - start;
            System.out.println("join " + timsMs + "ms");
        }

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

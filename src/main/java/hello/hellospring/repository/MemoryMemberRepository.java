package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //동시성 문제로 원래는 해쉬안씀
    private static Map<Long, Member> stroe = new HashMap<>();
    //0,1,2 키 값을 만들어줌
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {

        //id는 시스템이 지정해주는것임
        member.setId(++sequence);
        stroe.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        //옵셔널로 감싸면 null인것을 방지할 수 있음
        return Optional.ofNullable(stroe.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return stroe.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(stroe.values());
    }
    public void clearStore(){
        stroe.clear();
    }

}

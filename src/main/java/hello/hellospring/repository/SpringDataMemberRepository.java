package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스는 다중상속이 가능하다
public interface SpringDataMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    //이것만 하면 끝임
    //왜냐 JpaRepository가 읽고 자동으로 구현해줌
    @Override
    Optional<Member> findByName(String name);
}

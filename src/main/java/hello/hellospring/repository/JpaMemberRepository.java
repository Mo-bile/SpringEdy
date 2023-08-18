package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //JPA쓰면 항상 추가해주어야함
public class JpaMemberRepository implements MemberRepository{

    //jpa 라이브러리 받으면 자동으로 매니저 만들어 줌
    private final EntityManager em;


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영속하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        //jpql 이라는 쿼리언어임
        //객체 대상으로 쿼리 날림
        List<Member> result =
                em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }
}

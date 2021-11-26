package spring.springbasic.repository;

import spring.springbasic.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //jpa 가 insert 쿼리 만들어서 db에 넣어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입, 식별자
        return Optional.ofNullable(member); //optional로 반환하기 떄문, 값이 없을수도 있기때문에
    }


    //pk기반이 아니면 jpql 작성
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
         return em.createQuery("select m from Member as m",Member.class)
                .getResultList();
    }
}

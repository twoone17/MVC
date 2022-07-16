package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

//reqository로 spring이 등록을 해준다, component 어노테이션이 붙어있음, 컴포넌트 스캔의 대상이 된다
@Repository
@RequiredArgsConstructor //final로 넣어서 이렇게 해줄수있다
public class MemberRepository {

    //jpa entityManager에 주입해준다.
    //@PersistenceContext -> autowired로 바꿀수있다 스프링부트가 -> @RequiredArgsCosntructor로 롬복을 사용하여 안써줘도된다

    private final EntityManager em;
    //스프링이 EntityManager를 만들어서 injection해준다


//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    //persist 하면 영속성 컨텍스트에 member객체를 넣고 ,
    //transaction이 commit되는 시점에 db에 insert 쿼리가 들어가며 반영이 된다.
    public void save(Member member){
        em.persist(member);
        //이 순간에 영속성 컨텍스트를 올린다
        //key 랑 value가 있는데 id값이 키가 된다 , @GenerateValue 하면 id값이 항상 생성되는게 보장
        //key는 pk값이다. db 들어간 시점이 아니어도
    }

    //단건 조회
    public Member findOne(Long id){
        return em.find(Member.class,id);    //type, pk대입
    }

    /*sql을 거의 똑같긴한데 객체를 대상으로 검색한다.
      from 의 대상이 table이 아닌 객체
     */
    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }

    public List<Member> findByName(String name)
    {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name) //parameter가 위의 쿼리에 바인딩 된다
                .getResultList();
    }


}

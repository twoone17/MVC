package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //null을 처리하기 위해 널포인터execption을 방지하기 위해 (스터디떄함)
    List<Member> findAll();


}

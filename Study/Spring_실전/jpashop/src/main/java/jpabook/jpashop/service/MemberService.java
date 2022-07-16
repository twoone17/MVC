package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//jpa의 모든 데이터변경이나 로직은 transactional 안에서 수행되어야한다
@Transactional(readOnly = true)
//@AllArgsConstructor //lombok , 생성자를 자동으로 만들어준다
@RequiredArgsConstructor //final 이 있는 것만 생성자로 만들어준다. -> best
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    @Transactional
    //transactional 을 클래스 자체에서 써주면 다 먹고 method에서 써줄때 우선권을 얻는다
    public Long join(Member member){

        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
    //읽기만 할때는 readOnly = true를 넣는게 좋다
    //쓰기는 데이터 변경이 안되므로 넣으면 안된다,
   // @Transactional(readOnly = true) //jpa가 조회한 곳에서 성능을 최적화
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
   // @Transactional(readOnly = true)
    //단건조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}

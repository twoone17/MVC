package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appconfig = new AppConfig();
        //1.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appconfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appconfig.memberService();

        //참조값이 다른것을 확인
        //계속해서 객체가 생성이 되어서 비효율적이다, 5만개있으면 5만개 객체를 다 생성해야돼 그래서 싱글톤을 사용하구나
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        //의존하고있는 구현체까지 총 4개가 생성이 된다
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


    }
}

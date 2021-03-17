package BanpoXi.Dong;


import BanpoXi.Dong.repository.MemberRepository;
import BanpoXi.Dong.repository.MemoryMemberRepository;
import BanpoXi.Dong.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 코드로 직접 스프링 빈 등록하는 방법
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}

//과거에는 XML 로 등록했는데, 요즘에는 xml 거의 사용하지 않음


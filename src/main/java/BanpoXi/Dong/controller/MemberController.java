package BanpoXi.Dong.controller;

import BanpoXi.Dong.repository.MemberRepository;
import BanpoXi.Dong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 스프링컨테이너가 이걸 생성해서 들고있다.
    // 컨트롤러 , 이걸 스프링 빈(콩)이라고 함
    //스프링이 관리하게되면 내가직접 new 하지않고, 스프링이 만들어서 씀

    //하나만 만들어놓고 공용으로 쓰면 좋음
    private final MemberService memberService;

    @Autowired//연결시켜주는거야 그래서 와이어드
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}

package BanpoXi.Dong.service;

import BanpoXi.Dong.domain.Member;
import BanpoXi.Dong.repository.MemberRepository;
import BanpoXi.Dong.repository.MemoryMemberRepo;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepo();

    /*======================================================================
    회원가입
    */
    public Long join(Member member) {
        // 이 회원가입을 하는 Join 메서드는 동일한 이름의 회원가입을 허용하지 않습니다.

        Optional<Member> result = memberRepository.findByName(member.getName());
        // null 리턴의 위험이 있기 때문에 Optional로 한번 감싸서 리턴 합니다.

        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        //만약 존재하는 회원이라면, illegal 익셉션을 발생시킵니다.

        memberRepository.save(member);
        return member.getId();
    }

    public Long join_RefectoringV2(Member member) {
        // 이 회원가입을 하는 Join 메서드는 동일한 이름의 회원가입을 허용하지 않습니다.

        validateDuplicateMember(member);// 중복검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    /*======================================================================
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
/*
팁
- 서비스 로직 단에는, 기획자와 의사소통이 쉽도록 기획용어에 디펜던시 해서 코딩하면 편하다

 */
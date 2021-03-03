package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;

import java.util.*;

public class MemoryMemeberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();//동시성 문제가 있을수 있는데, 여기서는 고려할필요가 없고
    // 동시성문제가 있다면 컴포넌트해쉬맵
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }



    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}

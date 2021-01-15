package com.npee.myproject.service;

import com.npee.myproject.entity.domain.Member;
import com.npee.myproject.entity.domain.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryOld memberRepositoryOld;

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검출
        memberRepositoryOld.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // exception
        List<Member> findMembers = memberRepositoryOld.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepositoryOld.findAll();
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return
     */
    public Member findMember(Long memberId) {
        return memberRepositoryOld.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepositoryOld.findOne(id);
        member.setName(name);
    }
}

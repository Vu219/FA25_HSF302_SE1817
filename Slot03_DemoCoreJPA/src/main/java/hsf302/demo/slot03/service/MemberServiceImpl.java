package hsf302.demo.slot03.service;

import hsf302.demo.slot03.model.MemberEntity;
import hsf302.demo.slot03.repository.MemberRepository;
import hsf302.demo.slot03.repository.MemberRepositoryImpl;

public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl (String JpaName) {
        memberRepository = new MemberRepositoryImpl(JpaName);
    }

    @Override
    public boolean addMember(MemberEntity member) {
        return memberRepository.addMember(member);
    }
}

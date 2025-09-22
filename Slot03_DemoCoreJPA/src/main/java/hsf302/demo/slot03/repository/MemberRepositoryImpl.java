package hsf302.demo.slot03.repository;

import hsf302.demo.slot03.dao.MemberDAO;
import hsf302.demo.slot03.model.MemberEntity;

public class MemberRepositoryImpl implements MemberRepository {
    private MemberDAO memberDAO;
    public MemberRepositoryImpl(String JpaName) {
        memberDAO = new MemberDAO(JpaName);
    }

    @Override
    public boolean addMember(MemberEntity member) {
        return memberDAO.addMember(member);
    }

    @Override
    public boolean removeMember(MemberEntity member) {
        return memberDAO.removeMember(member);
    }

    @Override
    public MemberEntity getMemberById(MemberEntity member) {
        return memberDAO.getMemberById(member);
    }

    @Override
    public boolean updateMember(MemberEntity member) {
        return memberDAO.updateMember(member);
    }
}

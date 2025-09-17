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
}

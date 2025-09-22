package hsf302.demo.slot03.repository;

import hsf302.demo.slot03.model.MemberEntity;

import java.lang.reflect.Member;

public interface MemberRepository {
    public boolean addMember(MemberEntity member);
    public boolean removeMember(MemberEntity member);
    public MemberEntity getMemberById(MemberEntity member);
    public boolean updateMember(MemberEntity member);
}

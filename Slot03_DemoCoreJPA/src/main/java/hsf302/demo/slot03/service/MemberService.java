package hsf302.demo.slot03.service;

import hsf302.demo.slot03.model.MemberEntity;

public interface MemberService {
    public boolean addMember(MemberEntity member);
    public boolean removeMember(MemberEntity member);
    public MemberEntity getMemberById(MemberEntity member);
    public boolean updateMember(MemberEntity member);
}

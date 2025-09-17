package hsf302.demo.slot03;

import hsf302.demo.slot03.model.MemberEntity;
import hsf302.demo.slot03.service.MemberService;
import hsf302.demo.slot03.service.MemberServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        MemberService memberService = new MemberServiceImpl("JPA-Name");
        MemberEntity memberEntity = new MemberEntity("Vu", "halekaiqkahsn@gmail.com", "123456", 2);
        boolean success = memberService.addMember(memberEntity);
        if(success) {
            System.out.println("Member added successfully!");
        } else {
            System.out.println("Failed to add member.");
        }
    }
}
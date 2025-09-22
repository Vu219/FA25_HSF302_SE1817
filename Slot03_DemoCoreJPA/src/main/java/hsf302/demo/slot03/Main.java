package hsf302.demo.slot03;

import hsf302.demo.slot03.model.MemberEntity;
import hsf302.demo.slot03.model.RoleEntity;
import hsf302.demo.slot03.service.MemberService;
import hsf302.demo.slot03.service.MemberServiceImpl;
import hsf302.demo.slot03.service.RoleService;
import hsf302.demo.slot03.service.RoleServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Hello and welcome!");
//        MemberService memberService = new MemberServiceImpl("JPA-Name");
//
//       System.out.println("Please input member id:");
//        long choice = Long.parseLong(scanner.nextLine());
//       boolean isSuccess = memberService.removeMember(memberService.getMember(choice));
//
//       if(isSuccess){
//          System.out.println("remove member successfully");
//       } else {
//          System.out.println("remove member failed");
//       }
        String jpaName = "JPA-Name";
        RoleService roleService = null;
        MemberService memberService = null;

        try {
            // Khởi tạo các service, sử dụng đúng biến 'jpaName' đã khai báo
            roleService = new RoleServiceImpl(jpaName);
            memberService = new MemberServiceImpl(jpaName);

            // --- 1. Tạo và lưu một Role mới ---
            System.out.println("Đang tạo một role mới...");
            RoleEntity adminRole = new RoleEntity();
            adminRole.setRoleName("Admin");

            boolean roleAdded = roleService.addRole(adminRole);
            if(roleAdded) {
                System.out.println("Đã thêm thành công Role 'Admin' với ID: " + adminRole.getRoleId());
            } else {
                System.err.println("Thêm role thất bại. Có thể role này đã tồn tại.");
                return;
            }

            // --- 2. Tạo một Member mới và gán Role cho Member đó ---
            System.out.println("Đang tạo một member mới...");
            MemberEntity newMember = new MemberEntity();
            newMember.setName("John Doe");
            newMember.setEmail("john.doe@example.com");
            newMember.setPassword("securepassword123");
            newMember.setRoleEntity(adminRole);

            boolean memberAdded = memberService.addMember(newMember);
            if (memberAdded) {
                System.out.println("Đã thêm thành công Member 'John Doe' với Role 'Admin'.");
            } else {
                System.err.println("Thêm member thất bại. Có thể email này đã tồn tại.");
            }

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi không mong muốn trong quá trình thao tác với database.");
            e.printStackTrace();
        } finally {
            System.out.println("Chương trình kết thúc.");
            System.exit(0);
        }
    }
}
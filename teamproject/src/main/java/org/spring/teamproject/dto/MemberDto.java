package org.spring.teamproject.dto;

import lombok.*;
import org.spring.teamproject.entity.MemberEntity;
import org.spring.teamproject.role.Role;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {
    private Long no;

    @NotBlank(message = "이메일을 입력 해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력 해주세요. 4글자이상 10글자 이하로 입력하세요")
    private String password;

    @NotBlank(message = "배송주소를 입력 해주세요")
    private String address;

    @NotBlank(message = "이름을 입력해주세요")
    private String userName;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    private String phone;

    private Role role;

    private LocalDateTime createTime; //생성시에만 적용
    private LocalDateTime updateTime;// 수정 시에 적용
    public MemberDto(MemberEntity memberEntity) {
    }

    public static MemberDto updateMemberDto(MemberEntity memberEntity){

        MemberDto memberDto=new MemberDto();

        memberDto.setNo(memberEntity.getNo());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setAddress(memberEntity.getAddress());
        memberDto.setUserName(memberEntity.getUserName());
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        return memberDto;
    }

}

package com.koreait.springuser.data;

import com.koreait.springuser.entity.Member;
import com.koreait.springuser.entity.MemberRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private String userid;
    private String userpw;
    private Integer age;
    private String gender;
    private MemberRole role;

    @Builder
    public MemberDTO(String userid, String userpw, Integer age, String gender, MemberRole role) {
        this.userid = userid;
        this.userpw = userpw;
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    public Member toEntity() {
        return Member.builder()
                .userid(userid)
                .userpw(userpw)
                .age(age)
                .gender(gender)
                .role(role).build();
    }
}

package com.koreait.springuser.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@SequenceGenerator(
        name="seq_member_id",
        sequenceName = "seq_member_id",
        initialValue = 1,
        allocationSize = 1
)
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_id")
    private Long id;

    private String userid;
    private String userpw;
    private Integer age;
    private String gender;
    private LocalDateTime regdate;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
}

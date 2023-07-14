package com.gdsc.CGLH.entity;

import com.gdsc.CGLH.dto.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String loginId;

    private String password;
    private String nickname;

    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Waste> waste;

}

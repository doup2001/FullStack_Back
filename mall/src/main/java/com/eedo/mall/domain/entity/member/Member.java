package com.eedo.mall.domain.entity.member;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "memberRoleList")
public class Member {

    @Id
    private String email;

    private String pw;

    private String nickName;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        this.memberRoleList.add(memberRole);
    }

    public void clearRole() {
        this.memberRoleList.clear();
    }

    public void changePw(String pw) {
        this.pw = pw;
    }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }



}

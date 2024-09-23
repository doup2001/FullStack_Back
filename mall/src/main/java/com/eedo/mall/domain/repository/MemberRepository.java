package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 조인해서 한번에 처리
    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.email =:email")
    Optional<Member> getWithRoles(@Param("email") String email);



}

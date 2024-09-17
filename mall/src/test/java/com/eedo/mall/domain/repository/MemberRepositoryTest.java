package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.member.Member;
import com.eedo.mall.domain.entity.member.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegisterMember() throws Exception{


        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .email("doup200"+i+"@naver.com")
                    .pw(passwordEncoder.encode("1111"))
                    .nickName("USER "+i)
                    .social(false)
                    .build();

            member.addRole(MemberRole.USER);

            if (i >= 5)
                member.addRole(MemberRole.MANAGER);
            if (i >= 8)
                member.addRole(MemberRole.ADMIN);

            memberRepository.save(member);
        }
        //given


        //when


        //then

    }

    @Test
    public void testRead() throws Exception {
        //given

        String email = "doup2009@naver.com";


        //when

        Member member = memberRepository.getWithRoles(email);


        //then
        Assertions.assertEquals(member.getNickName(), "USER 9");
        log.info(member.getNickName());
        log.info(member.getMemberRoleList());

        //left join이 걸려있는것을 볼 수 있다.
    }

}
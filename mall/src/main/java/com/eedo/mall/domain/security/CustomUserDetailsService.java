package com.eedo.mall.domain.security;

import com.eedo.mall.domain.dto.MemberDTO;
import com.eedo.mall.domain.entity.member.Member;
import com.eedo.mall.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    // username은 id에 해당하는 값
    // 이번 경우는 email이 해당
    // userDetail이 dto에 해당
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("---------loadUserByUsername--------"+username);
        Optional<Member> result = memberRepository.getWithRoles(username);
        Member member = result.orElseThrow();

        MemberDTO memberDTO = new MemberDTO(member.getEmail(),
                member.getPw(),
                member.getNickName(),
                member.isSocial(),
                member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));

        return memberDTO;
    }
}

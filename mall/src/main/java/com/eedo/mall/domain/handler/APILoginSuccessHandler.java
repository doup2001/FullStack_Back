package com.eedo.mall.domain.handler;

import com.eedo.mall.domain.dto.MemberDTO;
import com.eedo.mall.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-------Success Handler------");
        log.info(authentication);

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

        Map<String, Object> claims = memberDTO.getClaims();
        claims.put("accessToken", JWTUtil.generateToken(claims,10));
        claims.put("refreshToken", JWTUtil.generateToken(claims,60*24));

        //JSon문자열로
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonStr);
        printWriter.close();
    }
}

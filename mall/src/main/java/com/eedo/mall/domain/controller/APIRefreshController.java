package com.eedo.mall.domain.controller;

import com.eedo.mall.util.CustomJWTException;
import com.eedo.mall.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member/refresh")
@Log4j2
public class APIRefreshController {


    // authHeader에서 access토큰 정보 얻어오고
    // 파라미터로 accessToken정보 얻어온다.



    @PostMapping
    public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader,
                                       @RequestParam("refreshToken") String refreshToken) {

        if (refreshToken == null) {
            return Map.of("error", "NO REFRESH TOKEN");
        }

        if (authHeader == null) {
            return Map.of("error", "NO ACCESS TOKEN");
        }

        String accessToken = authHeader.substring(7);

        boolean exp = checkExpToken(accessToken);

        // access o refresh o => same access & same refresh
        if (!exp) {
            //만료되지 않았으면
            return Map.of("AccessToken", accessToken, "refreshToken", refreshToken);

        }
        // access x , refresh condition

        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
        log.info("claims:" + claims);

        String newAccessToken = JWTUtil.generateToken(claims, 10);
        String newRefreshToken = checkTime((Integer) claims.get("exp")) ? JWTUtil.generateToken(claims, 24 * 60) : refreshToken;

        return Map.of("AccessToken", newAccessToken, "refreshToken", newRefreshToken);


    }


    private boolean checkTime(Integer exp) {

        //JWT exp를 날짜로 변환
        java.util.Date expDate = new java.util.Date( (long)exp * (1000));

        //현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = expDate.getTime() - System.currentTimeMillis();

        //분단위 계산
        long leftMin = gap / (1000 * 60);

        //1시간도 안남았는지..
        return leftMin < 60;
    }

    private boolean checkExpToken(String accessToken) {
        try {
            JWTUtil.validateToken(accessToken);
        } catch (CustomJWTException ex) {
            if (ex.getMessage().contains("Expired")) {
                return true;
            }
        }
        return false;
    }

}

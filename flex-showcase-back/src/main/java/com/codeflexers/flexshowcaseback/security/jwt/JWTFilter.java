package com.codeflexers.flexshowcaseback.security.jwt;

import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import com.codeflexers.flexshowcaseback.security.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     *
     * needAuthUrl 배열에 검사가 필요한 URL 작성
     * 일치하면 JWT 토큰 검사 후 CustomUserDetails 생성
     *
     * @param path 요청 경로
     * @return bool
     */
    private boolean checkUrl(String path){

        String[] needAuthUrl = {"/my-page"};

        for (String s : needAuthUrl) {
            if (s.startsWith(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println(request.getMethod());
        if(checkUrl(path) || (request.getMethod().equals("POST") && path.equals("/portfolio"))){
            String authorization = request.getHeader("Authorization");
            if(authorization == null || !authorization.startsWith("Bearer ")){
                System.out.println("token이 없거나, Bearer가 포함되어 있지 않습니다.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"토큰이 없거나, Bearer가 포함되어 있지 않습니다.");
                return;
            }
            String token = authorization.split(" ")[1];
            if(jwtUtil.isExpired(token)){
                System.out.println("token Expire 상태입니다.");
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"로그인이 만료되었습니다.");
                return;
            }

            Long accountCode = jwtUtil.getUserCode(token);
            String accountId = jwtUtil.getUsername(token);
            String accountRole = jwtUtil.getRole(token);
            String nickname = jwtUtil.getUserNickname(token);


            User user = new User();
            user.setUserId(accountId);
            user.setUserRole(accountRole);
            user.setUserCode(accountCode);
            user.setUserNickname(nickname);
            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request,response);
        } else {

            //테스트 코드 시작
//            User user = new User(1L,"wjdwltjq","wjdwltjq","ROLE_USER","정지섭");
//            CustomUserDetails customUserDetails = new CustomUserDetails(user);
//            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authToken);
            //테스트 코드 끝

            //검사 필요한거 아니면 넘어감 유저가 누군지 모름!
            filterChain.doFilter(request,response);
        }
    }
}

package com.inserm.gdi.ressourcetest.security;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inserm.gdi.ressourcetest.service.JwtValidation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final JwtValidation jwtValidation;

    public JWTFilter(UserDetailsService userDetailsService, JWTUtil jwtUtil, JwtValidation jwtValidation) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.jwtValidation = jwtValidation;
    }
/*
    @Override
    protected void doFilterInternal{
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username != null && Boolean.TRUE.equals(jwtUtil.validateToken(jwt))) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        chain.doFilter(request, response);
    }
*/

@Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain chain) throws ServletException, IOException {
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            if (jwtValidation.someRestCall(request.getHeader("Authorization")) != null) {
                System.out.println("JWT validation successful : ");
                System.out.println(jwtValidation.someRestCall(request.getHeader("Authorization")));
                JSONObject json = new JSONObject(jwtValidation.someRestCall(request.getHeader("Authorization")));
                System.out.println("json : " + json);
            }
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
}

}

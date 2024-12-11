package com.inserm.gdi.ressourcetest.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inserm.gdi.ressourcetest.model.CustomUserDetails;
import com.inserm.gdi.ressourcetest.service.JwtValidation;
import com.inserm.gdi.ressourcetest.service.UserDetailsConverter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTFilter extends OncePerRequestFilter {
    private final JwtValidation jwtValidation;

    public JWTFilter(JwtValidation jwtValidation) {
        this.jwtValidation = jwtValidation;
    }

@Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain chain) throws ServletException, IOException {
            String token = request.getHeader("Authorization");
            String jsonResponse = jwtValidation.someRestCall(token);
            CustomUserDetails userDetails = UserDetailsConverter.convert(jsonResponse);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
}

}

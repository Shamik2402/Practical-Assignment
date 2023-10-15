package com.project.practicalassignment.filters;

import com.project.practicalassignment.service.UserServiceImpl;
import com.project.practicalassignment.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if(!isAuthenticated()) {
            getToken(request)
                .map(token-> {
                    try {
                        return jwtUtil.validateToken(token);
                    } catch (Exception e) {
                        throw new AuthenticationCredentialsNotFoundException("Token Validation Failed " + e.getMessage());
                    }
                })
                .map(userDetailsService::loadUserByUsername)
                .ifPresent(this::setAuthentication);
        }
        chain.doFilter(request, response);
    }

    private void setAuthentication(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    private Optional<String> getToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7);
            return Optional.of(jwt);
        }

        return Optional.empty();
    }

}

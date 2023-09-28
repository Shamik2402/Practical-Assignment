package com.project.practicalassignment.service;

import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService service;

    private final String SECRET_KEY = "secret";

    @Test
    public void testLoadUserByUsername() {

        // given
        String username = "user";
        //when
        UserDetails details = service.loadUserByUsername(username);
        //then
        Assertions.assertEquals(details.getUsername(),username);
    }

    @Test
    public void testGetTokenAfterValidation() throws Exception {

        //given
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("user")
                .password("password")
                .build();

        UserDetails details = new User("user", "password", new ArrayList<>());

        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder().setClaims(claims).setSubject(details.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

        doReturn(token).when(jwtUtil).generateToken(eq(details));

        when(jwtUtil.generateToken(details)).thenReturn(token);
        when(jwtUtil.extractUsername(token)).thenReturn(details.getUsername());
        //when
        String generatedToken = service.authenticate(request);

        //then
        Assertions.assertEquals(token, generatedToken);
        Assertions.assertEquals(jwtUtil.extractUsername(token),request.getUsername());
    }

}

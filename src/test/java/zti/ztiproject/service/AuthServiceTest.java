package zti.ztiproject.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;
    private UserDetails userDetails;
    private String token;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
        userDetails = new User("testUser", "password", new ArrayList<>());
        token = authService.generateToken(userDetails);
    }

    @Test
    void extractUsername_ValidToken_ShouldExtractUsername() {
        String extractedUsername = authService.extractUsername(token);
        assertEquals(userDetails.getUsername(), extractedUsername);
    }

    @Test
    void generateToken_ShouldGenerateToken() {
        String generatedToken = authService.generateToken(userDetails);
        assertNotNull(generatedToken);
    }

    @Test
    void isTokenValid_ValidTokenAndUserDetails_ShouldReturnTrue() {
        assertTrue(authService.isTokenValid(token, userDetails));
    }

    @Test
    void extractClaim_ValidTokenAndClaimResolver_ShouldExtractClaim() {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("key", "value");

        String tokenWithClaims = authService.generateToken(extraClaims, userDetails);
        String extractedValue = authService.extractClaim(tokenWithClaims, claims -> claims.get("key", String.class));

        assertEquals("value", extractedValue);
    }
}

package zti.ztiproject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void testCreateUser_Success() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");
        Jwt jwt = new Jwt("token");

        when(accountService.createAccount(accountCreation)).thenReturn(jwt);

        // when
        ResponseEntity<Jwt> response = accountController.createUser(accountCreation);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwt, response.getBody());
        verify(accountService, times(1)).createAccount(accountCreation);
    }

    @Test
    void testCreateUser_Failure() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");

        when(accountService.createAccount(accountCreation)).thenReturn(null);

        // when
        ResponseEntity<Jwt> response = accountController.createUser(accountCreation);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(accountService, times(1)).createAccount(accountCreation);
    }

    @Test
    void testGetToken_Success() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");
        Jwt jwt = new Jwt("token");

        when(accountService.getToken(accountCreation)).thenReturn(jwt);

        // when
        ResponseEntity<Jwt> response = accountController.getToken(accountCreation);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwt, response.getBody());
        verify(accountService, times(1)).getToken(accountCreation);
    }

    @Test
    void testGetToken_Failure() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");

        when(accountService.getToken(accountCreation)).thenReturn(null);

        // when
        ResponseEntity<Jwt> response = accountController.getToken(accountCreation);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(accountService, times(1)).getToken(accountCreation);
    }
}

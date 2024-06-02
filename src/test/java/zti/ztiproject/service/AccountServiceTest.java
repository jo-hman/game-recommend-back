package zti.ztiproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.AccountRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount_Success() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");
        Account account = new Account("username", "password");
        Jwt jwt = new Jwt("token");

        when(accountRepository.findById("username")).thenReturn(Optional.empty());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(authService.generateToken(account)).thenReturn("token");

        // when
        Jwt response = accountService.createAccount(accountCreation);

        // then
        assertNotNull(response);
        assertEquals(jwt.getJwt(), response.getJwt());
        verify(accountRepository, times(1)).findById("username");
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(authService, times(1)).generateToken(account);
    }


    @Test
    void testCreateAccount_Failure() {
        // given
        AccountCreation accountCreation = new AccountCreation("existingUsername", "password");
        Account existingAccount = new Account("existingUsername", "password");

        when(accountRepository.findById("existingUsername")).thenReturn(Optional.of(existingAccount));

        // when
        Jwt response = accountService.createAccount(accountCreation);

        // then
        assertNull(response);
        verify(accountRepository, times(1)).findById("existingUsername");
    }

    @Test
    void testGetToken_Success() {
        // given
        AccountCreation accountCreation = new AccountCreation("username", "password");
        Account account = new Account("username", "password");
        Jwt jwt = new Jwt("token");

        when(accountRepository.findById("username")).thenReturn(Optional.of(account));
        when(authService.generateToken(account)).thenReturn("token");

        // when
        Jwt response = accountService.getToken(accountCreation);

        // then
        assertNotNull(response);
        assertEquals(jwt.getJwt(), response.getJwt());
        verify(accountRepository, times(1)).findById("username");
        verify(authService, times(1)).generateToken(account);
    }

    @Test
    void testGetToken_Failure() {
        // given
        AccountCreation accountCreation = new AccountCreation("nonexistentUsername", "password");

        when(accountRepository.findById("nonexistentUsername")).thenReturn(Optional.empty());

        // when
        Jwt response = accountService.getToken(accountCreation);

        // then
        assertNull(response);
        verify(accountRepository, times(1)).findById("nonexistentUsername");
    }
}

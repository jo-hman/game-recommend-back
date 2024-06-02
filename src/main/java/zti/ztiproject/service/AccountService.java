package zti.ztiproject.service;

import org.springframework.stereotype.Service;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.AccountRepository;

/**
 * Service class for managing user accounts.
 */
@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AuthService authService;

    /**
     * Constructs an AccountService with the specified repositories and services.
     * @param accountRepository the repository for managing user accounts
     * @param authService the authentication service
     */
    public AccountService(AccountRepository accountRepository, AuthService authService) {
        this.accountRepository = accountRepository;
        this.authService = authService;
    }

    /**
     * Creates a new user account.
     * @param account the account creation request containing username and password
     * @return a JWT token if the account is created successfully, otherwise null
     */
    public Jwt createAccount(AccountCreation account) {
        var accountOptional = accountRepository.findById(account.username());
        if (accountOptional.isEmpty()) {
            var acc = accountRepository.save(new Account(account.username(), account.password()));
            return new Jwt(authService.generateToken(acc));
        }
        return null;
    }

    /**
     * Retrieves a JWT token for the specified user account.
     * @param account the account creation request containing username and password
     * @return a JWT token if the account is found, otherwise null
     */
    public Jwt getToken(AccountCreation account) {
        return accountRepository.findById(account.username())
                .map(acc -> new Jwt(authService.generateToken(acc)))
                .orElse(null);
    }

}

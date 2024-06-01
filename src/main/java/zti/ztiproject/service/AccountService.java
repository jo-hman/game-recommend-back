package zti.ztiproject.service;

import org.springframework.stereotype.Service;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AuthService authService;

    public AccountService(AccountRepository accountRepository, AuthService authService) {
        this.accountRepository = accountRepository;
        this.authService = authService;
    }

    public Jwt createAccount(AccountCreation account) {
        var accountOptional = accountRepository.findById(account.username());
        if (accountOptional.isEmpty()) {
            var acc = accountRepository.save(new Account(account.username(), account.password()));
            return new Jwt(authService.generateToken(acc));
        }
        return null;
    }

    public Jwt getToken(AccountCreation account) {
        return accountRepository.findById(account.username())
                .map(acc -> new Jwt(authService.generateToken(acc)))
                .orElse(null);
    }

}

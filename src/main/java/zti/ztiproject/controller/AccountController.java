package zti.ztiproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.service.AccountService;

/**
 * Controller class for handling account-related endpoints.
 */
@RestController
@RequestMapping("accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates a new user account.
     * @param account the account creation request object
     * @return ResponseEntity containing a JWT if the account is created successfully, otherwise a bad request status
     */
    @PostMapping
    public ResponseEntity<Jwt> createUser(@RequestBody AccountCreation account) {
        var jwt = accountService.createAccount(account);
        return jwt == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(jwt);
    }

    /**
     * Retrieves a JWT token for the provided account credentials.
     * @param account the account credentials
     * @return ResponseEntity containing a JWT if the token is retrieved successfully, otherwise a bad request status
     */
    @PostMapping("/jwt")
    public ResponseEntity<Jwt> getToken(@RequestBody AccountCreation account) {
        var jwt = accountService.getToken(account);
        return jwt == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(jwt);
    }

}

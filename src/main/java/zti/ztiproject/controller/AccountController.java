package zti.ztiproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.ztiproject.controller.model.AccountCreation;
import zti.ztiproject.controller.model.Jwt;
import zti.ztiproject.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Jwt> createUser(@RequestBody AccountCreation account) {
        var jwt = accountService.createAccount(account);
        return jwt == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(jwt);
    }

    @PostMapping("/jwt")
    public ResponseEntity<Jwt> getToken(@RequestBody AccountCreation account) {
        var jwt = accountService.getToken(account);
        return jwt == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(jwt);
    }

}

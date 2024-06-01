package zti.ztiproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.Comment;
import zti.ztiproject.repository.Game;
import zti.ztiproject.service.GamesService;

import java.util.List;

@RestController
@RequestMapping("games")
public class GamesController {

    private GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gamesService.getGames());
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody GameCreation gameCreation, @AuthenticationPrincipal Account account) {
        gamesService.createGame(gameCreation, account);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String id) {
        return ResponseEntity.ok(gamesService.getComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> createComment(@PathVariable String id, @RequestBody CreateComment createComment, @AuthenticationPrincipal Account account){
        var result = gamesService.addComment(id, createComment, account);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/scores")
    public ResponseEntity<ScoreResponse> getScores(@PathVariable String id) {
        return ResponseEntity.ok(gamesService.getScore(id));
    }

    @PostMapping("/{id}/scores")
    public ResponseEntity<Void> createScore(@PathVariable String id, @RequestBody CreateScore createScore, @AuthenticationPrincipal Account account){
        var result = gamesService.addScore(id, createScore, account);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}



package zti.ztiproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import zti.ztiproject.controller.model.CreateComment;
import zti.ztiproject.controller.model.CreateScore;
import zti.ztiproject.controller.model.GameCreation;
import zti.ztiproject.controller.model.ScoreResponse;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.Comment;
import zti.ztiproject.repository.Game;
import zti.ztiproject.service.GamesService;

import java.util.List;

/**
 * Controller class for handling game-related endpoints.
 */
@RestController
@RequestMapping("games")
public class GamesController {

    private GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    /**
     * Retrieves all games.
     * @return ResponseEntity containing a list of all games
     */
    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gamesService.getGames());
    }

    /**
     * Creates a new game.
     * @param gameCreation the game creation request object
     * @param account the authenticated account
     * @return ResponseEntity indicating success or failure of the operation
     */
    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody GameCreation gameCreation, @AuthenticationPrincipal Account account) {
        gamesService.createGame(gameCreation, account);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves all comments for a specific game.
     * @param id the game ID
     * @return ResponseEntity containing a list of comments for the game
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String id) {
        return ResponseEntity.ok(gamesService.getComments(id));
    }

    /**
     * Adds a new comment to a specific game.
     * @param id the game ID
     * @param createComment the comment creation request object
     * @param account the authenticated account
     * @return ResponseEntity indicating success or failure of the operation
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> createComment(@PathVariable String id, @RequestBody CreateComment createComment, @AuthenticationPrincipal Account account){
        var result = gamesService.addComment(id, createComment, account);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     * Retrieves the score statistics for a specific game.
     * @param id the game ID
     * @return ResponseEntity containing the score statistics for the game
     */
    @GetMapping("/{id}/scores")
    public ResponseEntity<ScoreResponse> getScores(@PathVariable String id) {
        return ResponseEntity.ok(gamesService.getScore(id));
    }

    /**
     * Adds a new score to a specific game.
     * @param id the game ID
     * @param createScore the score creation request object
     * @param account the authenticated account
     * @return ResponseEntity indicating success or failure of the operation
     */
    @PostMapping("/{id}/scores")
    public ResponseEntity<Void> createScore(@PathVariable String id, @RequestBody CreateScore createScore, @AuthenticationPrincipal Account account){
        var result = gamesService.addScore(id, createScore, account);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}

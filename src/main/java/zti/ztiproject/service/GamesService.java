package zti.ztiproject.service;

import org.springframework.stereotype.Service;
import zti.ztiproject.controller.model.CreateComment;
import zti.ztiproject.controller.model.CreateScore;
import zti.ztiproject.controller.model.GameCreation;
import zti.ztiproject.controller.model.ScoreResponse;
import zti.ztiproject.repository.*;

import java.util.List;
import java.util.UUID;


/**
 * Service class responsible for handling game-related operations such as fetching games,
 * creating new games, adding comments and scores to games, and retrieving scores.
 */
@Service
public class GamesService {

    // Repository for accessing game data
    private GamesRepository gamesRepository;

    /**
     * Constructor for creating a new instance of GamesService.
     *
     * @param gamesRepository The repository for accessing game data.
     */
    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    /**
     * Retrieves all games from the repository.
     *
     * @return A list of all games.
     */
    public List<Game> getGames() {
        return gamesRepository.findAll();
    }

    /**
     * Creates a new game and saves it to the repository.
     *
     * @param gameCreation The details of the game to be created.
     * @param account      The account of the user creating the game.
     */
    public void createGame(GameCreation gameCreation, Account account) {
        gamesRepository.save(new Game(UUID.randomUUID().toString(), account.getUsername(), gameCreation.title(), gameCreation.description(), null, null));
    }

    /**
     * Retrieves the comments for a specific game.
     *
     * @param id The ID of the game for which to retrieve comments.
     * @return A list of comments for the specified game.
     */
    public List<Comment> getComments(String id) {
        return gamesRepository.findById(id).map(game -> game.getComments()).orElse(List.of());
    }

    /**
     * Adds a new comment to a specific game.
     *
     * @param id            The ID of the game to which the comment is being added.
     * @param createComment The details of the comment to be added.
     * @param account       The account of the user adding the comment.
     * @return {@code true} if the comment was successfully added, {@code false} otherwise.
     */
    public boolean addComment(String id, CreateComment createComment, Account account) {
        return gamesRepository.findById(id)
                .map(game -> {
                    if (game.getComments() == null) {
                        game.setComments(List.of(new Comment(account.getId(), createComment.comment())));
                    } else {
                        game.getComments().add(new Comment(account.getId(), createComment.comment()));
                    }
                    gamesRepository.save(game);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Adds a new score to a specific game.
     *
     * @param id          The ID of the game to which the score is being added.
     * @param createScore The details of the score to be added.
     * @param account     The account of the user adding the score.
     * @return {@code true} if the score was successfully added, {@code false} otherwise.
     */
    public boolean addScore(String id, CreateScore createScore, Account account) {
        return gamesRepository.findById(id)
                .map(game -> {
                    if (game.getScores() == null) {
                        game.setScores(List.of(createScore.score()));
                    } else {
                        game.getScores().add(createScore.score());
                    }
                    gamesRepository.save(game);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Retrieves the average score and count of scores for a specific game.
     *
     * @param id The ID of the game for which to retrieve the score information.
     * @return The average score and count of scores for the specified game.
     */
    public ScoreResponse getScore(String id) {
        return gamesRepository.findById(id)
                .map(game -> new ScoreResponse(getAverage(game.getScores()), game.getScores().size()))
                .orElse(null);
    }

    /**
     * Calculates the average of the provided scores.
     *
     * @param scores The list of scores for which to calculate the average.
     * @return The average of the provided scores.
     */
    private double getAverage(List<Integer> scores) {
        double sum = 0;
        if (scores == null) {
            return 0;
        }
        for (Integer score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }
}


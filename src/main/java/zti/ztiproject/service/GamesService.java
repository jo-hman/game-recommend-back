package zti.ztiproject.service;

import org.springframework.stereotype.Service;
import zti.ztiproject.controller.CreateComment;
import zti.ztiproject.controller.CreateScore;
import zti.ztiproject.controller.GameCreation;
import zti.ztiproject.controller.ScoreResponse;
import zti.ztiproject.repository.*;

import java.util.List;
import java.util.UUID;
import java.util.function.BinaryOperator;


@Service
public class GamesService {

    private GamesRepository gamesRepository;

    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public List<Game> getGames() {
        return gamesRepository.findAll();
    }

    public void createGame(GameCreation gameCreation, Account account) {
        gamesRepository.save(new Game(UUID.randomUUID().toString(), account.getUsername(), gameCreation.title(), gameCreation.description(), null, null));
    }

    public List<Comment> getComments(String id) {
        return gamesRepository.findById(id).map(game -> game.getComments()).orElse(List.of());
    }

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

    public ScoreResponse getScore(String id) {
        return gamesRepository.findById(id)
                .map(game -> new ScoreResponse(getAverage(game.getScores()), game.getScores().size()))
                .orElse(null);
    }

    private double getAverage(List<Integer> scores) {
        double sum = 0;
        if(scores == null) {
            return 0;
        }
        for (Integer score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }


}

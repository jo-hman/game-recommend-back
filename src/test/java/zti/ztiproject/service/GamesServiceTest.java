package zti.ztiproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zti.ztiproject.controller.model.GameCreation;
import zti.ztiproject.repository.Account;
import zti.ztiproject.repository.Comment;
import zti.ztiproject.repository.Game;
import zti.ztiproject.repository.GamesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GamesServiceTest {

    @Mock
    private GamesRepository gamesRepository;

    @InjectMocks
    private GamesService gamesService;

    @Test
    void getGames_ShouldReturnListOfGames() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("1", "user1", "Title1", "Description1", null, null));
        games.add(new Game("2", "user2", "Title2", "Description2", null, null));

        when(gamesRepository.findAll()).thenReturn(games);

        List<Game> result = gamesService.getGames();

        assertEquals(2, result.size());
    }

    @Test
    void createGame_ShouldCreateGame() {
        GameCreation gameCreation = new GameCreation("Title", "Description");
        Account account = new Account("user1", "password");

        gamesService.createGame(gameCreation, account);

        verify(gamesRepository, times(1)).save(any(Game.class));
    }

    @Test
    void getComments_ExistingGameId_ShouldReturnListOfComments() {
        String gameId = "1";
        Game game = new Game(gameId, "user1", "Title1", "Description1", null, null);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("user1", "Comment1"));
        comments.add(new Comment("user2", "Comment2"));
        game.setComments(comments);

        when(gamesRepository.findById(gameId)).thenReturn(Optional.of(game));

        List<Comment> result = gamesService.getComments(gameId);

        assertEquals(2, result.size());
    }

}

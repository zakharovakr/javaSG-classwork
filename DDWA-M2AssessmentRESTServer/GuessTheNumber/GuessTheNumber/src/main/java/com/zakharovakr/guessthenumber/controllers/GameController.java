package com.zakharovakr.guessthenumber.controllers;

import com.zakharovakr.guessthenumber.dtos.Game;
import com.zakharovakr.guessthenumber.dtos.Round;
import com.zakharovakr.guessthenumber.dtos.WebInput;
import com.zakharovakr.guessthenumber.services.BadGuessException;
import com.zakharovakr.guessthenumber.services.GameService;
import com.zakharovakr.guessthenumber.services.NoGameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame() {
        Game game = gameService.beginGame();
        return game.getID();
    }

    @PostMapping("/guess")
    public Round checkGuess(@RequestBody WebInput input) throws BadGuessException, NoGameException {
        Round round = gameService.checkGuess(input.getGuess(), input.getGameID());
        if (!round.getGame().getIsFinished()) {
            round.getGame().setAnswer("Still not solved!");
        }
        return round;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        List<Game> games = gameService.getAllGames();
        for (Game game : games) {
            if (!game.getIsFinished()) {
                game.setAnswer("Still not solved!");
            }
        }
        return games;
    }

    @GetMapping("/game/{gameID}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameID) throws NoGameException {
        Game game = gameService.getGameByID(gameID);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        if (!game.getIsFinished()) {
            game.setAnswer("Still not solved!");
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("/rounds/{gameID}")
    public List<Round> getAllRoundsByID(@PathVariable int gameID) throws NoGameException {
        List<Round> rounds = gameService.getAllRoundsByGameID(gameID);

        for (Round round : rounds) {
            if (!round.getGame().getIsFinished()) {
                round.getGame().setAnswer("Still not solved!");
            }
        }
        return rounds;
    }

}

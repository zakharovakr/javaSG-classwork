package com.zakharovakr.guessthenumber.services;

import com.zakharovakr.guessthenumber.daos.GameDAO;
import com.zakharovakr.guessthenumber.daos.RoundDAO;
import com.zakharovakr.guessthenumber.dtos.Game;
import com.zakharovakr.guessthenumber.dtos.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceJDBCImpl implements GameService {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private RoundDAO roundDAO;

    @Override
    public Game beginGame() {
        Game game = new Game();
        game.setAnswer(createNewNumber());
        game.setIsFinished(false);

        return gameDAO.create(game);
    }

    @Override
    public Round checkGuess(String guess, int gameID) throws BadGuessException, NoGameException {
        Game game = gameDAO.readByID(gameID);

        //change this
        if (game == null) {
            throw new NoGameException("Error: no game with such Id found.");
        }

        //throwing an exception is a guess has duplicate digits
        if (!isGuessUnique(guess)) {
            throw new BadGuessException("Error: your guess can't have duplicate digits!");
        }

        //throwing an exception if a guess contains other characters (not digits)
        if (!containsOnlyFourDigits(guess)) {
            throw new BadGuessException("Error: your guess can only contain 4 digits!");
        }

        Round round = new Round();
        round.setGame(game);
        round.setGuess(guess);
        round.setGuessTime(LocalDateTime.now());
        round.setResult(calculateResult(guess, game.getAnswer()));
        roundDAO.create(round);

        if (round.getResult().equalsIgnoreCase("e:4:p:0")) {
            game.setIsFinished(true);
            gameDAO.update(game);
        }

        return roundDAO.readByID(round.getID());
    }

    @Override
    public List<Game> getAllGames() {
        return gameDAO.readAll();
    }

    @Override
    public Game getGameByID(int id) throws NoGameException {
        Game game = gameDAO.readByID(id);
        if (game == null) {
            throw new NoGameException("Error: No game with such Id found.");
        }
        return game;
    }

    @Override
    public List<Round> getAllRoundsByGameID(int gameID) throws NoGameException {
        List<Round> rounds = roundDAO.readByGameID(gameID);
        if (rounds == null || rounds.isEmpty()) {
            throw new NoGameException("Error: No game with such Id found.");
        }
        return rounds;
    }

    private String createNewNumber() {
        String randomNumber = "";
        Random rand = new Random();
        int nextNumber;
        int indexToRemove;
        boolean keepGoing = true;
        ArrayList<Integer> numberPool = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numberPool.add(i);
        }

        for (int i = 0; i < 4; i++) {
            do {
                nextNumber = rand.nextInt(10);

                if (numberPool.contains(nextNumber)) {
                    randomNumber = randomNumber + nextNumber;
                    indexToRemove = numberPool.indexOf(nextNumber);
                    numberPool.remove(indexToRemove);
                    keepGoing = false;
                } else {
                    keepGoing = true;
                }
            } while (keepGoing);
        }

        return randomNumber;
    }

    private String calculateResult(String guess, String answer) {
        int exactMatches = 0;
        int partialMatches = 0;
        String finalResult;


        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                exactMatches++;
            } else if (answer.contains(guess.subSequence(i, i + 1))) {
                partialMatches++;
            }
        }

        finalResult = "e:" + exactMatches + ":p:" + partialMatches;
        return finalResult;
    }

    private boolean isGuessUnique (String input) {
        // If at any time we encounter 2 same
        // characters, return false
        for (int i = 0; i < input.length(); i++)
            for (int j = i + 1; j < input.length(); j++)
                if (input.charAt(i) == input.charAt(j))
                    return false;

        // If no duplicate characters encountered,
        // return true
        return true;
    }

    //checking if it contains 4 digits
    private boolean containsOnlyFourDigits(String guess) {
        if (guess.matches("[0-9]+") && guess.length() >= 4) {
            return true;
        }
        return false;
    }
}


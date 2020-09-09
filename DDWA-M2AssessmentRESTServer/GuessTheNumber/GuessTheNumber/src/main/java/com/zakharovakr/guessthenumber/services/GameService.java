package com.zakharovakr.guessthenumber.services;

import com.zakharovakr.guessthenumber.dtos.Game;
import com.zakharovakr.guessthenumber.dtos.Round;

import java.util.List;

public interface GameService {

    public Game beginGame();

    public Round checkGuess(String guess, int gameID) throws BadGuessException, NoGameException;

    public List<Game> getAllGames();

    public Game getGameByID(int id) throws NoGameException;

    public List<Round> getAllRoundsByGameID(int gameID) throws NoGameException;
}
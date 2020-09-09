package com.zakharovakr.guessthenumber.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {

    private int id;
    private Game game;
    private LocalDateTime guessTime;
    private String guess;
    private String result;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.game);
        hash = 37 * hash + Objects.hashCode(this.guessTime);
        hash = 37 * hash + Objects.hashCode(this.guess);
        hash = 37 * hash + Objects.hashCode(this.result);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }

}

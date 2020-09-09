package com.zakharovakr.guessthenumber.daos;

import com.zakharovakr.guessthenumber.dtos.Game;

import java.util.List;

public interface GameDAO {

    public Game create(Game game);

    public List<Game> readAll();

    public Game readByID(int id);

    public boolean update(Game game);

    public boolean delete(int id);

}

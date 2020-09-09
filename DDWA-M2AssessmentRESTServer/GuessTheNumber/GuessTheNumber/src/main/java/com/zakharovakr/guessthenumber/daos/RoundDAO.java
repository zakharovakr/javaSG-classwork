package com.zakharovakr.guessthenumber.daos;

import com.zakharovakr.guessthenumber.dtos.Round;

import java.util.List;

public interface RoundDAO {

    public Round create(Round round);

    public List<Round> readAll();

    public Round readByID(int id);

    public List<Round> readByGameID(int id);

    public boolean delete(int id);
}

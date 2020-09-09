package com.zakharovakr.guessthenumber.daos;

import com.zakharovakr.guessthenumber.dtos.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDAOJDBCImpl implements GameDAO {

    @Autowired
    private JdbcTemplate jdbc;

    private final String INSERT_GAME = "INSERT INTO Game(Answer, IsFinished) VALUES (?, ?)";
    private final String SELECT_ALL_GAMES = "SELECT GameID, Answer, IsFinished FROM Game";
    private final String SELECT_GAME_BY_ID = "SELECT GameID, Answer, IsFinished FROM Game WHERE GameID = ?";
    private final String UPDATE_GAME = "UPDATE Game SET IsFinished = ? WHERE GameID = ?";
    private final String DELETE_ROUND_BY_GAME = "DELETE FROM Round WHERE GameID = ?";
    private final String DELETE_GAME = "DELETE FROM Game WHERE GameID = ?";

    @Override
    @Transactional
    public Game create(Game game) {
        jdbc.update(INSERT_GAME, game.getAnswer(), game.getIsFinished());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setID(newID);
        return game;
    }

    @Override
    @Transactional
    public List<Game> readAll() {
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    @Transactional
    public Game readByID(int id) {
        try {
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean update(Game game) {
        return jdbc.update(UPDATE_GAME, game.getIsFinished(), game.getID()) > 0;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        jdbc.update(DELETE_ROUND_BY_GAME, id);
        return jdbc.update(DELETE_GAME, id) > 0;
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setID(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setIsFinished(rs.getBoolean("isFinished"));
            return game;
        }

    }

}

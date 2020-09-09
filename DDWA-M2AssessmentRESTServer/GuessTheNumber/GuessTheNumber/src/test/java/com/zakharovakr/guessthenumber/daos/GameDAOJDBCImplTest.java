package com.zakharovakr.guessthenumber.daos;

import com.zakharovakr.guessthenumber.TestApplicationConfiguration;
import com.zakharovakr.guessthenumber.dtos.Game;
import com.zakharovakr.guessthenumber.dtos.Round;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDAOJDBCImplTest {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private RoundDAO roundDAO;

    public GameDAOJDBCImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> games = gameDAO.readAll();
        for (Game game : games) {
            gameDAO.delete(game.getID());
        }

        List<Round> rounds = roundDAO.readAll();
        for (Round round : rounds) {
            roundDAO.delete(round.getID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create and readBy ID methods, of class GameDAOJDBCImpl.
     */
    @Test
    public void testCreateReadByID() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        Game fromDAO = gameDAO.readByID(game.getID());

        assertEquals(game, fromDAO);
    }

    /**
     * Test of readByID when no Game exists.
     */
    @Test
    public void testReadByIDNoGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);

        Game fromDAO = gameDAO.readByID(game.getID());
        assertNull(fromDAO);
    }

    /**
     * Test of readAll method, of class GameDAOJDBCImpl.
     */
    @Test
    public void testReadAll() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setIsFinished(false);
        gameDAO.create(game2);

        List<Game> games = gameDAO.readAll();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of readAll method when no Games exist.
     */
    @Test
    public void testReadAllNoGame() {
        List<Game> games = gameDAO.readAll();
        assertEquals(0, games.size());
    }

    /**
     * Test of update method, of class GameDAOJDBCImpl.
     */
    @Test
    public void testUpdate() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        Game fromDAO = gameDAO.readByID(game.getID());

        assertEquals(game, fromDAO);

        game.setIsFinished(true);

        gameDAO.update(game);

        assertNotEquals(game, fromDAO);

        fromDAO = gameDAO.readByID(game.getID());

        assertEquals(game, fromDAO);

    }

    /**
     * Test of delete method, of class GameDAOJDBCImpl.
     */
    @Test
    public void testDelete() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        gameDAO.delete(game.getID());

        Game fromDAO = gameDAO.readByID(game.getID());

        assertNull(fromDAO);
    }

}

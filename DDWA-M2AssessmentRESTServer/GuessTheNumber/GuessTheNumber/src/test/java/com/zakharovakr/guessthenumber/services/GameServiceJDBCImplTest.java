package com.zakharovakr.guessthenumber.services;

import com.zakharovakr.guessthenumber.TestApplicationConfiguration;
import com.zakharovakr.guessthenumber.daos.GameDAO;
import com.zakharovakr.guessthenumber.daos.RoundDAO;
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
public class GameServiceJDBCImplTest {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private RoundDAO roundDAO;

    @Autowired
    private GameService gameService;

    public GameServiceJDBCImplTest() {
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
     * Test of beginGame and getGameByID methods, of class GameServiceJDBCImpl.
     */
    @Test
    public void testBeginGameGetGameByID() throws NoGameException {
        Game game = gameService.beginGame();
        Game fromService = gameService.getGameByID(game.getID());

        String answer = game.getAnswer();

        //checking if the generated answer contains 4 characters
        assertTrue(answer.length() == 4);

        //checking if the characters are digits only
        assertTrue(answer.matches("[0-9]+"));

        //checking if the generated answer has duplicate digits
        String a = answer.substring(0, 1);
        String b = answer.substring(1, 2);
        String c = answer.substring(2, 3);
        String d = answer.substring(3, 4);
        assertTrue(!a.equals(b) || !a.equals(c) || !a.equals(d));
        assertTrue(!b.equals(c) || !b.equals(d));
        assertTrue(!c.equals(d));
        System.out.println(answer);

//        assertEquals(game, fromService);
//        assertFalse(fromService.getIsFinished());
    }

    /**
     * Test of checkGuess method when there is a complete match, of class
     * GameServiceJDBCImpl.
     */
    @Test
    public void testCheckGuessSuccess() throws BadGuessException, NoGameException {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        Round round = gameService.checkGuess("1234", game.getID());
        assertEquals("e:4:p:0", round.getResult());
        assertTrue(gameDAO.readByID(game.getID()).getIsFinished());

        try {
            gameService.checkGuess("1234", game.getID());
        } catch (BadGuessException e) {
            //assert
            fail("Guess was valid. No exception should have been thrown.");
        }

    }

    /**
     * Test of checkGuess method when there are partial matches, of class
     * GameServiceJDBCImpl.
     */
    @Test
    public void testCheckGuessFail() throws BadGuessException, NoGameException {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        //checking for duplicates
        try {
            gameService.checkGuess("0021", game.getID());
            fail("Expected BadGuessException was not thrown.");
        } catch (BadGuessException e) { //assert
            return;
        }

        //checking for other characters
        try {
            gameService.checkGuess("09t5", game.getID());
            fail("Expected BadGuessException was not thrown.");
        } catch (BadGuessException e) { //assert
            return;
        }

        //checking for the length (should be 4)
        try {
            gameService.checkGuess("875", game.getID());
            fail("Expected BadGuessException was not thrown.");
        } catch (BadGuessException e) { //assert
            return;
        }
    }

    @Test
    public void TestCheckGuessMatches() throws BadGuessException, NoGameException {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDAO.create(game);

        Round round = gameService.checkGuess ("9871", game.getID());
        assertEquals("e:0:p:1", round.getResult());
        assertFalse(gameDAO.readByID(game.getID()).getIsFinished());

        Round round2 = gameService.checkGuess ("1234", game.getID());
        assertEquals("e:4:p:0", round2.getResult());
    }

    /**
     * Test of checkGuess method when there are no games, of class
     * GameServiceJDBCImpl.
     */
    @Test
    public void testGetGameByIDNoGames() throws BadGuessException, NoGameException {
        Game game = new Game();
        game.setID(1);
        game.setAnswer("1234");
        game.setIsFinished(false);

        //checking for NoGameException
        try {
            gameService.getGameByID(2);
            fail("Expected BadGuessException was not thrown.");
        } catch (NoGameException e) { //assert
            return;
        }
    }

    /**
     * Test of getAllGames method, of class GameServiceJDBCImpl.
     */
    @Test
    public void testGetAllGames() {
        gameService.beginGame();
        gameService.beginGame();
        gameService.beginGame();

        List<Game> games = gameService.getAllGames();
        assertEquals(3, games.size());
    }

    /**
     * Test of getAllGames method when there are no Games, of class
     * GameServiceJDBCImpl.
     */
    @Test
    public void testGetAllGamesNoGames() {
        List<Game> games = gameService.getAllGames();
        assertEquals(0, games.size());
    }


    /**
     * Test of getAllRoundsByGameID method, of class GameServiceJDBCImpl.
     */
    @Test
    public void testGetAllRoundsByGameID() throws BadGuessException, NoGameException {
        Game game = gameService.beginGame();

        Round round = gameService.checkGuess("1234", game.getID());
        Round round2 = gameService.checkGuess("4567", game.getID());

        List<Round> rounds = gameService.getAllRoundsByGameID(game.getID());

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));

    }

    /**
     * Test of getAllRoundsByGameID method with no Games, of class
     * GameServiceJDBCImpl.
     */
    @Test
    public void testGetAllRoundsByGameIDNoGames() throws BadGuessException, NoGameException {
        Game game = new Game();
        game.setID(1);
        game.setAnswer("1234");
        game.setIsFinished(false);

        try {
            gameService.getGameByID(2);
            fail("Expected BadGuessException was not thrown.");
        } catch (NoGameException e) { //assert
            return;
        }

    }

}

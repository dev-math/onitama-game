import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameImplTest {
  private GameImpl game;

  @After
  public void print() {
    game.printBoard();
  }

  @Before
  public void setUp() {
    game = new GameImpl();
  }

  @Test
  public void testDefaultConstructor() {
    Assert.assertNotNull(game.getRedPlayer());
    Assert.assertNotNull(game.getBluePlayer());
    Assert.assertNotNull(game.getTableCard());
    Assert.assertNotNull(game.getCurrentPlayer());
    Assert.assertNull(game.getWinner());
  }

  @Test
  public void testConstructorWithPlayerNames() {
    String redPlayerName = "Player1";
    String bluePlayerName = "Player2";
    game = new GameImpl(redPlayerName, bluePlayerName);
    Assert.assertEquals(redPlayerName, game.getRedPlayer().getName());
    Assert.assertEquals(bluePlayerName, game.getBluePlayer().getName());
    Assert.assertNotNull(game.getRedPlayer());
    Assert.assertNotNull(game.getBluePlayer());
    Assert.assertNotNull(game.getTableCard());
    Assert.assertNotNull(game.getCurrentPlayer());
    Assert.assertNull(game.getWinner());
  }

  @Test
  public void testConstructorWithCustomDeck() {
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { new Position(-1, 0) }),
        new Card("Card2", Color.RED, new Position[] { new Position(-2, 0) }),
        new Card("Card3", Color.RED, new Position[] { new Position(1, 0) }),
        new Card("Card4", Color.RED, new Position[] { new Position(2, 0) }),
        new Card("Card5", Color.RED, new Position[] { new Position(3, 0) }),
    };
    String player1 = "Player 1";
    String player2 = "Player 2";
    game = new GameImpl(player1, player2, customDeck);
    Assert.assertEquals(player1, game.getRedPlayer().getName());
    Assert.assertEquals(player2, game.getBluePlayer().getName());
    Assert.assertNotNull(game.getRedPlayer());
    Assert.assertNotNull(game.getBluePlayer());
    Assert.assertNotNull(game.getTableCard());
    Assert.assertNotNull(game.getCurrentPlayer());
    Assert.assertNull(game.getWinner());

    Assert.assertTrue(Arrays.asList(customDeck).containsAll(Arrays.asList(game.getRedPlayer().getCards())));
    Assert.assertTrue(Arrays.asList(customDeck).containsAll(Arrays.asList(game.getBluePlayer().getCards())));
    Assert.assertTrue(Arrays.asList(customDeck).containsAll(Arrays.asList(game.getTableCard())));
  }

  @Test
  public void testGetSpotColor() {
    Position position = new Position(0, 0);
    Color spotColor = game.getSpotColor(position);
    Assert.assertEquals(Color.BLUE, spotColor);
  }

  @Test
  public void testGetPiece() {
    Position position = new Position(0, 2);
    Piece piece = game.getPiece(position);
    Assert.assertNotNull(piece);
    Assert.assertTrue(piece.isMaster());
  }

  @Test
  public void testGetTableCard() {
    Card tableCard = game.getTableCard();
    Assert.assertNotNull(tableCard);
  }

  @Test
  public void testGetRedPlayer() {
    Player redPlayer = game.getRedPlayer();
    Assert.assertNotNull(redPlayer);
  }

  @Test
  public void testGetBluePlayer() {
    Player bluePlayer = game.getBluePlayer();
    Assert.assertNotNull(bluePlayer);
  }

  @Test(expected = IllegalMovementException.class)
  public void testMakeMove_outsideBoard() throws IllegalMovementException {
    Position cardMove = new Position(-5, 0); // -5 sempre manda para fora do tabuleiro
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };

    game = new GameImpl("Player1", "Player2", customDeck);

    Card card = game.getCurrentPlayer().getCards()[0];
    Position currentPos = new Position(4, 2);
    game.makeMove(card, card.getPositions()[0], currentPos);
  }

  @Test(expected = IllegalMovementException.class)
  public void testMakeMove_spotOccupied() throws IllegalMovementException {
    Position cardMove = new Position(0, 1); // move a peça para a direita
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };

    game = new GameImpl("Player1", "Player2", customDeck);

    Card card = game.getCurrentPlayer().getCards()[0];
    Position currentPos = new Position(4, 2);
    game.makeMove(card, card.getPositions()[0], currentPos);
  }

  @Test(expected = InvalidCardException.class)
  public void testMakeMove_InvalidCardException() throws InvalidCardException {
    Position cardMove = new Position(0, 1);
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };

    game = new GameImpl("Player1", "Player2", customDeck);

    Card card = new Card("Random Card", Color.BLUE, new Position[] { cardMove });
    Position currentPos = new Position(4, 2);
    game.makeMove(card, card.getPositions()[0], currentPos);
  }

  @Test(expected = IncorrectTurnOrderException.class)
  public void testMakeMove_IncorrectTurnOrderException() throws IncorrectTurnOrderException {
    Position cardMove = new Position(1, 0);
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };
    game = new GameImpl("Player 1", "Player 2", customDeck);

    Position currentPos = new Position(0, 2); // Peça azul

    Card card = game.getCurrentPlayer().getCards()[0];
    game.makeMove(card, cardMove, currentPos);
  }

  @Test(expected = InvalidPieceException.class)
  public void testMakeMove_InvalidPieceException() throws InvalidPieceException {
    Position cardMove = new Position(1, 0);
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };
    game = new GameImpl("Player 1", "Player 2", customDeck);

    Position currentPos = new Position(3, 2); // Nenhuma peça

    Card card = game.getCurrentPlayer().getCards()[0];
    game.makeMove(card, cardMove, currentPos);
  }

  @Test
  public void testMakeMove() {
    Position cardMove = new Position(-1, 0);
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };
    game = new GameImpl("Player 1", "Player 2", customDeck);

    // move o mestre do player2 (vermelho) uma casa para cima
    Card card = game.getCurrentPlayer().getCards()[0];
    Position currentPos = new Position(4, 2);
    Piece piece = game.getPiece(currentPos);
    Card oldTableCard = game.getTableCard();

    game.makeMove(card, cardMove, currentPos);

    // testa se liberou o espaço do tabuleiro
    Assert.assertNull(game.getPiece(currentPos));

    // testa se moveu a peça
    Assert.assertTrue(game.getPiece(new Position(3, 2)).equals(piece));

    // testa se as cartas foram trocadas
    Assert.assertTrue(game.getTableCard().equals(card));
    Assert.assertTrue(game.getRedPlayer().getCards()[0].equals(oldTableCard));

    // testa se o turno passou para o azull
    Assert.assertTrue(game.getCurrentPlayer().equals(game.getBluePlayer()));
  }

  @Test
  public void testCheckVictory_NoWinner() {
    boolean victory = game.checkVictory(Color.BLUE);
    Assert.assertFalse(victory);
  }

  @Test
  public void testCheckVictory_Winner() {
    Position cardMove = new Position(-4, 0);
    Card[] customDeck = new Card[] {
        new Card("Card1", Color.RED, new Position[] { cardMove }),
        new Card("Card2", Color.RED, new Position[] { cardMove }),
        new Card("Card3", Color.RED, new Position[] { cardMove }),
        new Card("Card4", Color.RED, new Position[] { cardMove }),
        new Card("Card5", Color.RED, new Position[] { cardMove }),
    };
    game = new GameImpl("Player 1", "Player 2", customDeck);

    // coloca o mestre vermelho na casa do mestre azul
    Card card = game.getCurrentPlayer().getCards()[0];
    Position currentPos = new Position(4, 2);

    game.makeMove(card, cardMove, currentPos);

    boolean victory = game.checkVictory(Color.RED);
    Assert.assertTrue(victory);
  }
}

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class PlayerTest {
  private Player player;
  private Card[] cards;

  @Before
  public void setUp() {
    Card card1 = new Card("Card 1", Color.RED, new Position[] { new Position(0, 1) });
    Card card2 = new Card("Card 2", Color.BLUE, new Position[] { new Position(1, 0) });
    cards = new Card[] { card1, card2 };
    player = new Player("Player 1", Color.RED, cards);
  }

  @Test
  public void testGetName() {
    Assert.assertEquals("Player 1", player.getName());
  }

  @Test
  public void testGetPieceColor() {
    Assert.assertEquals(Color.RED, player.getPieceColor());
  }

  @Test
  public void testGetCards() {
    Assert.assertArrayEquals(cards, player.getCards());
  }

  @Test
  public void testSwapCard() throws InvalidCardException {
    Card newCard = new Card("New Card", Color.BLUE, new Position[] { new Position(2, 2) });
    player.swapCard(cards[0], newCard);
    Assert.assertEquals(newCard, player.getCards()[0]);
  }

  @Test(expected = InvalidCardException.class)
  public void testSwapCardWithInvalidCard() throws InvalidCardException {
    Card invalidCard = new Card("Invalid Card", Color.RED, new Position[] { new Position(0, 0) });
    Card newCard = new Card("New Card", Color.BLUE, new Position[] { new Position(2, 2) });
    player.swapCard(invalidCard, newCard);
  }
}

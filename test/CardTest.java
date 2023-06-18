import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardTest {
  private Card card;

  @Before
  public void setup() {
    Position[] positions = {
        new Position(1, 0),
        new Position(0, 1),
        new Position(-1, 0),
        new Position(0, -1)
    };
    card = new Card("TestCard", Color.RED, positions);
  }

  @Test
  public void testGetName() {
    String expectedName = "TestCard";
    String actualName = card.getName();
    Assert.assertEquals(expectedName, actualName);
  }

  @Test
  public void testGetColor() {
    Color expectedColor = Color.RED;
    Color actualColor = card.getColor();
    Assert.assertEquals(expectedColor, actualColor);
  }

  @Test
  public void testGetPositions() {
    Position[] expectedPositions = {
        new Position(1, 0),
        new Position(0, 1),
        new Position(-1, 0),
        new Position(0, -1)
    };
    Position[] actualPositions = card.getPositions();
    Assert.assertArrayEquals(expectedPositions, actualPositions);
  }

  @Test
  public void testCreateCards() {
    Card[] cards = Card.createCards();
    Assert.assertEquals(5, cards.length);

    for (Card card : cards) {
      Assert.assertNotNull(card.getName());
      Assert.assertNotNull(card.getColor());
      Assert.assertNotNull(card.getPositions());
    }
  }
}

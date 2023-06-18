import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class PieceTest {
  private Piece redPiece;
  private Piece bluePiece;

  @Before
  public void setUp() {
    redPiece = new Piece(Color.RED, true);
    bluePiece = new Piece(Color.BLUE, false);
  }

  @Test
  public void testGetColor() {
    Assert.assertEquals(Color.RED, redPiece.getColor());
    Assert.assertEquals(Color.BLUE, bluePiece.getColor());
  }

  @Test
  public void testIsMaster() {
    Assert.assertTrue(redPiece.isMaster());
    Assert.assertFalse(bluePiece.isMaster());
  }
}

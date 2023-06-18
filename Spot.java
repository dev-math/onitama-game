import java.util.Objects;

/**
 * Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
 */
public class Spot {
  private Piece piece;
  private Position pos;
  private Color color;

  /**
   * Construtor para espaços com peça e com cor
   * 
   * @param piece Peça que inicia nesse espaço do tabuleiro
   * @param pos   Posição do espaço no tabuleiro
   * @param color Cor do espaço no tabuleiro (Templo)
   */
  public Spot(Piece piece, Position pos, Color color) {
    this.piece = piece;
    this.pos = pos;
    this.color = color;
  }

  /**
   * Construtor para espaços com peça e sem cor
   * 
   * @param piece Peça que inicia nesse espaço do tabuleiro
   * @param pos   Posição do espaço no tabuleiro
   */
  public Spot(Piece piece, Position pos) {
    this.piece = piece;
    this.pos = pos;
    this.color = Color.NONE;
  }

  /**
   * Construtor para espaços sem peça e sem cor
   * 
   * @param pos Posição do espaço no tabuleiro
   */
  public Spot(Position pos) {
    this.piece = null;
    this.pos = pos;
    this.color = Color.NONE;
  }

  /**
   * Método que devolve a posição (coordenadas) do espaço
   * 
   * @return Objeto Position contendo a posição (coordenadas) do espaço
   */
  public Position getPosition() {
    return pos;
  }

  /**
   * Método que devolve a peça contida neste espaço
   * 
   * @return Objeto Piece caso tenha uma peça ou null caso o espaço esteja vazio
   */
  public Piece getPiece() {
    return piece;
  }

  /**
   * Método que devolve a cor do espaço
   * 
   * @return Enum Color com a cor do espaço. Caso o espaço não tenha cor, o valor
   *         do enum será NONE
   */
  public Color getColor() {
    return color;
  }

  /**
   * Método que ocupa o espaço atual com a peça passada
   * 
   * @param piece A peça para ocupar este espaço
   * @exception IllegalMovementException Caso o espaço já esteja ocupado por uma
   *                                     peça da mesma cor
   */
  protected void occupySpot(Piece piece) throws IllegalMovementException {
    if (this.piece != null && piece.getColor() == this.piece.getColor()) {
      throw new IllegalMovementException("Cannot occupy spot with a piece of the same color");
    }
    this.piece = piece;
  }

  /**
   * Método que "libera" o espaço atual, ou seja, deixa-o vazio
   */
  protected void releaseSpot() {
    this.piece = null;
  }

  @Override
  public String toString() {
    return "Spot at position " + pos.toString() + ", color: " + color.toString() + ", piece: "
        + (piece != null ? piece.toString() : "None");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Spot other = (Spot) obj;
    return Objects.equals(piece, other.piece) && Objects.equals(pos, other.pos) && color == other.color;
  }
}

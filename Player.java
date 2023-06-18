/**
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class Player {
  private String name;
  private Color pieceColor;
  private Card[] cards;

  /**
   * Construtor que define informações básicas do jogador
   * 
   * @param name       Nome do jogador
   * @param pieceColor Cor das peças do jogador
   * @param cards      Cartas na mão do jogador
   */
  public Player(String name, Color pieceColor, Card[] cards) {
    this.name = name;
    this.pieceColor = pieceColor;
    this.cards = cards;
  }

  /**
   * Construtor que define informações básicas do jogador
   * 
   * @param name       Nome do jogador
   * @param pieceColor Cor das peças do jogador
   * @param card1      A primeira carta na mão do jogador
   * @param card2      A segunda carta na mão do jogador
   */
  public Player(String name, Color pieceColor, Card card1, Card card2) {
    this.name = name;
    this.pieceColor = pieceColor;
    this.cards = new Card[] { card1, card2 };
  }

  /**
   * Método que devolve o nome do jogador(a)
   * 
   * @return String com o nome do jogador(a)
   */
  public String getName() {
    return name;
  }

  /**
   * Método que devolve a cor das peças do jogador
   * 
   * @return Enum Color com a cor das peças do jogador
   */
  public Color getPieceColor() {
    return pieceColor;
  }

  /**
   * Método que devolve as cartas da mão do jogador
   * 
   * @return Array de objetos Card representando as cartas da mão do jogador
   */
  public Card[] getCards() {
    return cards;
  }

  /**
   * Verifica se o jogador possui uma determinada carta em sua mão.
   *
   * @param card A carta a ser verificada
   * @return true se o jogador possuir a carta, false caso contrário
   */
  public boolean hasCard(Card card) {
    for (Card c : cards) {
      if (c.equals(card)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Método que troca uma carta da mão por outra carta (idealmente da mesa)
   * 
   * @param oldCard A carta que será substituída
   * @param newCard A carta que irá substituir
   * @throws InvalidCardException Caso a carta não esteja na mão do jogador e/ou
   *                              na mesa
   */
  protected void swapCard(Card oldCard, Card newCard) throws InvalidCardException {
    if (!hasCard(oldCard)) {
      throw new InvalidCardException("The card to be replaced is not in the player's hand.");
    }

    // if (!game.getTableCard().equals(oldCard)) {
    //   throw new InvalidCardException("The card to be replaced is not on the table.");
    // }

    // Substituir a carta na mão do jogador pela nova carta
    for (int i = 0; i < cards.length; i++) {
      if (cards[i].equals(oldCard)) {
        cards[i] = newCard;
        return;
      }
    }
  }
}

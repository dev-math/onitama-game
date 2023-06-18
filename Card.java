import java.util.Random;

/**
 * Classe que contém informações das cartas
 */
public class Card {
  private String name;
  private Color color;
  private Position[] positions;

  /**
   * Construtor que define os principais atributos de uma cara
   * 
   * @param name      Nome da carta
   * @param color     Cor da carta
   * @param positions Todas as posições relativas de movimento
   */
  public Card(String name, Color color, Position[] positions) {
    this.name = name;
    this.color = color;
    this.positions = positions;
  }

  /**
   * Método que devolve o nome da carta
   * 
   * @return String que contém o nome da carta
   */
  public String getName() {
    return name;
  }

  /**
   * Método que devolve a cor da carta
   * 
   * @return Enum Color que contém a cor da carta
   */
  public Color getColor() {
    return color;
  }

  /**
   * Método que devolve todas as possíveis posições relativas de movimento.
   * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as
   * possíveis posições de movimento em relação ao ponto de origem.
   * 
   * @return Um array de Position contendo todas as possíveis posições de
   *         movimento em relação ao ponto de origem
   */
  public Position[] getPositions() {
    return positions;
  }

  /**
   * Seleciona aleatoriamente cinco cartas de um baralho.
   *
   * @param deck O baralho de cartas.
   * @return Um vetor com as cinco cartas selecionadas.
   */
  public static Card[] pickGameCards(Card[] deck) {
    Random random = new Random();
    Card[] selectedCards = new Card[5];

    for (int i = 0; i < 5; i++) {
      int randomIndex = random.nextInt(deck.length);
      selectedCards[i] = deck[randomIndex];
    }

    return selectedCards;
  }

  /**
   * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que
   * serão utilizadas na partida.
   * 
   * @return Vetor de cartas com todas as cartas do jogo
   */
  public static Card[] createCards() {
    Card[] allCards = {
        // Cartas Vermelhas
        new Card(CardName.DRAGON.name(), Color.RED,
            new Position[] { new Position(1, -1), new Position(1, 1), new Position(-1, -2), new Position(-1, 2) }),
        new Card(CardName.FROG.name(), Color.RED,
            new Position[] { new Position(0, -2), new Position(-1, -1), new Position(1, 1) }),
        new Card(CardName.ELEPHANT.name(), Color.RED,
            new Position[] { new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1) }),
        new Card(CardName.ROOSTER.name(), Color.RED,
            new Position[] { new Position(-1, 1), new Position(1, -1), new Position(0, 1), new Position(0, -1) }),
        // Cartas Azuis
        new Card(CardName.TIGER.name(), Color.BLUE, new Position[] { new Position(-2, 0), new Position(1, 0) }),
        new Card(CardName.GOOSE.name(), Color.BLUE,
            new Position[] { new Position(-1, -1), new Position(0, -1), new Position(0, 1), new Position(1, 1) }),
        new Card(CardName.RABBIT.name(), Color.BLUE,
            new Position[] { new Position(1, -1), new Position(-1, 1), new Position(0, 2) }),
        new Card(CardName.CRAB.name(), Color.BLUE,
            new Position[] { new Position(0, -2), new Position(0, 2), new Position(-1, 0) }),
    };

    return pickGameCards(allCards);
  }
}

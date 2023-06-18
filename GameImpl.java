public class GameImpl implements Game {
  private final static int BOARD_SIZE = 5;
  private Spot[][] board = new Spot[5][5];
  private Card tableCard;
  private Player redPlayer;
  private Player bluePlayer;
  private Player currentPlayer;
  private Player winner;
  private Card[] deck;
  private final int BLUE_TEMPLE = 0;
  private final int RED_TEMPLE = 4;

  /**
   * Construtor que recebe nenhum parâmetro.
   * Cria um jogo com jogadores padrão (Red Player e Blue Player) e inicializa o
   * tabuleiro.
   */
  public GameImpl() {
    this.deck = Card.createCards();
    this.redPlayer = new Player("Red Player", Color.RED, deck[0], deck[1]);
    this.bluePlayer = new Player("Blue Player", Color.BLUE, deck[2], deck[3]);
    this.tableCard = deck[4];
    this.currentPlayer = (tableCard.getColor() == Color.RED) ? redPlayer : bluePlayer;
    this.winner = null;
    initializeBoard();
  }

  /**
   * Construtor que recebe duas Strings como parâmetro, representando os nomes dos
   * jogadores de Peças Vermelhas e Azuis, respectivamente.
   *
   * @param redPlayerName  O nome do jogador de peças vermelhas.
   * @param bluePlayerName O nome do jogador de peças azuis.
   */
  public GameImpl(String redPlayerName, String bluePlayerName) {
    this.deck = Card.createCards();
    this.redPlayer = new Player(redPlayerName, Color.RED, deck[0], deck[1]);
    this.bluePlayer = new Player(bluePlayerName, Color.BLUE, deck[2], deck[3]);
    this.tableCard = deck[4];
    this.currentPlayer = (tableCard.getColor() == Color.RED) ? redPlayer : bluePlayer;
    this.winner = null;
    initializeBoard();
  }

  /**
   * Construtor que recebe duas Strings como parâmetro, representando os nomes do
   * jogador de Peças Vermelhas e Azuis, respectivamente. Adicionalmente, o
   * construtor
   * recebe também um Array de Cards. Este Array de Cards terá um Deck totalmente
   * novo, com cartas que não estão nesse enunciado. A implementação selecionará 5
   * cartas aleatórias desse novo deck passado como parâmetro do construtor, ao
   * invés de
   * usar o deck criado no método createCards().
   *
   * @param redPlayerName  O nome do jogador de peças vermelhas.
   * @param bluePlayerName O nome do jogador de peças azuis.
   * @param customDeck     O deck personalizado de cartas.
   */
  public GameImpl(String redPlayerName, String bluePlayerName, Card[] customDeck) {
    this.deck = Card.pickGameCards(customDeck);
    this.redPlayer = new Player(redPlayerName, Color.RED, deck[0], deck[1]);
    this.bluePlayer = new Player(bluePlayerName, Color.BLUE, deck[2], deck[3]);
    this.tableCard = deck[4];
    this.currentPlayer = (tableCard.getColor() == Color.RED) ? redPlayer : bluePlayer;
    this.winner = null;
    initializeBoard();
  }

  /**
   * Inicializa o tabuleiro do jogo, atribuindo as peças e cores correspondentes a
   * cada posição (espaço/spot).
   */
  private void initializeBoard() {
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Position position = new Position(row, col);

        if (row == BLUE_TEMPLE || row == RED_TEMPLE) {
          Color color;
          if (row == BLUE_TEMPLE) {
            color = Color.BLUE;
          } else {
            color = Color.RED;
          }

          boolean isMaster = (col == 2);
          Piece piece = new Piece(color, isMaster);
          board[row][col] = new Spot(piece, position, color);
        } else {
          board[row][col] = new Spot(position);
        }
      }
    }
  }

  /**
   * Alterna o turno entre os jogadores.
   */
  private void switchTurn() {
    Player redPlayer = getRedPlayer();
    Player bluePlayer = getBluePlayer();
    setCurrentPlayer((getCurrentPlayer() == redPlayer) ? bluePlayer : redPlayer);
  }

  /**
   * Verifica se o movimento realizado é uma jogada vencedora.
   * Se a peça no destino for o mestre, define o jogador atual como vencedor.
   * Se a peça movida for o mestre, verifica se alcançou o templo adversário.
   *
   * @param destinationSpot O espaço de destino para onde a peça está sendo
   *                        movida.
   * @param piece           A peça que está sendo movida.
   */
  private void checkWinningMove(Spot destinationSpot, Piece piece) {
    // If the piece at the destination is the master, set the current player as the
    // winner
    Piece destinationPiece = destinationSpot.getPiece();
    if (destinationPiece != null && destinationPiece.isMaster()) {
      setWinner(currentPlayer);
    }

    // If the moved piece is the master, check if it reached the opponent's temple
    if (piece.isMaster() && destinationSpot.getColor() != Color.NONE) {
      if (destinationSpot.getColor() != piece.getColor()) {
        setWinner(currentPlayer);
      }
    }
  }

  /**
   * Obtém o espaço (Spot) na posição especificada do tabuleiro.
   *
   * @param position A posição do tabuleiro para obter o espaço.
   * @return O objeto Spot correspondente à posição especificada. Retorna null se
   *         a posição estiver fora dos limites do tabuleiro.
   */
  private Spot getSpot(Position position) {
    int row = position.getRow();
    int col = position.getCol();
    if (row >= 0 && row < 5 && col >= 0 && col < 5) {
      return board[row][col];
    }
    return null;
  }

  @Override
  public Color getSpotColor(Position position) {
    Spot spot = getSpot(position);
    return spot.getColor();
  }

  @Override
  public Piece getPiece(Position position) {
    Spot spot = getSpot(position);
    if (spot == null) {
      return null;
    }
    return spot.getPiece();
  }

  @Override
  public Card getTableCard() {
    return tableCard;
  }

  /**
   * Define a carta que está na mesa, que será substituída após a próxima jogada.
   *
   * @param tableCard A carta a ser definida como a nova carta da mesa.
   */
  public void setTableCard(Card tableCard) {
    this.tableCard = tableCard;
  }

  /**
   * Obtém o jogador atual.
   *
   * @return O objeto Player que representa o jogador atual.
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Define o jogador atual.
   *
   * @param currentPlayer O objeto Player que representa o novo jogador atual.
   */
  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  /**
   * Define o jogador vencedor.
   *
   * @param winner O objeto Player que representa o jogador vencedor.
   */
  public void setWinner(Player winner) {
    this.winner = winner;
  }

  /**
   * Obtém o jogador vencedor.
   *
   * @return O objeto Player que representa o jogador vencedor, ou null se não
   *         houver vencedor.
   */
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player getRedPlayer() {
    return redPlayer;
  }

  @Override
  public Player getBluePlayer() {
    return bluePlayer;
  }

  @Override
  public void makeMove(Card card, Position cardMove, Position currentPos)
      throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {
    Piece piece = getPiece(currentPos);
    Player currentPlayer = getCurrentPlayer();
    if (piece == null) {
      throw new InvalidPieceException("The selected position does not contain a piece.");
    }

    if (currentPlayer.getPieceColor() != piece.getColor()) {
      throw new IncorrectTurnOrderException("It is not the turn of player " + piece.getColor() + " to make a move.");
    }

    if (!currentPlayer.hasCard(card)) {
      throw new InvalidCardException("The selected card is not in the player's hand.");
    }

    Position destinationPos = new Position(currentPos.getRow() + cardMove.getRow(),
        currentPos.getCol() + cardMove.getCol());
    Spot destinationSpot = getSpot(destinationPos);
    if (destinationSpot == null) {
      throw new IllegalMovementException("The destination position is outside the board.");
    }

    // Check if is a win move
    checkWinningMove(destinationSpot, piece);

    // Move the piece to the destination spot
    Spot currentSpot = board[currentPos.getRow()][currentPos.getCol()];
    destinationSpot.occupySpot(piece);
    currentSpot.releaseSpot();

    // Swap cards
    currentPlayer.swapCard(card, tableCard);
    setTableCard(card);

    // Switch to the next player's turn
    switchTurn();
  }

  @Override
  public boolean checkVictory(Color color) {
    Player winner = getWinner();
    if (winner == null) {
      return false;
    }

    if (winner.getPieceColor() == color) {
      return true;
    }

    return false;
  }

  @Override
  public void printBoard() {
    final String ANSI_RED_BACKGROUND = "\u001B[41m";
    final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    final String ANSI_RESET = "\u001B[0m";

    // Imprimir as coordenadas superiores
    System.out.print("  ");
    for (int col = 0; col < BOARD_SIZE; col++) {
      System.out.print("   " + col + "   ");
    }
    System.out.println();

    // Imprimir as linhas horizontais e verticais
    for (int row = 0; row < BOARD_SIZE; row++) {
      System.out.print("  ");
      for (int col = 0; col < BOARD_SIZE; col++) {
        System.out.print("------+");
      }
      System.out.println();

      // Imprimir as coordenadas laterais e os quadrados vazios
      System.out.print(row + " ");
      for (int col = 0; col < BOARD_SIZE; col++) {
        String bg = "";
        if (row == RED_TEMPLE) {
          bg = ANSI_RED_BACKGROUND;
        } else if (row == BLUE_TEMPLE) {
          bg = ANSI_BLUE_BACKGROUND;
        }
        Position position = new Position(row, col);
        Piece piece = getPiece(position);
        if (piece == null) {
          System.out.print("|" + bg + "      " + ANSI_RESET);
        } else {
          String icon = "";
          if (piece.getColor() == Color.RED) {
            icon = (piece.isMaster() ? "🔴" : "💂");
          } else if (piece.getColor() == Color.BLUE) {
            icon = (piece.isMaster() ? "🔵" : "👮");
          }
          System.out.print("|" + bg + "  " + icon + "  " + ANSI_RESET);
        }
      }
      System.out.println("|");
    }

    // Imprimir a linha horizontal inferior
    System.out.print("  ");
    for (int col = 0; col < BOARD_SIZE; col++) {
      System.out.print("------+");
    }
    System.out.println();

    System.out.println("Carta da Mesa: " + getTableCard().getName());
  }
}

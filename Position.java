/**
 * Classe usada para definição de estrutura de posições e movimentos do jogo
 */
public class Position {
  private int row;
  private int col;

  /**
   * Construtor que define o valor da Linha e da Coluna da posição, baseado no
   * plano cartesiano
   * 
   * @param row Linha
   * @param col Coluna
   */
  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Método que devolve o valor do eixo X da posição
   * 
   * @return Um valor int representando o eixo X
   */
  public int getRow() {
    return row;
  }

  /**
   * Método que devolve o valor do eixo Y da posição
   * 
   * @return Um valor int representando o eixo Y
   */
  public int getCol() {
    return col;
  }

  /**
   * Compara se esta posição é igual a outra posição fornecida.
   *
   * @param obj O objeto a ser comparado
   * @return true se as posições forem iguais, false caso contrário
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Position other = (Position) obj;
    return row == other.row && col == other.col;
  }

  /**
   * Retorna uma representação em formato de string da posição.
   *
   * @return A posição no formato "(row, col)"
   */
  @Override
  public String toString() {
    return "(" + row + ", " + col + ")";
  }
}

/**
 * Exceção para quando se tenta mover uma peça que não está em jogo
 */
public class InvalidPieceException extends OnitamaGameException {
    /**
     * Construtor que recebe uma mensagem e repassa para a superclasse
     * @param message A mensagem descrevendo o motivo do problema
     */
    public InvalidPieceException(String message) {
        super(message);
    }
}

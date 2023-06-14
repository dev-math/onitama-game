/**
 * Exceção para quando se tenta jogar uma carta que não está na mão do jogador
 */
public class InvalidCardException extends OnitamaGameException {
    /**
     * Construtor que recebe uma mensagem e repassa para a superclasse
     * @param message A mensagem descrevendo o motivo do problema
     */
    public InvalidCardException(String message) {
        super(message);
    }
}

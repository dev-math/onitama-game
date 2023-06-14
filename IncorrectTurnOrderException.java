/**
 * Exceção para quando se tenta jogar fora do seu turno
 */
public class IncorrectTurnOrderException extends OnitamaGameException {
    /**
     * Construtor que recebe uma mensagem e repassa para a superclasse
     * @param message A mensagem descrevendo o motivo do problema
     */
    public IncorrectTurnOrderException(String message) {
        super(message);
    }
}

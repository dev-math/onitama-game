/**
 * Exceção para quando se tenta fazer um movimento inválido
 */
public class IllegalMovementException extends OnitamaGameException {
    /**
     * Construtor que recebe uma mensagem e repassa para a superclasse
     * @param message A mensagem descrevendo o motivo do problema
     */
    public IllegalMovementException(String message) {
        super(message);
    }
}

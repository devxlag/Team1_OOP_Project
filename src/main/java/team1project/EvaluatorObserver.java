package team1project;

/**
 * The EvaluatorObserver interface represents an observer in the observer pattern for the Evaluator.
 * Classes implementing this interface can receive updates from classes implementing the EvaluatorSubject interface.
 */
public interface EvaluatorObserver {
    
    /**
     * Updates the observer with the latest information from the Evaluator.
     *
     * @param evaluator The Evaluator providing the update.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Evaluator evaluator);
    
}

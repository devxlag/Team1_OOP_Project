package team1project;

/**
 * The EvaluatorSubject interface represents the subject in the observer pattern for the Evaluator.
 * Classes implementing this interface can be observed by classes implementing the EvaluatorObserver interface.
 */
public interface EvaluatorSubject {

    /**
     * Registers an observer to receive notifications.
     *
     * @param observer The observer to register.
     */
    public void registerObserver(EvaluatorObserver observer);

    /**
     * Removes an observer from the list of registered observers.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(EvaluatorObserver observer);

    /**
     * Notifies all registered observers about a change in the Evaluator.
     *
     * @param observer The observer to notify.
     */
    public void notifyObservers(EvaluatorObserver observer);
    
}

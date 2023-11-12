package team1project;

public interface EvaluatorSubject {

    public void registerObserver(EvaluatorObserver observer);

    public void removeObserver(EvaluatorObserver observer);

    public void notifyObservers(EvaluatorObserver observer);
    
}

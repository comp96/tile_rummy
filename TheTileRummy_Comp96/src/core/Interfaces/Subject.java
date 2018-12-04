package core.Interfaces;

/**
 * Used Derek Banas' video as reference, thus some of our Observer pattern code
 * comes here: https://www.youtube.com/watch?v=wiQdrH2YpT4
 */
public interface Subject {

    /**
     * Add new observer to list
     *
     * @param new_observer
     */
    public void register(Observer new_observer);

    /**
     * @param delete_observer from list
     */
    public void unregister(Observer delete_observer);

    /**
     * Notify all observers of changes in subject's hand size
     */
    public void notify_observers();

}

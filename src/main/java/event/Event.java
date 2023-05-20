package event;

import user.User;
import utilities.LinkedListCustom;
import exceptions.InvalidEventDayException;
import librarySystem.LibrarySystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Event {
    // Fields
    private String eventName;
    private LocalDate eventDate;
    private EventType eventType;
    private boolean familyEvent;
    private LinkedListCustom<User> eventList;

    // Constructor
    public Event(String eventName, LocalDate eventDate, EventType eventType, boolean familyEvent) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.familyEvent = familyEvent;
        eventList  = new LinkedListCustom<User>();
    }

    // Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
            LocalDate now = LocalDate.now();
            if (eventDate.isAfter(now)) {
                this.eventDate = eventDate;
            } throw new InvalidEventDayException("The event date is in the past");
    }

    public EventType getEventType() {
        return eventType;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean isFamilyEvent() {
        return familyEvent;
    }

    public void setFamilyEvent(boolean familyEvent) {
        this.familyEvent = familyEvent;
    }

    // Methods
    /**
     * The method adds a user to library event
     * @param user
     */
    public void addEventAttendee(User user) {
        eventList.add(eventList, user);
    }

    /**
     * This method displays event attendees
     */
    public void printEventAttendees() {
        Logger logger = LogManager.getLogger(LibrarySystem.class);
        logger.info("event.Event attendees are:");
        eventList.printCustomList(eventList);
    }

    /**
     * This method removes an attendee from the list
     * @param user
     */

    public void removeEventAttendee(User user) {
        eventList.remove(eventList, user);
    }
}

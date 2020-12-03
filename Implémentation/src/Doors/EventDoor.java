package Doors;

import Items.PassType;

public class EventDoor extends LockedDoor {
    public EventDoor(String tag, PassType p) {
        super(tag, p);
    }

    @Override
    public void unlock(){
        super.unlock();
    }
}

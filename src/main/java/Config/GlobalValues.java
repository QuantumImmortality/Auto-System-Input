package Config;

import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class GlobalValues {
    public static final int RECORDING_DELAY = 3000;              //millisecond delay in recording events

    public static boolean DEBUGGING = true;
    public static int ACTIVATION_KEY = GlobalKeyEvent.VK_F7;    //activates the record input feature

    public enum level {
        DEBUG, WARN, ERROR
    }

    public enum inputType {
        MOUSE, KEY
    }

    public enum mouseInput {
        LMB, RMB, MMB, MWHEEL, MOVEMENT
    }

}

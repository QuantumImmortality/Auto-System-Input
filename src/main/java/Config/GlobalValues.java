package Config;

import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class GlobalValues {

    public static boolean DEBUGGING = false;
    public static int ACTIVATION_KEY = GlobalKeyEvent.VK_F7;    //activates the record input feature

    public enum level {
        DEBUG, WARN, ERROR
    }

    public enum inputType {
        MOUSE, KEY
    }

    public enum mouseInput {
        LMB, RMB
    }

}

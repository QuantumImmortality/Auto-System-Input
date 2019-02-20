package Input;

import Config.GlobalValues;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.util.LinkedList;
import java.util.Map;

import static Config.GlobalValues.ACTIVATION_KEY;
import static Config.GlobalValues.DEBUGGING;
import static Logging.Logger.writeLogMessage;

public class GlobalKeyListener {
    private static boolean run = true;
    private static boolean recordInput = false;



    public void monitorKey() {
        // might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input
        LinkedList<RecordedInput> inputs = new LinkedList<>();

        if(DEBUGGING) {
            writeLogMessage("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:", GlobalValues.level.DEBUG);

            for (Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet())
                writeLogMessage("ID: " + keyboard.getKey() + " " + keyboard.getValue(), GlobalValues.level.DEBUG);
        }

        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            @Override public void keyPressed(GlobalKeyEvent event) {

                if(event.getVirtualKeyCode() == ACTIVATION_KEY) {
                    recordInput = !recordInput;
                    writeLogMessage("record input toggled " + recordInput, GlobalValues.level.DEBUG);
                }
            }
            @Override public void keyReleased(GlobalKeyEvent event) {
                if(recordInput) System.out.println(event); }
        });

        try {
            while(run) Thread.sleep(128);
        } catch(InterruptedException e) {
            writeLogMessage("InterruptedException while waiting for key press " + e, GlobalValues.level.ERROR);
        }
        finally { keyboardHook.shutdownHook(); }
    }


}

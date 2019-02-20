package Input;

import Logging.LogLevel;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.util.Map;

import static Logging.Logger.writeLogMessage;

public class GlobalKeyListner {
    private static boolean run = true;

    public void monitorKey() {
        // might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input

        writeLogMessage("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:", LogLevel.level.DEBUG);

        for(Map.Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet())
            writeLogMessage("ID: " + keyboard.getKey() + " " +  keyboard.getValue(), LogLevel.level.DEBUG);

        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            @Override public void keyPressed(GlobalKeyEvent event) {
                System.out.println(event);
                if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
                    run = false;
            }
            @Override public void keyReleased(GlobalKeyEvent event) {
                System.out.println(event); }
        });

        try {
            while(run) Thread.sleep(128);
        } catch(InterruptedException e) { /* nothing to do here */ }
        finally { keyboardHook.shutdownHook(); }
    }
}

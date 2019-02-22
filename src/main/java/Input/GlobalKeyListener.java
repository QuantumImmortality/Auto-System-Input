package Input;

import Config.GlobalValues;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

import static Config.GlobalValues.*;
import static Logging.Logger.writeLogMessage;

public class GlobalKeyListener {
    private static boolean run = true;
    private static boolean recordInput = false;
    private static LinkedList<RecordedInput> recordedEvents;

    public void monitorEvents() {
        recordedEvents = new LinkedList<>();

        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
        initKeyboardHook(keyboardHook);

        GlobalMouseHook mouseHook = new GlobalMouseHook();
        initMouseHook(mouseHook);

       try {
            while(run) Thread.sleep(RECORDING_DELAY);
        } catch(InterruptedException e) {
            writeLogMessage("InterruptedException while waiting for key press " + e, GlobalValues.level.ERROR);
        }
        finally {
           keyboardHook.shutdownHook();
           mouseHook.shutdownHook();
       }
    }

    private void initKeyboardHook(GlobalKeyboardHook keyboardHook){

        if(DEBUGGING) {
            writeLogMessage("Global keyboard hook successfully started. Connected keyboards:", GlobalValues.level.DEBUG);
            for (Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet())
                writeLogMessage("ID: " + keyboard.getKey() + " " + keyboard.getValue(), GlobalValues.level.DEBUG);
        }

        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            @Override public void keyPressed(GlobalKeyEvent event) {

                if(event.getVirtualKeyCode() == ACTIVATION_KEY) {
                    recordInput = !recordInput;

                    if(DEBUGGING)
                        writeLogMessage("record input toggled " + recordInput, GlobalValues.level.DEBUG);

                    recordedEvents.clear();
                    /*while(recordInput){
                        recordEvents(event);

                    }*/
                }
            }
            @Override public void keyReleased(GlobalKeyEvent event) {
                if(DEBUGGING)
                    if(recordInput) writeLogMessage("key pressed: " + event, GlobalValues.level.DEBUG); }
        });
    }

    private void initMouseHook(GlobalMouseHook mouseHook){

        if(DEBUGGING) {
            writeLogMessage("Global mouse hook successfully started. Connected mice:", GlobalValues.level.DEBUG);
            for(Map.Entry<Long,String> mouse:GlobalMouseHook.listMice().entrySet())
                writeLogMessage("ID: " + mouse.getKey() + " " + mouse.getValue(), GlobalValues.level.DEBUG);
        }

        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override public void mousePressed(GlobalMouseEvent event)  {
                if(event.getButton() == GlobalMouseEvent.BUTTON_LEFT)
                    recordEvents(event, mouseInput.LMB, false);
                else
                    recordEvents(event, mouseInput.RMB, false);

                if((event.getButtons()& GlobalMouseEvent.BUTTON_LEFT) != GlobalMouseEvent.BUTTON_NO
                        && (event.getButtons()&GlobalMouseEvent.BUTTON_RIGHT) != GlobalMouseEvent.BUTTON_NO)
                    System.out.println("Both mouse buttons are currently pressed!");
            }
            @Override public void mouseReleased(GlobalMouseEvent event)  {
                if(event.getButton() == GlobalMouseEvent.BUTTON_LEFT)
                    recordEvents(event, mouseInput.LMB, true);
                else
                    recordEvents(event, mouseInput.RMB, true);
            }
            @Override public void mouseMoved(GlobalMouseEvent event) {
                recordEvents(event, mouseInput.MOVEMENT); }
            @Override public void mouseWheel(GlobalMouseEvent event) {
                recordEvents(event, mouseInput.MWHEEL); }
        });

    }

    /**
     * For key presses
     * @param event
     */
    private void recordEvents(GlobalKeyEvent event){
        if(DEBUGGING)
            writeLogMessage("GlobalKeyEvent recorded ", GlobalValues.level.DEBUG);

        recordedEvents.add(
                new RecordedInput(
                        GlobalValues.mouseInput.MOVEMENT,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y,
                        RECORDING_DELAY
                )
        );

    }

    /**
     * For mouse movement and wheel
     * @param event
     * @param type
     */
    private void recordEvents(GlobalMouseEvent event, mouseInput type){
        if(DEBUGGING)
            writeLogMessage("GlobalMouseEvent recorded " + type, GlobalValues.level.DEBUG);

        recordedEvents.add(
                new RecordedInput(
                        GlobalValues.mouseInput.MOVEMENT,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y,
                        RECORDING_DELAY
                )
        );

    }

    /**
     * For mouse button events
     * @param event
     * @param type
     * @param buttonRelease
     */
    private void recordEvents(GlobalMouseEvent event, mouseInput type, boolean buttonRelease){
        if(DEBUGGING)
            writeLogMessage("GlobalMouseEvent recorded, button released =  " + buttonRelease + " " + type, GlobalValues.level.DEBUG);

        recordedEvents.add(
                new RecordedInput(
                        GlobalValues.mouseInput.MOVEMENT,
                        MouseInfo.getPointerInfo().getLocation().x,
                        MouseInfo.getPointerInfo().getLocation().y,
                        RECORDING_DELAY
                )
        );

    }
}

package Input;

import Config.GlobalValues;

public class RecordedInput {

    private GlobalValues.inputType inputType;
    private char keyStroke;
    private GlobalValues.mouseInput mouseInput;
    private int cursorX;
    private int cursorY;
    private int inputDelay;

    /**
     * For Keystroke input
     * @param inputType type of input
     * @param keyStroke the keystroke input
     * @param inputDelay delay between this and previous input
     */
    public RecordedInput(GlobalValues.inputType inputType, char keyStroke, int inputDelay){
        this.inputType = inputType;
        this.keyStroke = keyStroke;
        this.inputDelay = inputDelay;
    }

    /**
     * For mouse input
     * @param mouseInput the mouse button pressed
     * @param cursorX x position of cursor
     * @param cursorY y position of cursor
     * @param inputDelay delay between this and previous input
     */
    public RecordedInput(GlobalValues.mouseInput mouseInput, int cursorX, int cursorY, int inputDelay){
        this.mouseInput = mouseInput;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.inputDelay = inputDelay;
    }
}

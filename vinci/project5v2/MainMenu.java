/*
 * The MIT License
 *
 * Copyright 2014 Michael D. Vinci.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package vinci.project5v2;

import vinci.project5v2.Battle.Battle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainMenu extends BasicGameState {
    Graphics g = new Graphics();  
    public Image menuImg, titleImg, newGameImg, continueImg, endGameImg, chooseImg;
    public int arrowy = 425;
    private Audio menuMusic;
    
    public MainMenu(int state) {
    }

    @Override
    public int getID() {
        return 0;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        try {
            menuMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/menu.wav"));
        } catch (IOException ex) {
            Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        menuMusic.playAsMusic(.4f, 1.0f, true);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuImg = new Image("/res/menu.png");
        titleImg = new Image("/res/title.png");
        newGameImg = new Image("/res/newgame.png");
        continueImg = new Image("/res/continue.png");
        endGameImg = new Image("/res/endgame.png");
        chooseImg = new Image("/res//choose.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        g.drawImage(menuImg, 0, 0);
        g.drawImage(titleImg, 50, 50);
        g.drawImage(newGameImg, 15, 425); //225,425 for arrow
        g.drawImage(continueImg, 15, 475); //225,475 for arrow
        g.drawImage(endGameImg, 15, 525); //225,525 for arrow
        g.drawImage(chooseImg, 225, arrowy); //arrow start location
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        Input input = gc.getInput();
        
        keyboardMMenuInput(gc, input, sbg);
        mouseMMenuInput(xpos, ypos, input, sbg);
    }
    
    public void keyboardMMenuInput(GameContainer gc, Input input, StateBasedGame sbg) {
        if(input.isKeyDown(Input.KEY_UP)){
            if(arrowy == 525) {
                arrowy = 475;
            }
            else if(arrowy == 475) {
                arrowy = 425;
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            if(arrowy == 425) {
                arrowy = 475;
            }
            else if(arrowy == 475) {
                arrowy = 525;
            }
        }
        if (input.isKeyDown(Input.KEY_ENTER)) {
            if (arrowy == 425) {
                sbg.enterState(2);
            }
            if (arrowy == 525) {
                System.exit(0);
            }
        }
    }
    
    public void mouseMMenuInput(int xpos, int ypos, Input input, StateBasedGame sbg) {
        System.out.println(xpos + "," + ypos);
        
        if ((xpos >= 25) && (xpos <= 270)) {
            if((ypos >= 165) && (ypos <= 200)) {
                arrowy = 425;
                if(input.isMouseButtonDown(0)) {
                sbg.enterState(2);
                }
            }
            else if((ypos >= 115) && (ypos <= 150)) {
                arrowy = 475;
            }
            else if((ypos >= 65) && (ypos <= 100)) {
                arrowy = 525;
                if(input.isMouseButtonDown(0)) {
                System.exit(0);
                }
            }
        }
    }
}

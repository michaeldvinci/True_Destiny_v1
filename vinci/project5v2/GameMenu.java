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

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author michaeldvinci
 */
public class GameMenu extends BasicGameState {
    private Image gameMenu, gMenuArrow;
    private final Graphics g = new Graphics();
    private final int gMenuArrowX = 590;
    private int gMenuArrowY = 97, xpos, ypos;
        
    GameMenu(int state) {
    }
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gameMenu = new Image("/res/gameMenu1.png");
        gMenuArrow = new Image("/res/gMenuArrow.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        gameMenu.draw(50,40);
        gMenuArrow.draw(gMenuArrowX, gMenuArrowY);
    }
    
    

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        xpos = Mouse.getX();
        ypos = Mouse.getY();
        
        System.out.println(xpos + "," + ypos);
        
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            sbg.enterState(2);
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
        keyboardGMenuInput(input);
        mouseGMenuInput(input);
        pressEnter(input, sbg);
    }
    
    public void keyboardGMenuInput(Input input) {
        if(input.isKeyPressed(Input.KEY_DOWN)) {
            if(gMenuArrowY == 97) {
                gMenuArrowY = 151;
            }
            else if(gMenuArrowY == 151) {
                gMenuArrowY = 204;
            }
            else if(gMenuArrowY == 204) {
                gMenuArrowY = 256;
            }
            else if(gMenuArrowY == 256) {
                gMenuArrowY = 336;
            }
            else if(gMenuArrowY == 336) {
                gMenuArrowY = 388;
            }
        }
        if(input.isKeyPressed(Input.KEY_UP)) {
            if(gMenuArrowY == 151) {
                gMenuArrowY = 97;
            }
            if(gMenuArrowY == 204) {
                gMenuArrowY = 151;
            }
            if(gMenuArrowY == 256) {
                gMenuArrowY = 204;
            }
            if(gMenuArrowY == 336) {
                gMenuArrowY = 256;
            }
            if(gMenuArrowY == 388) {
                gMenuArrowY = 336;
            }
        }
    }
    
    public void mouseGMenuInput(Input input) {
        if ((xpos >= 615) && (xpos <= 723)) {
            if ((ypos <= 544) && (ypos >= 519)) {
                gMenuArrowY = 97;
            }
            if ((ypos <= 492) && (ypos >= 467)) {
                gMenuArrowY = 151;
            }
            if ((ypos <= 437) && (ypos >= 412)) {
                gMenuArrowY = 204;
            }
            if ((ypos <= 386) && (ypos >= 358)) {
                gMenuArrowY = 256;
            }
            if ((ypos <= 304) && (ypos >= 283)) {
                gMenuArrowY = 336;
            }
            if ((ypos <= 252) && (ypos >= 232)) {
                gMenuArrowY = 388;
            }
        }
    }
    
    //  cont 590, 97
    // items 590, 151
    // magic 590, 204
    //   map 590, 256
    //  save 590, 336
    //  exit 590, 368
    public void pressEnter(Input input, StateBasedGame sbg) {
        if(input.isKeyPressed(Input.KEY_RETURN)) {
            if(gMenuArrowY == 97) {
                
            }
            if(gMenuArrowY == 151) {
                
            }
            if(gMenuArrowY == 204) {
                
            }
            if(gMenuArrowY == 256) {
                sbg.enterState(5);
            }
            if(gMenuArrowY == 336) {
                
            }
            if(gMenuArrowY == 388) {
                sbg.enterState(Game.stateStack.pop());
            }
        }
    }    
}

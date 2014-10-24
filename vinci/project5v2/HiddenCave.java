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

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author michaeldvinci
 */
class HiddenCave extends BasicGameState {

    public Image caveMap, miroIMG;
    public float mapPosX = 0, mapPosY = 0;
    public float miroPosX = 0, miroPosY = 0;
    public int shiftX = 380, shiftY = 250;
    public Animation miro, movingUp, movingDown, movingLeft, movingRight, standStill;
    private final int[] duration = {150, 150, 150};
    
    public HiddenCave(int hiddenCave) {
    }

    @Override
    public int getID() {
        return 6;
    }

    public void enter() {
        Game.stateStack.push(getID());
        
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        caveMap = new Image("/res/cave1.png");
        Image[] walkUp = {new Image("/res/miroUp1.png"), new Image("/res/miroUp.png"), new Image("/res/miroUp2.png")};
        Image[] walkDown = {new Image("/res/miroDown1.png"), new Image("/res/miroStandStill1.png"), new Image("/res/miroDown2.png")};
        Image[] walkLeft = {new Image("/res/miroLeft1.png"), new Image("/res/miroLeft2.png"), new Image("/res/miroLeft3.png")};
        Image[] walkRight = {new Image("/res/miroRight1.png"), new Image("/res/miroRight.png"), new Image("/res/miroRight2.png")};
        Image[] standingStill = {new Image("/res/miroStandStill1.png"), new Image("/res/miroStandStill1.png"), new Image("/res/miroStandStill1.png")};
        
        movingUp = new Animation(walkUp, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        standStill = new Animation(standingStill, duration, true);
        
        miro = standStill;
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        mapPosX = miroPosX - 0;
        mapPosY = miroPosY - 400;
        caveMap.draw(mapPosX, mapPosY);
        miro.draw(shiftX, shiftY);
        //940,125
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_UP)) {
            miro = movingUp;
            miroPosY += delta * .1f;
            toBattle(sbg, 6);
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            miro = movingDown;
            miroPosY -= delta * .1f;
            toBattle(sbg, 6);
        }
        if(input.isKeyDown(Input.KEY_LEFT)) {
            miro = movingLeft;
            miroPosX += delta * .1f;
            toBattle(sbg, 6);
        }
        if(input.isKeyDown(Input.KEY_RIGHT)) {
            miro = movingRight;
            miroPosX -= delta * .1f;
            toBattle(sbg, 6);
        }
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            sbg.enterState(4);
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
    }
    
    public void toBattle(StateBasedGame sbg, int state) {
        double test = Math.random();
        if ((test > .003) && (test < .004)) {
            sbg.enterState(3);
        }
    }
}

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
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author michaeldvinci
 */
public class Map extends BasicGameState {
    public Graphics g = new Graphics();
    public Animation miro, movingUp, movingDown, movingLeft, movingRight, standStill;
    public Image wmap, gameMenu;
    public boolean quit = false;
    private boolean menu = false;
    private final int[] duration = {150, 150, 150};
    public float miroPosX = 0;
    public float miroPosY = 0;
    public float shiftX = miroPosX + 380;
    public float shiftY = miroPosY + 250;
//    public float shiftX = 0;
//    public float shiftY = 0;
    public float mapPosX, mapPosY;
    private Audio worldMapMusic;
    public static float posXX, relX, relY;
    
    public Map(int state) {
    }

    @Override
    public int getID() {
        return 2;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Game.stateStack.push(getID());
        try {
            worldMapMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/worldMap.wav"));
        } 
        catch (IOException ex) {
            Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!worldMapMusic.isPlaying()) {
            worldMapMusic.playAsMusic(1.0f, 1.0f, true);
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        wmap = new Image("/res/worldmap.png");
        gameMenu = new Image("/res/gameMenu1.png");
            
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
        mapPosX = miroPosX - 450;
        mapPosY = miroPosY - 450;
        wmap.draw(mapPosX, mapPosY);
        miro.draw(shiftX, shiftY);
        
        relX = ((-1 * mapPosX) + shiftX);        
        relY = ((-1 * mapPosY) + shiftY);        
        
        System.out.println(relX + "," + relY);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_UP)) {
            miro = movingUp;
            miroPosY += delta * .1f;
            toBattle(sbg, getID());
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            miro = movingDown;
            miroPosY -= delta * .1f;
            toBattle(sbg, getID());
        }
        if(input.isKeyDown(Input.KEY_LEFT)) {
            miro = movingLeft;
            miroPosX += delta * .1f;
            toBattle(sbg, getID());
        }
        if(input.isKeyDown(Input.KEY_RIGHT)) {
            miro = movingRight;
            miroPosX -= delta * .1f;
            toBattle(sbg, getID());
        }
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            sbg.enterState(4);
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
        
        if(((relX >= 2435) && (relX <= 2453)) && ((relY >= 1104) && (relY <= 1124))) {
            sbg.enterState(6);
    }
        
//        System.out.println(miroPosX + "," + miroPosY);
//        System.out.println(mapPosX + "," + mapPosY);
        
    }
    
    public static float returnMe() {
        return posXX;
        
    }
    
    public void toBattle(StateBasedGame sbg, int state) {
        double test = Math.random();
        if ((test > .003) && (test < .004)) {
            sbg.enterState(3);
        }
    }
}

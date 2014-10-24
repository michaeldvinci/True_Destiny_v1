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

package vinci.project5v2.Battle;

import java.io.IOException;
import java.lang.reflect.Array;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.util.ResourceLoader;
import vinci.project5v2.Game;

/**
 *
 * @author michaeldvinci
 */
public class Battle extends BasicGameState {
    
    public Animation bossMoving, boss, miroMoving, miroBat, jacquesMoving, jacquesBat, sephiroth, sephirothMoving, queenBee, queenBeeMoving;
    public Image battleMap, bossImg, sephirothIMG, queenBeeImg, battleArrow;
    private ImageIcon mp0, mp10, mp20, mp30, mp40, mp50, mp60, mp70, mp80, mp90, mp100;
    private ImageIcon hp0, hp10, hp20, hp30, hp40, hp50, hp60, hp70, hp80, hp90, hp100;
    private final int[] duration = {300, 300, 300};
    private final int[] duration2 = {300, 600, 600};
    public Graphics g = new Graphics();
    private Audio battleSound;
    private int enemyID, bArrowX = 440, bArrowY = 523;
    public ImageIcon[] hp = new ImageIcon[11];
    public ImageIcon[] mp = new ImageIcon[11];

    public Battle(int state) {
    }

    @Override
    public int getID() {
        return 3;
    }
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enemyID = (int)(Math.random() * ((3) + 1));
                
        if (enemyID == 0) {
            bossImg = new Image("/res/bahamut.png");
            Image[] bossMove = {new Image("/res/bahamut.png"), new Image("/res/bahamut2.png"), new Image("/res/bahamut3.png")};
            bossMoving = new Animation(bossMove, duration, true);
            boss = bossMoving;
        }
        if (enemyID == 1) {
            queenBeeImg = new Image("/res/queenBee1.png");
            Image[] queenBeeMove = {new Image("/res/queenBee1.png"), new Image("/res/queenBee2.png"), new Image("/res/queenBee3.png")}; 
            queenBeeMoving = new Animation(queenBeeMove, duration, true);
            queenBee = queenBeeMoving;
        }
        if ((enemyID == 2) || (enemyID == 3)) {
            sephirothIMG = new Image("/res/sephiroth.png");
            Image[] sephirothMove = {new Image("/res/sephiroth.png"), new Image("/res/sephiroth2.png"), new Image("/res/sephiroth3.png")};
            sephirothMoving = new Animation(sephirothMove, duration2, true);
            sephiroth = sephirothMoving;
        }
        
        battleMap = new Image("/res/grassBattle.png");
        battleArrow = new Image("/res/battleArrow.png");
        Image[] jacquesMove = {new Image("/res/jacques.png"), new Image("/res/jacques2.png"), new Image("/res/jacques3.png")};
        Image[] miroMove = {new Image("/res/battle1.png"), new Image("/res/battle2.png"), new Image("/res/battle1.png")};
        miroMoving = new Animation(miroMove, duration, true);
        jacquesMoving = new Animation(jacquesMove, duration, true);
        miroBat = miroMoving;
        jacquesBat = jacquesMoving;
        
//        for(int i = 0; i < 100; i = i + 10){
//            hp[i] = new ImageIcon("/res/hp" + i + ".png");
//            mp[i] = new ImageIcon("/res/mp" + i + ".png");
//        }
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        try {
            battleSound = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/battle.wav"));
        } catch (IOException ex) {
            Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        battleSound.playAsMusic(1.0f, 1.0f, true);
    }
    
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        battleSound.stop();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        battleMap.draw(0, 0);
        miroBat.draw(100, 300);
        jacquesBat.draw(95, 225);
        battleArrow.draw(bArrowX, bArrowY);
        
        if (enemyID == 0) {
            boss.draw(500, 200);
        }
        if (enemyID == 1) {
            queenBee.draw(500, 200);
        }
        if (enemyID == 2) {
            sephiroth.draw(500, 200);
        }
        if (enemyID == 3) {
            
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        System.out.println(xpos + "," + ypos);
        
        miroBat = miroMoving;
        jacquesBat = jacquesMoving;
        
        if (enemyID == 0) {
            boss = bossMoving;
        }
        if (enemyID == 1) {
            queenBee = queenBeeMoving;
        }
        if (enemyID == 2) {
            sephiroth = sephirothMoving;
        }
        if (enemyID == 3) {
            
        }
        
        keyboardBattleInput(input);
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
        
        if(input.isKeyPressed(Input.KEY_RETURN)) {
            if((bArrowX == 440) && (bArrowY == 523)) {
                
            }
            if((bArrowX == 440) && (bArrowY == 580)) {
                
            }
            if((bArrowX == 635) && (bArrowY == 523)) {
                
            }
            if((bArrowX == 635) && (bArrowY == 580)) {
                int runAway = (int)(Math.random() * ((3) + 1));
                if (runAway == 3) {
                    sbg.enterState(Game.stateStack.pop());
                }
            }
        }
    }
    
    public void keyboardBattleInput(Input input) {
        //  attack - 440, 523   635, 523 - items
        //   magic - 440, 580   635, 580 - escape
        
        if(input.isKeyDown(Input.KEY_UP)) {
            if((bArrowX == 440) && (bArrowY == 580)) {
                bArrowY = 523;
            }
            if((bArrowX == 635) && (bArrowY == 580)) {
                bArrowY = 523;
            } 
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            if((bArrowX == 440) && (bArrowY == 523)) {
                bArrowY = 580;
            }
            else if((bArrowX == 635) && (bArrowY == 523)) {
                bArrowY = 580;
            }
        }
        if(input.isKeyDown(Input.KEY_LEFT)) {
            if((bArrowX == 635) && (bArrowY == 523)) {
                bArrowX = 440;
            }
            else if((bArrowX == 635) && (bArrowY == 580)) {
                bArrowX = 440;
            } 
        }
        if(input.isKeyDown(Input.KEY_RIGHT)) {
            if((bArrowX == 440) && (bArrowY == 523)) {
                bArrowX = 635;
            }
            else if((bArrowX == 440) && (bArrowY == 580)) {
                bArrowX = 635;
            } 
        }
    }
    
    public void mouseBattleInput(Input input) {
        
    }
}

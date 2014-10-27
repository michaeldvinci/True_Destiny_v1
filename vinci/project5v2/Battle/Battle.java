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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public Animation bossMoving, boss, miroMoving, miroBat, jacquesMoving, jacquesBat, sephiroth, sephirothMoving, queenBee, queenBeeMoving, miroAttacking;
    public Image battleMap, bossImg, sephirothIMG, queenBeeImg, battleArrow, miroAtk1, miroAtk2, missed;
    private Image mp0, mp10, mp20, mp30, mp40, mp50, mp60, mp70, mp80, mp90, mp100;
    private Image hp0, hp10, hp20, hp30, hp40, hp50, hp60, hp70, hp80, hp90, hp100;
    private final int[] duration = {300, 300, 300};
    private final int[] duration2 = {300, 600, 600};
    private final int[] durationBattle = {200, 200, 200, 200, 200, 200, 200, 200, 200, 200};
    public Graphics g = new Graphics();
    private Audio battleSound;
    private int enemyID, bArrowX = 440, bArrowY = 523;
    public Image[] hp = new Image[11];
    public Image[] mp = new Image[11];
    Queue<Integer> battleQueue;
    public int turn, enemyHP, enemypHP, damage, enemyBat, mHP = 50, jHP = 50, miroX = 100;
    
    public Battle(int state) {
    }

    @Override
    public int getID() {
        return 3;
    }
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
               
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enemyHP = 100;
        
        try {
            battleSound = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/battle.wav"));
        } catch (IOException ex) {
            Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        battleSound.playAsMusic(1.0f, 1.0f, true);
        
        battleQueue = new LinkedList<>();
        
        enemyID = (int)(Math.random() * ((3) + 1));
        
        initHP();
        initMP();
        
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
        missed = new Image("/res/Missed.png");
        Image[] jacquesMove = {new Image("/res/jacques.png"), new Image("/res/jacques2.png"), new Image("/res/jacques3.png")};
        Image[] miroMove = {new Image("/res/battle1.png"), new Image("/res/battle2.png"), new Image("/res/battle1.png")};
        Image[] miroAtk = {new Image("/res/mAttack1.png"), new Image("/res/mAttack2.png"), new Image("/res/mAttack3.png"), new Image("/res/mAttack4.png"), new Image("/res/mAttack5.png"), new Image("/res/mAttack6.png"), new Image("/res/mAttack4.png"), new Image("/res/mAttack3.png"), new Image("/res/mAttack2.png"), new Image("/res/mAttack1.png")};
        miroMoving = new Animation(miroMove, duration, true);
        jacquesMoving = new Animation(jacquesMove, duration, true);
        miroAttacking = new Animation(miroAtk, durationBattle, true);
        miroBat = miroAttacking;
        jacquesBat = jacquesMoving;
    }
    
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        battleSound.stop();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        battleMap.draw(0, 0);
        jacquesBat.draw(95, 300);
        miroBat.draw(miroX, 225);
        battleArrow.draw(bArrowX, bArrowY);
        
        miroHP(mHP);
        jacquesHP(jHP);
        
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
        
        initQueue();
        turn = battleQueue.element();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
        
        if (turn == 3) {
            enemyAttack();
            
            System.out.println("   miro hp: " + mHP);
            System.out.println("jacques hp: " + jHP + "\n");
            
            battleQueue.remove();
            battleQueue.offer(turn);
        }
        if(input.isKeyPressed(Input.KEY_RETURN)) {
            if((bArrowX == 440) && (bArrowY == 523)) {
                if (turn == 1) {
                    miroBat = miroMoving;
                    attackPhase(sbg);
                    System.out.println("  enemy hp: " + enemyHP + "\n");
                }
                if (turn == 2) {
                    miroBat = miroMoving;
                    attackPhase(sbg);
                    System.out.println("  enemy hp: " + enemyHP + "\n");
                }
            }
            if((bArrowX == 440) && (bArrowY == 580)) {
                
            }
            if((bArrowX == 635) && (bArrowY == 523)) {
                
            }
            if((bArrowX == 635) && (bArrowY == 580)) {
                 if ((turn == 1) || (turn == 2)){
                    int runAway = (int)(Math.random() * ((3) + 1));
                    if (runAway == 3) {
                        sbg.enterState(Game.stateStack.pop());
                    }
                    battleQueue.remove();
                    battleQueue.offer(turn);
                }
            }
        }
        
        miroHP(mHP);
        jacquesHP(jHP);
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
    
    public void initHP() throws SlickException {
        hp[0] = hp0 = new Image("/res/hp0.png");
        hp[1] = hp10 = new Image("/res/hp10.png");
        hp[2] = hp20 = new Image("/res/hp20.png");
        hp[3] = hp30 = new Image("/res/hp30.png");
        hp[4] = hp40 = new Image("/res/hp40.png");
        hp[5] = hp50 = new Image("/res/hp50.png");
        hp[6] = hp60 = new Image("/res/hp60.png");
        hp[7] = hp70 = new Image("/res/hp70.png");
        hp[8] = hp80 = new Image("/res/hp80.png");
        hp[9] = hp90 = new Image("/res/hp90.png");
        hp[10] = hp100 = new Image("/res/hp100.png");
    }
    
    public void initMP() throws SlickException {
        mp[0] = mp0 = new Image("/res/mp0.png");
        mp[1] = mp10 =  new Image("/res/mp10.png");
        mp[2] = mp20 =  new Image("/res/mp20.png");
        mp[3] = mp30 =  new Image("/res/mp30.png");
        mp[4] = mp40 =  new Image("/res/mp40.png");
        mp[5] = mp50 =  new Image("/res/mp50.png");
        mp[6] = mp60 =  new Image("/res/mp60.png");
        mp[7] = mp70 =  new Image("/res/mp70.png");
        mp[8] = mp80 =  new Image("/res/mp80.png");
        mp[9] = mp90 =  new Image("/res/mp90.png");
        mp[10] = mp100 =  new Image("/res/mp100.png");
    }
    
    public void miroHP(int mHP) {
//        hp100.draw(140, 515);
        int mh = (mHP / 5);
        hp[mh].draw(140, 515);
        mp100.draw(140, 530);
    }
    
    public void jacquesHP(int jHP) {
//        hp80.draw(140, 552);
        int jh = (jHP / 5);
        
        hp[jh].draw(140, 552);
        mp100.draw(140, 567);
    }
    
    public void initQueue() {
        battleQueue.offer(1);
        battleQueue.offer(2);
        battleQueue.offer(3);
    }
    
    public void attackPhase(StateBasedGame sbg) {
        damage = 10;
        enemyHP = attack(enemyHP, damage, enemyID, sbg);
        battleQueue.remove();
        battleQueue.offer(turn);
    }
        
    public int attack(int eHP, int damage, int enemyID, StateBasedGame sbg) {
        double yn = Math.random();
        if (yn < .69) {
            if (enemyID == 0) {
                eHP -= 10;
            }
            if (enemyID == 1) {
                eHP -= 10;
            }
            if (enemyID == 2) {
                eHP -= 10;
            }
            if (eHP <= 0) {
                sbg.enterState(Game.stateStack.pop());
            }
        }
        else {
            System.out.print("You missed!");
            g.drawImage(missed, 500, 140);
        }
        return eHP;
    }
    
    public void enemyAttack() {
        double yn = Math.random();
        double enBat = Math.random();
        if (yn < .76) {   
            if(enBat > .5) {
                mHP -= 10;
                if (mHP < 0) {
                    mHP = 0;
                }
            }
            else
                jHP -= 10;
                if (jHP < 0) {
                        jHP = 0;
                    }
        }
        else {
            System.out.println("MISSED!");
        }
    }
}

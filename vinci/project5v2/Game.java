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

import Characters.Miro;
import vinci.project5v2.Battle.*;
import java.util.Stack;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

    public static Stack<Integer> stateStack = new Stack<>();
    public static final String gameName = "True Destiny";
    public static final int menu = 0;
    public static final int openingMovie = 1;
    public static final int map = 2;
    public static final int battle = 3;
    public static final int gameMenu = 4;
    public static final int miniMap = 5;
    public static final int hiddenCave = 6;
    public static final int battleAction  = 7;
    public static final int battleMagic = 8;
    public static final int battleItem = 9;
    public static Miro m;
    

    public Game(String gameName) {
        super(gameName);
        this.addState(new MainMenu(menu));
//        this.addState(new OpeningMovie(openingMovie));
        this.addState(new Map(map));
        this.addState(new Battle(battle));
        this.addState(new GameMenu(gameMenu));
        this.addState(new MiniMap(miniMap));
        this.addState(new HiddenCave(hiddenCave));
        this.addState(new BattleAction(battleAction));
        this.addState(new BattleItem(battleItem));
        this.addState(new BattleMagic(battleMagic));
        m = new Miro(50, 50, 6, 5, 7, 4);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
//        this.getState(openingMovie).init(gc, this);
        this.getState(map).init(gc, this);
        this.getState(battle).init(gc, this);
        this.getState(gameMenu).init(gc, this);
        this.enterState(menu);
//        this.enterState(battle);
    }

    public static void main(String[] args)throws Exception{
        AppGameContainer appGC;
        try {
            appGC = new AppGameContainer(new Game(gameName));
//            appGC.setFullscreen(true);
            appGC.setDisplayMode(800, 640, false);
            appGC.setShowFPS(false);
            appGC.start();
        }
        catch(SlickException e) {
        }
    }
}

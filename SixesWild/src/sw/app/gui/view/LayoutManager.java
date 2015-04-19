package sw.app.gui.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import sw.common.system.manager.LevelManager;


public class LayoutManager {

	SixesWildJFrame f;
	MainMenuView mmv;
	SplashScreenView ssv;
	GameplayView gv;
	CreditScreenView csv;
	ScoreBoardView sbv;
	PlayGameScreenView pgs;
	PostGameView pgv;
	
	JPanel current = null;
	
	public LayoutManager(SixesWildJFrame f){
		this.f = f;
		mmv = new MainMenuView(this);
		gv = new GameplayView(this, f.getLevelManager());
		csv = new CreditScreenView(this);
		sbv = new ScoreBoardView(this);
		pgs = new PlayGameScreenView(this, f.levelManager);
		pgv = new PostGameView(this, f.levelManager);
	}
	
	public LayoutManager(SixesWildJFrame_BoardPanel f){
		///this.f = f;
		mmv = new MainMenuView(this);
		//gv = new GameplayView(this, f.getLevelManager());
		csv = new CreditScreenView(this);
		sbv = new ScoreBoardView(this);
		pgs = new PlayGameScreenView(this, new LevelManager());
	}
	
	public void switchToMainMenu(){
		System.out.println("Going to Main Menu");
		current = mmv;
		f.switchToScreen(mmv);
	}
	
	public void switchToGameplayView(){
		System.out.println("Going to gameplay view");
		current = gv;
		f.switchToScreen(gv);
	}
	
	public void switchToScoreBoardView(){
		System.out.println("Going to score board view");
		current = sbv;
		f.switchToScreen(sbv);
	}
	
	public void switchToCreditScreenView(){
		System.out.println("Going to credit view");
		//TODO Need to make credit view object
		f.switchToScreen(csv);
	}
	
	public void switchToPlayView(){
		System.out.println("Going to play view");
		current = pgs;
		f.switchToScreen(pgs);
		//TODO Need to make play view object
	}
	
	public void switchToPostGameView() {
		System.out.println("Going to postgame view");
		current = pgv;
		f.switchToScreen(pgv);
	}
	
	public JPanel getCurrentView() {
		return current;
	}
	
}

package sw.builder.gui.layout;

import javax.swing.JPanel;


import sw.builder.gui.layout.SplashScreenBuilderView;
import sw.builder.gui.layout.LevelBuilderJFrame;
import sw.builder.gui.layout.LevelBuilderView;



public class BuilderLayoutManager {
	LevelBuilderJFrame lbjf;
	SplashScreenBuilderView ssbv;
	LevelBuilderView lbv;
JPanel current = null;
	
	public BuilderLayoutManager(LevelBuilderJFrame lbjf){
		this.lbjf = lbjf;
		lbv = new LevelBuilderView(this);
	}
	
	public void switchToLevelBuilder(){
		System.out.println("Going to Level Builder");
		current = lbv;
		lbjf.switchToScreen(lbv);
	}
		
}

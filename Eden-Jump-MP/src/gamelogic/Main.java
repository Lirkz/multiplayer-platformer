package gamelogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameengine.GameBase;
import gameengine.input.KeyboardInputManager;
import gameengine.loaders.LeveldataLoader;
import gamelogic.level.Level;
import gamelogic.level.Leveldata;
import gamelogic.level.PlayerDieListener;
import gamelogic.level.PlayerWinListener;

public class Main extends GameBase implements PlayerDieListener, PlayerWinListener, ScreenTransitionListener{

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 860;
	public static final boolean DEBUGGING = false;

	private ScreenTransition screenTransition = new ScreenTransition();

	public ConnectionHandler connection;
	public Color playerColor;
	
	private Leveldata[] levels;
	public Level currentLevel;
	private int currentLevelIndex;
	private boolean active;
	
	private int numberOfTries;
	private long levelStartTime;
	private long levelFinishTime;
	
	private LevelCompleteBar levelCompleteBar;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start("Eden Jump", SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	@Override
	public void init() {
		GameResources.load();
		
		int randColor = (int)(Math.random()*4);
		if (randColor == 0) {
			playerColor = Color.YELLOW;
		}
		if (randColor == 1) {
			playerColor = Color.BLUE;
		}
		if (randColor == 2) {
			playerColor = Color.GREEN;
		}
		if (randColor == 3) {
			playerColor = Color.RED;
		}

		currentLevelIndex = 2;

		levels = new Leveldata[5];
		try {
			levels[0] = LeveldataLoader.loadLeveldata(".\\maps\\testMap.txt");
			levels[1] = LeveldataLoader.loadLeveldata(".\\maps\\map1.txt");
			levels[2] = LeveldataLoader.loadLeveldata(".\\maps\\gasTest1.txt");
			levels[3] = LeveldataLoader.loadLeveldata(".\\maps\\springTest.txt");
			levels[4] = LeveldataLoader.loadLeveldata(".\\maps\\map2.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		currentLevel = new Level(levels[currentLevelIndex],playerColor);

		currentLevel.addPlayerDieListener(this);
		currentLevel.addPlayerWinListener(this);

		screenTransition.addScreenTransitionListener(this);
		
		active = true;
		
		numberOfTries = 0;
		levelStartTime = System.currentTimeMillis();
		
		levelCompleteBar = new LevelCompleteBar(100, 10, SCREEN_WIDTH - 200, 10, currentLevel.getPlayer());
		connection = new ConnectionHandler();
		connection.main = this;
		connection.start();
	}
	
	//-----------------------------------------------------Screen Transition Listener
	@Override
	public void onTransitionActivationFinished() {
		if(currentLevel.isPlayerDead()) {
			currentLevel.restartLevel();
			levelCompleteBar = new LevelCompleteBar(100, 10, SCREEN_WIDTH - 200, 10, currentLevel.getPlayer());
		}
		if(currentLevel.isPlayerWin()) {
			if(currentLevelIndex < levels.length-1) {
				changeLevel();
			}
		}
	}

	@Override
	public void onTransitionFinished() {
		active = true;
	}

	//-----------------------------------------------Player Listener
	@Override
	public void onPlayerDeath() {
		numberOfTries++;
		levelStartTime = System.currentTimeMillis();
		if(DEBUGGING) {
			currentLevel.restartLevel();
			levelCompleteBar = new LevelCompleteBar(100, 10, SCREEN_WIDTH - 200, 10, currentLevel.getPlayer());
			return;
		}
		screenTransition.showLoseScreen(numberOfTries);
		
		active = false;
	}

	@Override
	public void onPlayerWin() {
		levelFinishTime = System.currentTimeMillis();
		screenTransition.showVictorySceen(levelFinishTime - levelStartTime);
		
		active = false;
	}

	private void changeLevel() {
		numberOfTries = 0;
		if(currentLevelIndex < levels.length-1) {
			currentLevelIndex++;
			currentLevel = new Level(levels[currentLevelIndex],playerColor);

			currentLevel.addPlayerDieListener(this);
			currentLevel.addPlayerWinListener(this);
			levelCompleteBar = new LevelCompleteBar(100, 10, SCREEN_WIDTH - 200, 10, currentLevel.getPlayer());
		}
	}

	@Override
	public void update(float tslf) {
		//if(KeyboardInputManager.isKeyDown(KeyEvent.VK_N)) init();
		if(KeyboardInputManager.isKeyDown(KeyEvent.VK_ESCAPE)) System.exit(0);

		if (active) currentLevel.update(tslf);
		
		screenTransition.update(tslf);
		
		levelCompleteBar.update(tslf);
		connection.player=Level.player;
	}

	@Override
	public void draw(Graphics g) {
		drawBackground(g);

		//Camera-translate
		currentLevel.draw(g);
		//- Camera-translate
		
		levelCompleteBar.draw(g);
		
		screenTransition.draw(g);
	}

	public void drawBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
}

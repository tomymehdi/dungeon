package front;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import parser.BoardParserFromFile;
import parser.CorruptedFileException;
import saveLoadImplementation.LoadGameFromFile;
import saveLoadImplementation.SaveGameOnFile;
import saveLoadImplementation.SavingCorruptedException;
import back.BloodyFloor;
import back.BoardObtainer;
import back.DungeonGame;
import back.DungeonGameListener;
import back.Floor;
import back.LoadGame;
import back.MoveTypes;
import back.Point;
import back.Putable;
import back.Wall;

public class DungeonGameFrame extends GameFrame {

	private static final long serialVersionUID = 1L;

	private Image playerImage;
	private Map<Class<? extends Putable>, Image> boardImagesByClass = new HashMap<Class<? extends Putable>, Image>();
	private Map<String, Image> monsterImagesByName = new HashMap<String, Image>();
	private Map<String, Image> bonusImagesByName = new HashMap<String,Image>();
	private DataPanel dataPanel;
	private DungeonPanel dungeonPanel;

	public DungeonGameFrame() {
		super("Dungeon game");
		playerImage();
		boardImagesByClass();
		monstersImagesInitialize();
		bonusImagesInitialize();
	}

	private void playerImage() {
		try {
			playerImage = ImageUtils.loadImage("./resources/images/hero.png");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void boardImagesByClass() {
		try {
			boardImagesByClass.put(Wall.class,
					ImageUtils.loadImage("./resources/images/wall.png"));
			boardImagesByClass.put(Floor.class,
					ImageUtils.loadImage("./resources/images/background.png"));
			boardImagesByClass.put(BloodyFloor.class,
					ImageUtils.loadImage("./resources/images/blood.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void bonusImagesInitialize() {
		try {
			bonusImagesByName.put("LIFE",
					ImageUtils.loadImage("./resources/images/healthBoost.png"));
			bonusImagesByName.put("STRENGTH",
					ImageUtils.loadImage("./resources/images/attackBoost.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void monstersImagesInitialize() {
		try {
			monsterImagesByName.put("GOLEM",
					ImageUtils.loadImage("./resources/images/golem.png"));
			monsterImagesByName.put("DRAGON",
					ImageUtils.loadImage("./resources/images/dragon.png"));
			monsterImagesByName.put("SNAKE",
					ImageUtils.loadImage("./resources/images/serpent.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void createDefaultJMenuActionListeners() {

		setNewGameItemAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BoardObtainer boardObtainer = new BoardParserFromFile(new File(
					"./testBoard/boardForTest1"));
					game = new DungeonGame(boardObtainer, new DungeonGameListenerImp());
					drawDataPanel();
					drawDungeonPanel();
					repaint();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Level file is corrupt", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setRestartGameItemAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (game == null) {
						JOptionPane.showMessageDialog(null,
								"You are not playing a level.");
					} else {
						game.restart();
					}
				} catch (CorruptedFileException e1) {
					JOptionPane.showMessageDialog(null, "The file is corrupt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setSaveGameItemAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) {
					File directory = new File("./savedGames");
					if (!directory.exists()) {
						directory.mkdir();
					}
					try {
						new SaveGameOnFile<DungeonGame>(game);
					} catch (SavingCorruptedException e1) {
						JOptionPane.showMessageDialog(null,
								"Files saving error occours. Try again later.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		setSaveGameAsItemAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) {
					File directory = new File("./savedGames");
					if (!directory.exists()) {
						directory.mkdir();
					}
					File file;
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new File("./savedGames"));
					fc.showOpenDialog(DungeonGameFrame.this);
					file = fc.getSelectedFile();
					if (file == null) {
						JOptionPane.showMessageDialog(null,
								"You didn't select any file.");
					} else {
						try {
							new SaveGameOnFile<DungeonGame>(game, file);
						} catch (SavingCorruptedException e1) {
							JOptionPane.showMessageDialog(null,
									"The file is corrupt", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		setLoadGameItemAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				File file;
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("./savedGames"));
				fc.showOpenDialog(DungeonGameFrame.this);
				file = fc.getSelectedFile();
				if (file == null) {
					JOptionPane.showMessageDialog(null,
							"You didn't select any file.");
				} else {
					try {
						LoadGame<DungeonGame> loadGame = new LoadGameFromFile<DungeonGame>(file);
						game = loadGame.getGame(DungeonGame.class, new DungeonGameListenerImp());
					} catch (CorruptedFileException e2) {
						JOptionPane.showMessageDialog(null,
								"The file does not exist", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		setExitGameItemAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DungeonGameFrame.this.setVisible(false);
					DungeonGameFrame.this.dispose();
				} catch (Throwable e1) {
					JOptionPane.showMessageDialog(null, "Exit fault", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void drawDataPanel() {
		dataPanel = new DataPanel(game);
		add(dataPanel);

	}

	private void drawDungeonPanel() {
		dungeonPanel = new DungeonPanel(this);
		add(dungeonPanel);
	}

	public Image getPlayerImage() {
		return playerImage;
	}

	public Map<String, Image> getMonsterImagesByName() {
		return monsterImagesByName;
	}

	public Map<String, Image> getBonusImagesByName() {
		return bonusImagesByName;
	}
	
	public Map<Class<? extends Putable>, Image> getBoardImagesByClass() {
		return boardImagesByClass;
	}

	@Override
	public void addKeyListener() {

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					game.receibeStroke(MoveTypes.LEFT);

					break;
				case KeyEvent.VK_UP:
					game.receibeStroke(MoveTypes.UP);

					break;
				case KeyEvent.VK_RIGHT:
					game.receibeStroke(MoveTypes.RIGHT);

					break;
				case KeyEvent.VK_DOWN:
					game.receibeStroke(MoveTypes.DOWN);

					break;
				}
			}
		});
	}

	public DungeonPanel getDungeonPanel() {
		return dungeonPanel;
	}

	public DataPanel getDataPanel() {
		return dataPanel;
	}

	private class DungeonGameListenerImp implements DungeonGameListener {

		@Override
		public void executeWhenBonusGrabed(Point p) {
			Image imagFloor = getBoardImagesByClass().get(Floor.class);
			dungeonPanel.put(ImageUtils.overlap(imagFloor, getPlayerImage()),
					p.x, p.y);
		}

		@Override
		public void executeWhenCharacterDie(Point p) {
			Image imagFloor = getBoardImagesByClass().get(Floor.class);
			Image imagBloodFloor = getBoardImagesByClass().get(
					BloodyFloor.class);
			dungeonPanel.put(ImageUtils.overlap(imagFloor, imagBloodFloor),
					p.x, p.y);
		}

		@Override
		public void executeWhenGameLoosed() {
			JOptionPane.showMessageDialog(DungeonGameFrame.this, "You loose the level.");
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDungeonPanel());
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDataPanel());
		}

		@Override
		public void executeWhenGameWinned() {
			JOptionPane.showMessageDialog(DungeonGameFrame.this, "You win the level with "
					+ game.getPlayer().getSteps() + "steps.");
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDungeonPanel());
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDataPanel());
		}

		@Override
		public void executeWhenPlayerMoves(MoveTypes moveType) {
			Image imagFloor = getBoardImagesByClass().get(Floor.class);
			Point afterMove = new Point(game.getPlayer().getPosition().x, game
					.getPlayer().getPosition().y);
			Point beforeMove = afterMove.sub(moveType.getDirection());
			dungeonPanel.put(imagFloor, beforeMove.x, beforeMove.y);
			dungeonPanel.put(ImageUtils.overlap(imagFloor, getPlayerImage()),
					afterMove.x, afterMove.y);
		}

		@Override
		public String playerNameRequest() {
			String name = null;
			while (name == null || name.isEmpty()) {
				name = JOptionPane.showInputDialog("Player name");
			}
			return name;
		}
	}
}

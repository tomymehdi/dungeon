package front;

import java.awt.BorderLayout;
import java.awt.Color;
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
import back.DungeonGameImp;
import back.DungeonGameListener;
import back.Floor;
import back.LoadGame;
import back.MoveTypes;
import back.Point;
import back.Putable;
import back.Wall;

/**
 * @author tmehdi Class that extends GameFrame. It's used for the frame of the
 *         game.
 */
public class DungeonGameFrame extends GameFrame {

	private static final long serialVersionUID = 1L;

	private Image playerImage;
	private Map<Class<? extends Putable>, Image> boardImagesByClass = new HashMap<Class<? extends Putable>, Image>();
	private Map<String, Image> monsterImagesByName = new HashMap<String, Image>();
	private Map<String, Image> bonusImagesByName = new HashMap<String, Image>();
	private DataPanel dataPanel;
	private DungeonPanel dungeonPanel;

	public DungeonGameFrame() {
		super("Dungeon game");
		playerImage();
		setIconImage(playerImage);
		boardImagesByClass();
		monstersImagesInitialize();
		bonusImagesInitialize();
		addKeyListener();
	}

	/**
	 * DungeonGameFrame menu. It have 6 options: New game, Restart,Save game,
	 * Save game as..., Load game and Exit
	 * 
	 * @see front.GameFrame#createDefaultJMenuActionListeners()
	 **/
	@Override
	public void createDefaultJMenuActionListeners() {

		setNewGameItemAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (game != null) {
						dataPanel.setVisible(false);
						dungeonPanel.setVisible(false);
						remove(dataPanel);
						remove(dungeonPanel);
						repaint();
						game = null;
					}
					File file = null;
					String[] listBoards;
					File directory = new File("./boards");
					listBoards = directory.list();
					Object selection = JOptionPane.showInputDialog(
							DungeonGameFrame.this, "Select level",
							"Levels selector", JOptionPane.QUESTION_MESSAGE,
							null, listBoards, listBoards[0]);
					if (selection != null) {
						file = new File("./boards/" + selection);
						BoardObtainer boardObtainer = new BoardParserFromFile(
								file);

						game = new DungeonGameImp(boardObtainer,
								new DungeonGameListenerImp());
						drawDungeonPanel();
						drawDataPanel();
						dataPanel.updateUI();
						dungeonPanel.updateUI();
					}
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
						dataPanel.setVisible(false);
						dungeonPanel.setVisible(false);
						drawDungeonPanel();
						drawDataPanel();
						dataPanel.updateUI();
						dungeonPanel.updateUI();
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
						new SaveGameOnFile(game);
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
							new SaveGameOnFile(game, file);
						} catch (SavingCorruptedException e1) {
							JOptionPane.showMessageDialog(null,
									"Files saving error occours. Try again later.",
									"Error", JOptionPane.ERROR_MESSAGE);
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
						LoadGame<DungeonGameImp> loadGame = new LoadGameFromFile<DungeonGameImp>(
								file);
						game = loadGame.getGame(DungeonGameImp.class,
								new DungeonGameListenerImp());
						drawDungeonPanel();
						drawDataPanel();
						dataPanel.updateUI();
						dungeonPanel.updateUI();
					} catch (CorruptedFileException e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Files loading error occours. Try again later.",
								"Error", JOptionPane.ERROR_MESSAGE);
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

	/**
	 * Method to make apear the data panel.
	 */
	private void drawDataPanel() {
		dataPanel = new DataPanel(game.getPlayer(), Color.GRAY);
		add(dataPanel, BorderLayout.EAST);

	}

	/**
	 * Method to make apear the dungeon panel.
	 */
	private void drawDungeonPanel() {
		dungeonPanel = new DungeonPanel(this);
		add(dungeonPanel, BorderLayout.CENTER);
	}

	/**
	 * Geter of the player images.
	 * 
	 * @return
	 */
	public Image getPlayerImage() {
		return playerImage;
	}

	/**
	 * Geter of the bonus images
	 * 
	 * @return
	 */
	public Map<String, Image> getMonsterImagesByName() {
		return monsterImagesByName;
	}

	/**
	 * Geter of the bonus images.
	 * 
	 * @return
	 */
	public Map<String, Image> getBonusImagesByName() {
		return bonusImagesByName;
	}

	/**
	 * Geter of the board images.
	 * 
	 * @return
	 */
	public Map<Class<? extends Putable>, Image> getBoardImagesByClass() {
		return boardImagesByClass;
	}

	/**
	 * Geter of the dungeon panel.
	 * 
	 * @return
	 */
	public DungeonPanel getDungeonPanel() {
		return dungeonPanel;
	}

	/**
	 * Geter of the data panel.
	 * 
	 * @return
	 */
	public DataPanel getDataPanel() {
		return dataPanel;
	}

	/**
	 * Listener of the move keys, up down left right.
	 * 
	 * @see front.GameFrame#addKeyListener()
	 **/
	@Override
	public void addKeyListener() {

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					game.receiveMoveStroke(MoveTypes.LEFT);

					break;
				case KeyEvent.VK_UP:
					game.receiveMoveStroke(MoveTypes.UP);

					break;
				case KeyEvent.VK_RIGHT:
					game.receiveMoveStroke(MoveTypes.RIGHT);

					break;
				case KeyEvent.VK_DOWN:
					game.receiveMoveStroke(MoveTypes.DOWN);

					break;
				}
			}
		});
	}

	/**
	 * Method to initialize player image.
	 */
	private void playerImage() {
		try {
			playerImage = ImageUtils.loadImage("./resources/images/hero.png");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to initialize board images.
	 */
	private void boardImagesByClass() {
		try {
			boardImagesByClass.put(Wall.class, ImageUtils
					.loadImage("./resources/images/wall.png"));
			boardImagesByClass.put(Floor.class, ImageUtils
					.loadImage("./resources/images/background.png"));
			boardImagesByClass.put(BloodyFloor.class, ImageUtils
					.loadImage("./resources/images/blood.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to initialize bonus images.
	 */
	private void bonusImagesInitialize() {
		try {
			bonusImagesByName.put("LIFE", ImageUtils
					.loadImage("./resources/images/healthBoost.png"));
			bonusImagesByName.put("STRENGTH", ImageUtils
					.loadImage("./resources/images/attackBoost.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to initialize monsters images.
	 */
	private void monstersImagesInitialize() {
		try {
			monsterImagesByName.put("GOLEM", ImageUtils
					.loadImage("./resources/images/golem.png"));
			monsterImagesByName.put("DRAGON", ImageUtils
					.loadImage("./resources/images/dragon.png"));
			monsterImagesByName.put("SNAKE", ImageUtils
					.loadImage("./resources/images/serpent.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected Error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @author tmehdi Inner class for the listener of this game implementation.
	 */
	private class DungeonGameListenerImp implements DungeonGameListener {

		@Override
		public void executeWhenBonusGrabed(Point p) {
			dungeonPanel.drawGrabedBonus(DungeonGameFrame.this, p);
		}

		@Override
		public void executeWhenCharacterDie(Point p) {
			dungeonPanel.drawDiedCharacter(DungeonGameFrame.this, p);
		}

		@Override
		public void executeWhenGameLoosed() {
			JOptionPane.showMessageDialog(DungeonGameFrame.this,
					"You loose the level.");
			DungeonGameFrame.this.remove(DungeonGameFrame.this
					.getDungeonPanel());
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDataPanel());
			repaint();
		}

		@Override
		public void executeWhenGameWinned() {
			JOptionPane.showMessageDialog(DungeonGameFrame.this, "WINNER!"
					+ '\n' + "You win the level with "
					+ game.getPlayer().getSteps() + " steps.");
			DungeonGameFrame.this.remove(DungeonGameFrame.this
					.getDungeonPanel());
			DungeonGameFrame.this.remove(DungeonGameFrame.this.getDataPanel());
			repaint();
		}

		@Override
		public void executeWhenPlayerMoves(MoveTypes moveType) {
			dungeonPanel.drawPlayerMove(DungeonGameFrame.this, moveType);
			dungeonPanel.drawDiscoveredCell(DungeonGameFrame.this, moveType);
		}

		@Override
		public String playerNameRequest() {
			String name = null;
			while (name == null || name.isEmpty()) {
				name = JOptionPane.showInputDialog("Player name");
			}
			return name;
		}

		@Override
		public void executeWhenFight() {
			dataPanel.refresh(DungeonGameFrame.this);
			dataPanel.updateUI();
		}
	}

}

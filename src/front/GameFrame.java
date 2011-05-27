package front;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import back.DungeonGame;

public abstract class GameFrame extends JFrame implements DefaultGameMenuBar {

	private static final long serialVersionUID = 1L;
	private static final int CELL_SIZE = 30;
	public DungeonGame game;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newGameItem;
	private JMenuItem restartGameItem;
	private JMenuItem saveGameItem;
	private JMenuItem saveGameAsItem;
	private JMenuItem loadGameItem;
	private JMenuItem exitGameItem;

	public GameFrame() {
		setSize(15 * CELL_SIZE + 17, 15 * CELL_SIZE + 60);
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newGameItem = fileMenu.add("New game");
		restartGameItem = fileMenu.add("Restart");
		loadGameItem = fileMenu.add("Load game");
		saveGameItem = fileMenu.add("Save game");
		saveGameAsItem = fileMenu.add("Save game as ...");
		exitGameItem = fileMenu.add("Exit");

		newGameItem.setAccelerator(KeyStroke.getKeyStroke('N',
				InputEvent.CTRL_DOWN_MASK));

		restartGameItem.setAccelerator(KeyStroke.getKeyStroke('R',
				InputEvent.CTRL_DOWN_MASK));

		saveGameItem.setAccelerator(KeyStroke.getKeyStroke('S',
				InputEvent.CTRL_DOWN_MASK));

		saveGameAsItem.setAccelerator(KeyStroke.getKeyStroke('D',
				InputEvent.CTRL_DOWN_MASK));

		loadGameItem.setAccelerator(KeyStroke.getKeyStroke('L',
				InputEvent.CTRL_DOWN_MASK));

		exitGameItem.setAccelerator(KeyStroke.getKeyStroke('Q',
				InputEvent.CTRL_DOWN_MASK));

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		//this.setLayout(new GridLayout(0, 2));
		createDefaultJMenuActionListeners();
	}

	public void setNewGameItemAction(ActionListener a) {
		newGameItem.addActionListener(a);
	}

	public void setRestartGameItemAction(ActionListener a) {
		restartGameItem.addActionListener(a);
	}

	public void setSaveGameItemAction(ActionListener a) {
		saveGameItem.addActionListener(a);
	}

	public void setSaveGameAsItemAction(ActionListener a) {
		saveGameAsItem.addActionListener(a);
	}

	public void setLoadGameItemAction(ActionListener a) {
		loadGameItem.addActionListener(a);
	}

	public void setExitGameItemAction(ActionListener a) {
		exitGameItem.addActionListener(a);
	}
	
	public abstract void addKeyListener();

	public abstract void createDefaultJMenuActionListeners();

}

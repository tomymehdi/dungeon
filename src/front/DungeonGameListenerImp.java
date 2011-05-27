package front;

import javax.swing.JOptionPane;

import back.DungeonGameListener;

public class DungeonGameListenerImp implements DungeonGameListener {

	@Override
	public void executeWhenBonusGrabed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeWhenCharacterDie() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeWhenGameLoosed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeWhenGameWinned() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeWhenPlayerMoves() {
		//TODO 
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

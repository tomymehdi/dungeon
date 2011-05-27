package front;

import java.awt.Color;
import java.awt.Image;

import back.Floor;
import back.Monster;
import back.Putable;

public class DungeonPanel extends GamePanel {

	private static final long serialVersionUID = 1L;
	private static final int CELL_SIZE = 30;

	public DungeonPanel(DungeonGameFrame dungeonGameFrame) {
		super(dungeonGameFrame.game.getBoardDimension().x - 2,
				dungeonGameFrame.game.getBoardDimension().y - 2, CELL_SIZE,
				new GamePanelListener() {

					@Override
					public void onMouseMoved(int row, int column) {
						System.out.println("Mouse over cell (" + row + ", "
								+ column + ")");
					}
				}, Color.BLUE);

		drawDungeon(dungeonGameFrame);
		this.setVisible(true);
	}

	private void drawDungeon(DungeonGameFrame dungeonGameFrame) {
		Image image;
		for (int i = 1; i < dungeonGameFrame.game.getBoardDimension().x-1; i++) {
			for (int j = 1; j < dungeonGameFrame.game.getBoardDimension().y-1; j++) {
				Putable cell = dungeonGameFrame.game.getBoard()[i][j];
				System.out.println(cell);
				System.out.println((i-1) + "," + (j-1));
				if (cell.getClass().equals(Monster.class)) {
					image = dungeonGameFrame.getBoardImagesByClass().get(Floor.class);
					put(image, i-1, j-1);
					image = dungeonGameFrame.getMonsterImagesByName().get(
							((Monster) cell).getMonsterType().toString());
				} else {
					image = dungeonGameFrame.getBoardImagesByClass().get(
							cell.getClass());
				}

				this.put(image, j - 1, i - 1);
			}

		}
	}

}

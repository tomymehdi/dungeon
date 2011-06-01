package front;

import java.awt.Color;
import java.awt.Image;

import back.Bonus;
import back.Floor;
import back.Monster;
import back.Putable;
import back.Wall;

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
		Image floorImage = dungeonGameFrame.getBoardImagesByClass().get(
				Floor.class);
		int row = dungeonGameFrame.game.getBoardDimension().x - 2;
		int col = dungeonGameFrame.game.getBoardDimension().y - 2;

		for (int i = 1; i <= row ; i++) {
			System.out.println();
			for (int j = 1; j <= col ; j++) {
				Putable cell = dungeonGameFrame.game.getBoard()[i][j];
				if (cell.getClass().equals(Monster.class)) {
					image = dungeonGameFrame.getMonsterImagesByName().get(
							((Monster) cell).getMonsterType().toString());
					image = ImageUtils.overlap(floorImage, image);
					put(image, i - 1, j - 1);
				} else if (cell.getClass().equals(Bonus.class)) {
					image = dungeonGameFrame.getBonusImagesByName().get(
							((Bonus) cell).getBonusType().toString());
					image = ImageUtils.overlap(floorImage, image);
					image = ImageUtils.drawString(image, (((Bonus) cell)
							.getBonusType().getBonusAmount()).toString(),
							Color.RED);
					put(image, i - 1, j - 1);
				} else {
					image = dungeonGameFrame.getBoardImagesByClass().get(
							cell.getClass());
					if (cell.getClass().equals(Wall.class)) {
						put(image, i - 1, j - 1);
					} else {
						put(floorImage, i - 1, j - 1);
					}
				}
			}
		}
		image = ImageUtils.overlap(floorImage, dungeonGameFrame
				.getPlayerImage());
		put(image, dungeonGameFrame.game.getPlayer().getPosition().x - 1,
				dungeonGameFrame.game.getPlayer().getPosition().y - 1);
	}
	
}

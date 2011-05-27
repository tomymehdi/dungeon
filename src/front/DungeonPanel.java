package front;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import back.Floor;
import back.Monster;
import back.Putable;
import back.Wall;

public class DungeonPanel extends GamePanel {

	private static final long serialVersionUID = 1L;
	private static final int CELL_SIZE = 30;

	private Image[][] secondLayerImages;
	private Integer rowSecondLayer;
	private Integer colSecondLayer;

	public DungeonPanel(DungeonGameFrame dungeonGameFrame) {
		super(dungeonGameFrame.game.getBoardDimension().y - 2,
				dungeonGameFrame.game.getBoardDimension().x - 2, CELL_SIZE,
				new GamePanelListener() {

					@Override
					public void onMouseMoved(int row, int column) {
						System.out.println("Mouse over cell (" + row + ", "
								+ column + ")");
					}
				}, Color.BLUE);

		rowSecondLayer = dungeonGameFrame.game.getBoardDimension().x - 2;
		colSecondLayer = dungeonGameFrame.game.getBoardDimension().y - 2;

		secondLayerImages = new Image[rowSecondLayer][colSecondLayer];

		drawDungeon(dungeonGameFrame);
		this.setVisible(true);
	}

	private void drawDungeon(DungeonGameFrame dungeonGameFrame) {
		Image image;
		for (int i = 1; i < dungeonGameFrame.game.getBoardDimension().x - 1; i++) {
			for (int j = 1; j < dungeonGameFrame.game.getBoardDimension().y - 1; j++) {
				Putable cell = dungeonGameFrame.game.getBoard()[i][j];
				image = dungeonGameFrame.getBoardImagesByClass().get(
						Floor.class);
				put(image, i - 1, j - 1);
				if (cell.getClass().equals(Monster.class)) {
					image = dungeonGameFrame.getMonsterImagesByName().get(
							((Monster) cell).getMonsterType().toString());
					putSecondLayer(image, i - 1, j - 1);
				} else {
					image = dungeonGameFrame.getBoardImagesByClass().get(
							cell.getClass());
					if (cell.getClass().equals(Wall.class)) {
						put(image, i - 1, j - 1);
					} else {
						putSecondLayer(image, i - 1, j - 1);
					}
				}
			}
		}
		putSecondLayer(dungeonGameFrame.getPlayerImage(), dungeonGameFrame.game
				.getPlayer().getPosition().x - 1, dungeonGameFrame.game
				.getPlayer().getPosition().y - 1);
	}

	private void putSecondLayer(Image image, int i, int j) {
		secondLayerImages[i][j] = image;
	}

	@Override
	public void put(Image image, int row, int colum) {
		super.put(image, colum, row);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < rowSecondLayer; i++) {
			for (int j = 0; j < colSecondLayer; j++) {
				if (secondLayerImages[i][j] != null) {
					g.drawImage(secondLayerImages[j][i], i * CELL_SIZE, j
							* CELL_SIZE, null);
				}
			}
		}
	}

}

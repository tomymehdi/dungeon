package front;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import back.BloodyFloor;
import back.Bonus;
import back.Floor;
import back.Monster;
import back.MoveTypes;
import back.Point;
import back.Putable;
import back.Wall;

/**
 * @author tmehdi Class that extends the professorship class GamePanel. This
 *         class is used for the Dungeon panel that is into the
 *         DungeonGameFrame.
 */
public class DungeonPanel extends GamePanel {

	private static final long serialVersionUID = 1L;
	private static final int CELL_SIZE = 30;

	private Monster monsterUnderMouse = null;

	/**
	 * @param dungeonGameFrame
	 *            Call the super constructor and draw the pane. The interface
	 *            DungeonPanelListener that extends the professorship inteface
	 *            GamePanelListener is used to make an implementation of the
	 *            "onMouseMoved" method. It allows us to know in what cell is
	 *            and make the diferent actions.
	 */
	public DungeonPanel(final DungeonGameFrame dungeonGameFrame) {
		super(dungeonGameFrame.game.getBoardDimension().x - 2,
				dungeonGameFrame.game.getBoardDimension().y - 2, CELL_SIZE,
				new DungeonPanelListener() {

					@Override
					public void onMouseMoved(int row, int column) {

						Monster monster = dungeonGameFrame.getDungeonPanel()
								.getMonsterUnderMouse();
						if (monster != null) {
							dungeonGameFrame.getDataPanel().removeCharacter(
									monster);
							dungeonGameFrame.getDungeonPanel()
									.setMonsterUnderMouse(null);
						}
						Putable putable = dungeonGameFrame.game.getBoard()[row + 1][column + 1];
						if (putable instanceof Monster) {
							dungeonGameFrame.getDungeonPanel()
									.setMonsterUnderMouse((Monster) putable);
							dungeonGameFrame.getDataPanel().addCharacter(
									dungeonGameFrame.getDungeonPanel()
											.getMonsterUnderMouse());
						}
						dungeonGameFrame.getDataPanel().refresh(
								dungeonGameFrame);
						dungeonGameFrame.getDataPanel().updateUI();

					}
				}, Color.BLACK);
		drawDungeon(dungeonGameFrame);
		this.setVisible(true);
	}

	/**
	 * @param monsterUnderMouse
	 *            Setter of the monster under mouse.
	 */
	public void setMonsterUnderMouse(Monster monsterUnderMouse) {
		this.monsterUnderMouse = monsterUnderMouse;
	}

	/**
	 * @param dungeonGameFrame
	 *            Draw the full dungeon panel.
	 */
	public void dwarFullDungeon(DungeonGameFrame dungeonGameFrame) {
		Image image;
		Image floorImage = dungeonGameFrame.getBoardImagesByClass().get(
				Floor.class);
		Image bloodyFloorImage = ImageUtils
				.overlap(floorImage, dungeonGameFrame.getBoardImagesByClass()
						.get(BloodyFloor.class));
		int row = dungeonGameFrame.game.getBoardDimension().x - 2;
		int col = dungeonGameFrame.game.getBoardDimension().y - 2;

		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
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
					} else if (cell.getClass().equals(BloodyFloor.class)) {
						put(bloodyFloorImage, i - 1, j - 1);
					} else {
						put(floorImage, i - 1, j - 1);
					}
				}
			}
		}

		Point p = new Point(dungeonGameFrame.game.getPlayer().getPosition());

		if (dungeonGameFrame.game.getBoard()[p.x][p.y] instanceof BloodyFloor) {
			image = ImageUtils.overlap(bloodyFloorImage, dungeonGameFrame
					.getPlayerImage());
		}
		image = ImageUtils.overlap(floorImage, dungeonGameFrame
				.getPlayerImage());
		put(image, p.x - 1, p.y - 1);
	}

	/**
	 * @param dungeonGameFrame
	 * 
	 *            Draw the dungeon panel when a game begins.
	 */
	private void drawDungeon(DungeonGameFrame dungeonGameFrame) {
		Image image;
		Image floorImage = dungeonGameFrame.getBoardImagesByClass().get(
				Floor.class);
		Image bloodyFloorImage = ImageUtils
				.overlap(floorImage, dungeonGameFrame.getBoardImagesByClass()
						.get(BloodyFloor.class));

		Point pPos = dungeonGameFrame.game.getPlayer().getPosition();
		pPos = pPos.sub(2, 2);
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				Putable cell = dungeonGameFrame.game.getBoard()[pPos.x + i][pPos.y
						+ j];
				if (cell.getClass().equals(Monster.class)) {
					image = dungeonGameFrame.getMonsterImagesByName().get(
							((Monster) cell).getMonsterType().toString());
					image = ImageUtils.overlap(floorImage, image);
					put(image, pPos.x + i - 1, pPos.y + j - 1);
				} else if (cell.getClass().equals(Bonus.class)) {
					image = dungeonGameFrame.getBonusImagesByName().get(
							((Bonus) cell).getBonusType().toString());
					image = ImageUtils.overlap(floorImage, image);
					image = ImageUtils.drawString(image, (((Bonus) cell)
							.getBonusType().getBonusAmount()).toString(),
							Color.RED);
					put(image, pPos.x + i - 1, pPos.y + j - 1);
				} else {
					image = dungeonGameFrame.getBoardImagesByClass().get(
							cell.getClass());
					if (cell.getClass().equals(Wall.class)) {
						put(image, pPos.x + i - 1, pPos.y + j - 1);
					} else if (cell.getClass().equals(BloodyFloor.class)) {
						put(bloodyFloorImage, pPos.x + i - 1, pPos.y + j - 1);
					} else {
						put(floorImage, pPos.x + i - 1, pPos.y + j - 1);
					}
				}
			}
		}

		Point p = new Point(dungeonGameFrame.game.getPlayer().getPosition());

		if (dungeonGameFrame.game.getBoard()[p.x][p.y] instanceof BloodyFloor) {
			image = ImageUtils.overlap(bloodyFloorImage, dungeonGameFrame
					.getPlayerImage());
		}
		image = ImageUtils.overlap(floorImage, dungeonGameFrame
				.getPlayerImage());
		put(image, p.x - 1, p.y - 1);
	}

	/**
	 * @return Getter of the monsterUnderMouse.
	 */
	public Monster getMonsterUnderMouse() {
		return monsterUnderMouse;
	}

	/**
	 * @param dungeonGameFrame
	 * @param moveType
	 * 
	 *            Redraw if necessary the DungeonPanel.
	 */
	public void drawPlayerMove(DungeonGameFrame dungeonGameFrame,
			MoveTypes moveType) {
		Image bloodyFloor;
		Image floor;
		Point afterMove = new Point(dungeonGameFrame.game.getPlayer()
				.getPosition().x, dungeonGameFrame.game.getPlayer()
				.getPosition().y);
		Point beforeMove = afterMove.sub(moveType.getDirection());
		floor = dungeonGameFrame.getBoardImagesByClass().get(Floor.class);
		bloodyFloor = dungeonGameFrame.getBoardImagesByClass().get(
				BloodyFloor.class);
		bloodyFloor = ImageUtils.overlap(floor, bloodyFloor);
		if (dungeonGameFrame.game.getBoard()[beforeMove.x][beforeMove.y]
				.getClass().equals(BloodyFloor.class)) {
			clear(beforeMove.x - 1, beforeMove.y - 1);
			put(bloodyFloor, beforeMove.x - 1, beforeMove.y - 1);
		} else {
			clear(beforeMove.x - 1, beforeMove.y - 1);
			put(floor, beforeMove.x - 1, beforeMove.y - 1);
		}

		if (dungeonGameFrame.game.getBoard()[afterMove.x][afterMove.y]
				.getClass().equals(BloodyFloor.class)) {
			clear(afterMove.x - 1, afterMove.y - 1);
			put(ImageUtils.overlap(bloodyFloor, dungeonGameFrame
					.getPlayerImage()), afterMove.x - 1, afterMove.y - 1);
		} else {
			clear(afterMove.x - 1, afterMove.y - 1);
			put(ImageUtils.overlap(floor, dungeonGameFrame.getPlayerImage()),
					afterMove.x - 1, afterMove.y - 1);
		}
		dungeonGameFrame.getDataPanel().refresh(dungeonGameFrame);
		dungeonGameFrame.getDataPanel().updateUI();
		updateUI();
	}

	/**
	 * @param dungeonGameFrame
	 * @param p
	 * 
	 *            Draw blood on the floor where a character die.
	 */
	public void drawDiedCharacter(DungeonGameFrame dungeonGameFrame, Point p) {
		Image imagFloor = dungeonGameFrame.getBoardImagesByClass().get(
				Floor.class);
		Image imagBloodFloor = dungeonGameFrame.getBoardImagesByClass().get(
				BloodyFloor.class);
		clear(p.x - 1, p.y - 1);
		put(ImageUtils.overlap(imagFloor, imagBloodFloor), p.x - 1, p.y - 1);
		repaint();

	}

	/**
	 * @param dungeonGameFrame
	 * @param p
	 * 
	 *            Remove de image of the bonus and draw a floor.
	 */
	public void drawGrabedBonus(DungeonGameFrame dungeonGameFrame, Point p) {
		Image floor = dungeonGameFrame.getBoardImagesByClass().get(Floor.class);
		clear(p.x - 1, p.y - 1);
		put(ImageUtils.overlap(floor, dungeonGameFrame.getPlayerImage()),
				p.x - 1, p.y - 1);
		repaint();

	}

	public void drawDiscoveredCell(DungeonGameFrame dungeonGameFrame,
			MoveTypes dir) {
		Point pPos = dungeonGameFrame.game.getPlayer().getPosition();
		List<Point> points = new ArrayList<Point>();
		points.add(pPos.add(dir.getDirection()));
		if (dir == MoveTypes.LEFT || dir == MoveTypes.RIGHT) {
			points.add(pPos.add(1, 0).add(dir.getDirection()));
			points.add(pPos.sub(1, 0).add(dir.getDirection()));
		} else {
			points.add(pPos.add(0, 1).add(dir.getDirection()));
			points.add(pPos.sub(0, 1).add(dir.getDirection()));
		}

		Image image;
		Image floorImage = dungeonGameFrame.getBoardImagesByClass().get(
				Floor.class);
		Image bloodyFloorImage = ImageUtils
				.overlap(floorImage, dungeonGameFrame.getBoardImagesByClass()
						.get(BloodyFloor.class));

		for (Point p : points) {
			if (dungeonGameFrame.game.getBoard()[p.x][p.y].isVisible()) {
				dungeonGameFrame.game.getBoard()[p.x][p.y].setVisible();
				Putable cell = dungeonGameFrame.game.getBoard()[p.x][p.y];
				if (cell.getClass().equals(Monster.class)) {
					image = dungeonGameFrame.getMonsterImagesByName().get(
							((Monster) cell).getMonsterType().toString());
					image = ImageUtils.overlap(floorImage, image);
					put(image, p.x - 1, p.y - 1);
				} else if (cell.getClass().equals(Bonus.class)) {
					image = dungeonGameFrame.getBonusImagesByName().get(
							((Bonus) cell).getBonusType().toString());
					image = ImageUtils.overlap(floorImage, image);
					image = ImageUtils.drawString(image, (((Bonus) cell)
							.getBonusType().getBonusAmount()).toString(),
							Color.RED);
					put(image, p.x - 1, p.y - 1);
				} else {
					image = dungeonGameFrame.getBoardImagesByClass().get(
							cell.getClass());
					if (cell.getClass().equals(Wall.class)) {
						put(image, p.x - 1, p.y - 1);
					} else if (cell.getClass().equals(BloodyFloor.class)) {
						put(bloodyFloorImage, p.x - 1, p.y - 1);
					} else {
						put(floorImage, p.x - 1, p.y - 1);
					}
				}
			}
		}

	}
}

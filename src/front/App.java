package front;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {
		GameFrame dungeonGameFrame = new DungeonGameFrame();
		dungeonGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dungeonGameFrame.setVisible(true);
	}
}

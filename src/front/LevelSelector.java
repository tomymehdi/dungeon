package front;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class LevelSelector extends JFrame {

	private static final long serialVersionUID = 1L;

	private File levelSelected;

	public LevelSelector() {

		String[] listElems;
		File directory = new File("./boards");
		listElems = directory.list();

		final JList list = new JList(listElems);
		final JScrollPane pane = new JScrollPane(list);
		final JFrame frame = new JFrame("Level selector");

		final JButton startGame = new JButton("Start game");
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelSelected = new File("./boards/"
						+ list.getSelectedValue().toString());
			}
		});

		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		frame.getContentPane().add(startGame, BorderLayout.SOUTH);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}

	public File getLevelSelected() {
		return levelSelected;
	}

}

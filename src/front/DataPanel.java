package front;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import back.DungeonGame;

public class DataPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JLabel[] charactersLabels = new JLabel[9]; // 9 es el maximo q van a aparecer en el caso de q al comienzo del juego el jugador este rodeado de monstruos
		
	public DataPanel(DungeonGame game, Color color) {
	}
}

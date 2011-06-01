package front;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import back.DungeonGame;

public class DataPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JLabel[] charactersLabels = new JLabel[9]; // 9 es el maximo q van a aparecer en el caso de q al comienzo del juego el jugador este rodeado de monstruos
		
	public DataPanel(DungeonGame game, Color color) {
		charactersLabels[0] = new JLabel("Nombre");
		charactersLabels[1] = new JLabel("Nombre");
		charactersLabels[2] = new JLabel("Nombre");
		charactersLabels[3] = new JLabel("Nombre");
		charactersLabels[4] = new JLabel("Nombre");
		charactersLabels[5] = new JLabel("Nombre");
		charactersLabels[6] = new JLabel("Nombre");
		charactersLabels[7] = new JLabel("Nombre");
		charactersLabels[8] = new JLabel("Nombre");
		for(JLabel j: charactersLabels){
			add(j);
		}
	}
}

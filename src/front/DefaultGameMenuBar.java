package front;

import java.awt.event.ActionListener;

public interface DefaultGameMenuBar {
	
	public void setNewGameItemAction(ActionListener a);
	
	public void setRestartGameItemAction(ActionListener a);
	
	public void setSaveGameItemAction(ActionListener a);
	
	public void setSaveGameAsItemAction(ActionListener a);
	
	public void setLoadGameItemAction(ActionListener a);
	
	public void setExitGameItemAction(ActionListener a);
	
	public void createDefaultJMenuActionListeners();

}

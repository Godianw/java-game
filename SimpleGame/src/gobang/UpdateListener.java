package gobang;

import java.util.*;

public interface UpdateListener extends EventListener{
	
	public void updateStateEvent(UpdateEvent e);
	
	public void updateWinnerEvent(UpdateEvent e);
}

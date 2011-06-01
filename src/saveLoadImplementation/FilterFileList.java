package saveLoadImplementation;

import java.io.File;
import java.util.List;

public interface FilterFileList extends List<File>{
	
	public FilterFileList filter(String string);

}

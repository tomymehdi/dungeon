package saveLoadImplementation;

import java.io.File;
import java.util.ArrayList;

public class FilterArrayFileList extends ArrayList<File> implements
		FilterFileList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterArrayFileList() {
	}

	public FilterArrayFileList(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				this.add(f);
			}
		}
	}

	@Override
	public FilterFileList filter(String string) {
		FilterArrayFileList filterArrayFileList = new FilterArrayFileList();
		for (File t : this) {
			if (t.getName().startsWith(string)) {
				filterArrayFileList.add(t);
			}
		}
		return filterArrayFileList;
	}

}

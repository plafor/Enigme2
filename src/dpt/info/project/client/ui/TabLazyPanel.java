package dpt.info.project.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class TabLazyPanel extends TabPanel {

	public TabLazyPanel() {
		super();
		this.setVisible(true);
	}

	@Override
	public void add(IsWidget w, String tabText) {
		//LazyPanel pan = new LazyPanel();
		super.add(w, tabText);
	}

	@Override
	public void selectTab(int index) {
		// TODO Auto-generated method stub
		super.selectTab(index);
	}

}

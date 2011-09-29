package dpt.info.project.client.ui;

import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;



public class OngletEnigmeLazy extends LazyPanel {

	//private Widget widget;
	
	public OngletEnigmeLazy() {
		super();
		//this.widget = string;
	}
	
	protected Widget createWidget() {
		
		return new EnigmeTable().onInitialize();
	}

}

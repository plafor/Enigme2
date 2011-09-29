package dpt.info.project.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;


public class FichierUpload  extends DecoratorPanel {

	private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "upload";

	final FileUpload fileUpload;

	public FichierUpload() {
		super();
		VerticalPanel layout = new VerticalPanel();
		//FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		layout.setSpacing(6);
		//layout.setWidth("450px");

		//layout.setHTML(0, 0, "Fichier a uploader");
		fileUpload = new FileUpload();
		fileUpload.ensureDebugId("cwFileUpload");
		//layout.setWidget(1,0,fileUpload);
		fileUpload.setName("fileUpload");
		final FormPanel form = new FormPanel();
		form.setAction(UPLOAD_ACTION_URL);


		// Add a button to upload the file
		Button uploadButton = new Button("Envoyer");

		uploadButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String filename = fileUpload.getFilename();
				if (filename.length() != 0) {
					form.submit();
				} else {
					Window.alert("Selectionnez un fichier");
				}
			}
		});
		//layout.setWidget(2,0,uploadButton);
		//cellFormatter.setColSpan(2, 0, 2);
		//cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);


		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		layout.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		layout.setSpacing(15);
		layout.add(fileUpload);
		layout.add(uploadButton);
		
		form.setWidget(layout);
		
		form.addFormHandler(new FormHandler()
		{
			public void onSubmit(FormSubmitEvent event)
			{
				// if (something_is_wrong)
					// {
					// Take some action
				// event.setCancelled(true);
				// }
			}

			public void onSubmitComplete(FormSubmitCompleteEvent event)
			{
				Window.alert(event.getResults());
			}
		});


		// Wrap the content in the DecoratorPanel
		this.setWidget(form);

	}

}
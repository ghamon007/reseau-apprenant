package fr.admr.reseau.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.repository.FormationRepository;

@SpringUI(path="/formation")
@Theme("valo")
public class FormationUI extends UI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	FormationEditor formationEditor;
	
	FormationRepository formationRepository;
	
	Grid<Formation> grid;
	
	TextField filter;

	Button addNewBtn;


	@Override
	protected void init(VaadinRequest request) {
		
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, formationEditor);
		setContent(mainLayout);
		
		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "dateFormation", "prenom","statut");

		filter.setPlaceholder("Filtrer par nom");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listFormation());

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			formationEditor.editFormation(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> formationEditor.editFormation(new Formation()));

		// Initialize listing
		listFormation();

	}
	
	private void listFormation() {
			grid.setItems(formationRepository.findAll());
	}

}

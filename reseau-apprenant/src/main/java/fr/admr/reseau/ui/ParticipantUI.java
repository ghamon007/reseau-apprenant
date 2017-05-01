package fr.admr.reseau.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.repository.ParticipantRepository;

@SpringUI(path="participant")
@Theme("valo")
public class ParticipantUI extends UI {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ParticipantRepository participantRepository;
	
	private final ParticipantEditor participantEditor;
	
	final Grid<Participant> grid;
	
	final TextField filter;

	final Button addNewBtn;

	@Autowired
	public ParticipantUI(ParticipantRepository repo, ParticipantEditor aParticipantEditor) {
	    this.participantRepository = repo;
	    this.participantEditor = aParticipantEditor;
	    this.grid = new Grid<>(Participant.class);
	    this.filter = new TextField();
	    this.addNewBtn = new Button("Nouveau participant", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, participantEditor);
		setContent(mainLayout);
		
		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "nom", "prenom","statut");

		filter.setPlaceholder("Filtrer par nom");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listParticipant(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			participantEditor.editParticipant(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> participantEditor.editParticipant(new Participant("", "", Statut.PARTICIPANT)));

		// Listen changes made by the editor, refresh data from backend
		participantEditor.setChangeHandler(() -> {
			participantEditor.setVisible(false);
			listParticipant(filter.getValue());
		});

		// Initialize listing
		listParticipant(null);
	}
	
	private void listParticipant(String filterText) {
	    if (StringUtils.isEmpty(filterText)) {
			grid.setItems(participantRepository.findAll());
		}
		else {
			grid.setItems(participantRepository.findByNomStartsWithIgnoreCase(filterText));
		}
	}

}

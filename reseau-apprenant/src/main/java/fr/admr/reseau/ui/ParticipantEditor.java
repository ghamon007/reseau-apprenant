package fr.admr.reseau.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.repository.ParticipantRepository;

@SpringComponent
@UIScope
public class ParticipantEditor extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ParticipantRepository repository = null;

	/**
	 * The currently edited customer
	 */
	private Participant participant;

	/* Fields to edit properties in Customer entity */
	TextField nom = new TextField("Nom");
	TextField prenom = new TextField("Prénom");
	NativeSelect<Statut> statuts = new NativeSelect<>("Statut");

	/* Action buttons */
	Button save = new Button("Sauvegarder", FontAwesome.SAVE);
	Button cancel = new Button("Annuler");
	Button delete = new Button("Supprimer", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Participant> binder = new Binder<>(Participant.class);

	@Autowired
	public ParticipantEditor(ParticipantRepository repository) {
		this.repository = repository;
		statuts.setItems(Statut.values());
		if (participant != null && participant.getStatut() !=  null){
			statuts.setSelectedItem(participant.getStatut());
		}
		
		addComponents(nom, prenom,statuts, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(participant));
		delete.addClickListener(e -> repository.delete(participant));
		cancel.addClickListener(e -> editParticipant(participant));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editParticipant(Participant particiant) {
		if (particiant == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = particiant.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			participant = repository.findOne(particiant.getId());
		}
		else {
			this.participant = particiant;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(participant);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		nom.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}

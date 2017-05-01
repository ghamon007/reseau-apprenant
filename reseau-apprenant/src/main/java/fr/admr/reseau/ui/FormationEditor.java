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

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.domain.TypeFormation;
import fr.admr.reseau.repository.FormationRepository;
import fr.admr.reseau.repository.ParticipantRepository;

@SpringComponent
@UIScope
public class FormationEditor extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FormationRepository repository = null;

	/**
	 * The currently edited customer
	 */
	private Formation formation;

	/* Fields to edit properties in Customer entity */
	TextField dateFormation = new TextField("Date");
	TextField tuteur = new TextField("Tuteur");
	TextField formateur = new TextField("Formateur");
	TextField participant1 = new TextField("Participant 1");
	TextField participant2 = new TextField("Participant 2");
	NativeSelect<TypeFormation> typesFormation = new NativeSelect<>("Type");

	/* Action buttons */
	Button save = new Button("Sauvegarder", FontAwesome.SAVE);
	Button cancel = new Button("Annuler");
	Button delete = new Button("Supprimer", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Formation> binder = new Binder<>(Formation.class);

	@Autowired
	public FormationEditor(FormationRepository repository) {
		this.repository = repository;
		
		typesFormation.setItems(TypeFormation.values());

		addComponents(dateFormation, typesFormation,tuteur,formateur, participant1, participant2, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(formation));
		delete.addClickListener(e -> repository.delete(formation));
		cancel.addClickListener(e -> editFormation(formation));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editFormation(Formation formation) {
		if (formation == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = formation.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			formation = repository.findOne(formation.getId());
		}
		else {
			formation = formation;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(formation);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		dateFormation.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}

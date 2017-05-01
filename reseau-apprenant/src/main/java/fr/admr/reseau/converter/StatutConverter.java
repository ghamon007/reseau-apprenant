package fr.admr.reseau.converter;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.NativeSelect;

import fr.admr.reseau.domain.Statut;

public class StatutConverter implements Converter<NativeSelect<Statut>, Statut> {

	@Override
	public Result<Statut> convertToModel(NativeSelect<Statut> value, ValueContext context) {
		return null;
	}

	@Override
	public NativeSelect<Statut> convertToPresentation(Statut value, ValueContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}

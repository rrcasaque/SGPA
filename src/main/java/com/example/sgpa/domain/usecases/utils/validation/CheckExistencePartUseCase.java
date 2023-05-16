package com.example.sgpa.domain.usecases.utils.validation;

import java.util.Optional;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.part.PartDAO;

public class CheckExistencePartUseCase {
	private PartDAO partDAO;

	public CheckExistencePartUseCase(PartDAO partDAO) {		
		this.partDAO = partDAO;
	}
	
	public void check(Part part) {
		Optional<Part> partFinded = partDAO.findOne(null);
		if(partFinded.isEmpty())
			throw new RuntimeException("part not found!");
	}
}

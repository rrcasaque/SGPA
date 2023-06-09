package com.example.sgpa.domain.usecases.utils.validation;

import java.util.Optional;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.part.PartDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;

public class CheckExistencePartUseCase {
	private PartItemDAO partItemDAO;

	public CheckExistencePartUseCase(PartItemDAO partItemDAO) {
		this.partItemDAO = partItemDAO;
	}
	
	public void check(int patrimonialId) {
		Optional<PartItem> foundPart = partItemDAO.findOne(patrimonialId);
		if(foundPart.isEmpty())
			throw new RuntimeException("Part not found!");
	}
}

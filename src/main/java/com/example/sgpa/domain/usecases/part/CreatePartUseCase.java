package com.example.sgpa.domain.usecases.part;

public class CreatePartUseCase {
    private PartDAO partDAO;
    public CreatePartUseCase(PartDAO partDAO) {
        this.partDAO = partDAO;
    }
}

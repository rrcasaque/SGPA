package com.example.sgpa.application;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.application.repository.inmemory.*;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.auth.Auth;
import com.example.sgpa.domain.usecases.checkout.CreateCheckOutUseCase;
import com.example.sgpa.domain.usecases.checkout.ReturnPartItemUseCase;
import com.example.sgpa.domain.usecases.part.*;
import com.example.sgpa.domain.usecases.report.GenerateReportByPartUseCase;
import com.example.sgpa.domain.usecases.report.GenerateReportByUserUseCase;
import com.example.sgpa.domain.usecases.report.GenerateReportUseCase;
import com.example.sgpa.domain.usecases.reservation.CreateReservationUseCase;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingsIssuesUseCase;

public class Main {
    public static Auth authUseCase;
    public static CreateCheckOutUseCase createCheckOutUseCase;
    public static ReturnPartItemUseCase returnPartItemUseCase;
    public static CheckForPartItemAvailabilityUseCase checkForPartItemAvailabilityUseCase;
    public static CreatePartItemUseCase createPartItemUseCase;
    public static CreatePartUseCase createPartUseCase;
    public static DeletePartItemUserCase deletePartItemUserCase;
    public static UpdatePartItemUseCase updatePartItemUseCase;
    public static GenerateReportByPartUseCase generateReportByPartUseCase;
    public static GenerateReportByUserUseCase generateReportByUserUseCase;
    public static GenerateReportUseCase generateReportUseCase;
    public static CreateReservationUseCase createReservationUseCase;
    public static CheckForUserPendingsIssuesUseCase checkForUserPendingsIssuesUseCase;
    public static void main(String[] args) {
        configureInjection();
        //fazer autenticação

        //criar peças
        Part propeller = createPartUseCase.createPart("Hélice", 5, 30);
        Part valve = createPartUseCase.createPart("Válvula", 10, 30);
        Part brakePad = createPartUseCase.createPart("Pastilha de freio", 8, 30);
        //criar itens-peças
        createPartItemUseCase.createPartItem("h01", propeller);
        createPartItemUseCase.createPartItem("h02", propeller);
        createPartItemUseCase.createPartItem("h03", propeller);

        createPartItemUseCase.createPartItem("v01", valve);
        createPartItemUseCase.createPartItem("v02", valve);
        createPartItemUseCase.createPartItem("v03", valve);

        createPartItemUseCase.createPartItem("b01", brakePad);
        createPartItemUseCase.createPartItem("b02", brakePad);
        createPartItemUseCase.createPartItem("b03", brakePad);

        //criar tecnico
        //criar professor
        //criar aluno

        //criar reservas
        //criar emprestimos
        //fazer devoluções

        //gerar relatórios
        LocalDateTime startDate = LocalDateTime.of(2015, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.now();
        
        List<Event> partReport = generateReportByPartUseCase.generate(valve,startDate,endDate);
        List<Event> userReport = generateReportByUserUseCase.generate(null, null, startDate, endDate);
        List<Event> report = generateReportUseCase.generate(startDate, endDate);
    }
    private static void configureInjection(){
        InMemoryCheckedOutItemDAO inMemoryCheckedOutItemDAO = new InMemoryCheckedOutItemDAO();
        InMemoryCheckOutDAO inMemoryCheckOutDAO = new InMemoryCheckOutDAO();
        InMemoryEventDAO inMemoryEventDAO = new InMemoryEventDAO();
        InMemoryPartItemDAO inMemoryPartItemDAO = new InMemoryPartItemDAO();
        InMemoryPartDAO inMemoryPartDAO = new InMemoryPartDAO();
        InMemoryReservationDAO inMemoryReservationDAO = new InMemoryReservationDAO();
        InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();

        authUseCase = new Auth(inMemoryUserDAO);
        checkForUserPendingsIssuesUseCase = new CheckForUserPendingsIssuesUseCase(inMemoryCheckedOutItemDAO);
        checkForPartItemAvailabilityUseCase = new CheckForPartItemAvailabilityUseCase(inMemoryPartItemDAO);
        createCheckOutUseCase = new CreateCheckOutUseCase(
                inMemoryUserDAO,
                inMemoryPartItemDAO,
                inMemoryCheckOutDAO,
                inMemoryCheckedOutItemDAO,
                inMemoryEventDAO,
                checkForUserPendingsIssuesUseCase,
                checkForPartItemAvailabilityUseCase);
        returnPartItemUseCase = new ReturnPartItemUseCase(
                inMemoryCheckedOutItemDAO,
                inMemoryPartItemDAO,
                inMemoryEventDAO,
                inMemoryUserDAO);
        createPartItemUseCase = new CreatePartItemUseCase(inMemoryPartItemDAO, inMemoryPartDAO);
        createPartUseCase = new CreatePartUseCase(inMemoryPartDAO);
        deletePartItemUserCase = new DeletePartItemUserCase(inMemoryPartItemDAO);
        updatePartItemUseCase = new UpdatePartItemUseCase(inMemoryPartItemDAO);
        generateReportByPartUseCase = new GenerateReportByPartUseCase(inMemoryEventDAO,inMemoryPartDAO);
        generateReportByUserUseCase = new GenerateReportByUserUseCase(inMemoryEventDAO,inMemoryUserDAO);
        generateReportUseCase = new GenerateReportUseCase(inMemoryEventDAO);        
        createReservationUseCase = new CreateReservationUseCase(
                inMemoryUserDAO,
                inMemoryPartItemDAO,
                inMemoryReservationDAO,
                inMemoryEventDAO,
                checkForUserPendingsIssuesUseCase,
                checkForPartItemAvailabilityUseCase);
    }
}

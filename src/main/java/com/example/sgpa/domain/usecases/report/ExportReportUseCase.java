package com.example.sgpa.domain.usecases.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.utils.FixLengthStringBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportReportUseCase {
    private final FixLengthStringBuilder formatter = new FixLengthStringBuilder();
    public void export(List<Event> events){
        Document document = new Document();
        try{
            String path = "SGPA_REPORT_"+LocalDateTime.now().toString().substring(0,19).replace("-","")
                    .replace(":","")+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(eventListToParagraph(events));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }finally {
            if(document.isOpen()) document.close();
        }
    }
    private String eventToLineReport(Event event){
        return formatter.format(String.valueOf(event.getPatrimonialId()),15)
                + formatter.format(event.getPartType(),9)
                + formatter.format(event.getEventType().toString(),14)
                + formatter.format(event.getRequesterName(),16)
                + formatter.format(event.getTechnicianName(),13)
                + formatter.format(event.getStringTimeStamp(),15);
    }

    private Paragraph eventListToParagraph(List<Event> events) throws DocumentException, IOException {
        String headerStr = formatter.format("Patrimônio",15)
                +formatter.format("Peça",9)
                +formatter.format("Transação",14)
                +formatter.format("Solicitante",16)
                +formatter.format("Operador",13)
                +formatter.format("Data e Hora",15)+"\n\n";

        LocalDateTime timestamp  = LocalDateTime.now();
        int day = timestamp.getDayOfMonth();
        int month = timestamp.getMonthValue();
        int year = timestamp.getYear();
        int hour = timestamp.getHour();
        int min = timestamp.getMinute();

        String footerSrt = "\n\n\n\n\nRelatório gerado pelo técnico: "
                + Session.getLoggedTechnician().getName()
                +" às "+hour+"h"+min+"min do dia "+day+"/"+month+"/"+year+".";

        StringBuilder reportStr = new StringBuilder();
        for (Event event : events) {
            reportStr.append(eventToLineReport(event)).append("\n\n");
        }

        BaseFont courier = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font mono = new Font(courier);
        mono.setSize(9f);

        return new Paragraph(headerStr+reportStr+footerSrt,mono);
    }
}

package com.bcpc.movement.bean;

import com.bcpc.movement.controller.dto.ReportDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfUtils {
    public static byte[] generateReportPdf(List<ReportDTO> reports) throws DocumentException, IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();




        // Add document title
        document.add(new Paragraph("ESTADO DE CUENTA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK)));
        document.add(new Paragraph(" ")); // Empty line for spacing

        // Add account information section
        PdfPTable accountInfoTable = new PdfPTable(2);
        accountInfoTable.setWidthPercentage(100); // Full width
        accountInfoTable.setSpacingBefore(10f);
        accountInfoTable.setSpacingAfter(10f);

        // Add the account information rows
        accountInfoTable.addCell(new PdfPCell(new Phrase("Nombre: "+reports.get(0).getName(), FontFactory.getFont(FontFactory.HELVETICA, 10))));
        accountInfoTable.addCell(new PdfPCell(new Phrase("Cuenta: "+reports.get(0).getNumberAccount(), FontFactory.getFont(FontFactory.HELVETICA, 10))));
        accountInfoTable.addCell(new PdfPCell(new Phrase("Total: "+reports.get(0).getTotalAmount() , FontFactory.getFont(FontFactory.HELVETICA, 10))));
        accountInfoTable.addCell(new PdfPCell(new Phrase("CICLO 1", FontFactory.getFont(FontFactory.HELVETICA, 10))));

        // Add the account info table to the document
        document.add(accountInfoTable);

        // Add header row with column titles
        PdfPTable table = new PdfPTable(7); // 8 columns for the transaction details
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        // Add table headers
        String[] headers = {"Fecha","Cliente","# Cuenta","Tipo Movimiento", "Monto Inicial", "Movimiento","Monto Total",};
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        // Iterate over each report item and add details to the table
        for (ReportDTO report : reports) {
            System.out.println(report);

            Date movementAt = report.getMovementAt();
            String formattedDate = dateFormat.format(movementAt);

            table.addCell(new PdfPCell(new Phrase(formattedDate)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(report.getName()))));
            table.addCell(new PdfPCell(new Phrase(report.getNumberAccount())));
            table.addCell(new PdfPCell(new Phrase(report.getTypeAccount())));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(report.getInitialAmount()))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(report.getMovementAmount()))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(report.getTotalAmount()))));

        }

        // Add the table to the document
        document.add(table);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("R.U.C: 1790010397001", FontFactory.getFont(FontFactory.HELVETICA, 8)));
        document.add(new Paragraph("Dirección: Av. Amazonas 4560 y Pereira", FontFactory.getFont(FontFactory.HELVETICA, 8)));
        document.add(new Paragraph("Teléfono: (02) 2980 980", FontFactory.getFont(FontFactory.HELVETICA, 8)));
        document.add(new Paragraph("Quito - Ecuador", FontFactory.getFont(FontFactory.HELVETICA, 8)));

        // Close the document
        document.close();
        return baos.toByteArray();
    }
}

package org.example.tourplanner.BL.Services;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;

import java.util.List;

@Slf4j
public class PDFService implements IPDFService {
    private final IntTourService tourService;
    private static PDFService pdfService;

    public PDFService() {
        this.tourService = new TourService(); // Direct instantiation
        log.info("PDFService initialized");
    }

    public static PDFService getPDFService() {
        if (pdfService == null) {
            pdfService = new PDFService();
        }
        return pdfService;
    }

    @Override
    public void createTourListPDF(String filePath) throws Exception {
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        log.info("Creating PDF at {}", filePath);

        // Title
        document.add(new Paragraph("Tour List: ").setBold().setFontSize(14).setTextAlignment(
                TextAlignment.LEFT
        ));

        // Table
        List<Tour> tours = tourService.getAllTours();
        Table table = new Table(7);
        // Table header
        table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Distance").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Transport Type").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("From").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("To").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Estimated Time").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));

        // Table rows
        for (Tour tour : tours) {
            table.addCell(new Cell().add(new Paragraph(tour.getName()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(tour.getDescription()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(tour.getDistance())).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(tour.getTransportType()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(tour.getFrom()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(tour.getTo()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(tour.getEstimatedTime()).setTextAlignment(TextAlignment.CENTER)));
        }

        // Add table to document
        document.add(table);
        document.close();
        log.info("PDF created successfully at {}", filePath);
    }
    @Override
    public void createTourPDF(String filePath, String name) throws Exception {

        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        log.info("Creating PDF at {}", filePath);

        // Title
        document.add(new Paragraph("Tour: ").setBold().setFontSize(14).setTextAlignment(
                TextAlignment.LEFT
        ));

        // Table
        Tour tour = tourService.getTourByName(name);
        Table table = new Table(7);
        // Table header
        table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Distance").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Transport Type").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("From").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("To").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Estimated Time").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));

        // Table rows
        table.addCell(new Cell().add(new Paragraph(tour.getName()).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(tour.getDescription()).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(tour.getDistance())).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(tour.getTransportType()).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(tour.getFrom()).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(tour.getTo()).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(tour.getEstimatedTime()).setTextAlignment(TextAlignment.CENTER)));

        // Add table to document
        document.add(table);
        document.close();
        log.info("PDF created successfully at {}", filePath);
    }
}

package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static String decodeBase64(String input) {
        return  new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
    }

    public static String getDataFormatada(LocalDateTime data, boolean max){
        DateTimeFormatter formatter;
        if(!max) {
             formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }else{
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        }
        return data.format(formatter);
    }
    public static void savePhoto(String photoName, String imagemBase64) {
        try {
            if (photoName == null || imagemBase64 == null)
                throw new Exception("Erro ao salvar a foto!");

            // Remove prefixo se vier do tipo "data:image/png;base64,..."
            if (imagemBase64.contains(",")) {
                imagemBase64 = imagemBase64.split(",")[1];
            }

            byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);

            // Garante que a pasta existe
            File pasta = new File("uploads");
            if (!pasta.exists()) pasta.mkdirs();

            if (!photoName.toLowerCase().endsWith(".jpg")) {
                photoName += ".png";
            }
            // Caminho do arquivo
            String caminho = "uploads/" + photoName;

            try (FileOutputStream fos = new FileOutputStream(caminho)) {
                fos.write(imagemBytes);
            }

            System.out.println("Imagem salva com sucesso em: " + caminho);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Locale LOCALE_BR = new Locale("pt", "BR");

    public static String convertValor(BigDecimal valor) {

        if (valor == null)  return "R$ 0,00";
        NumberFormat formatador = NumberFormat.getCurrencyInstance(LOCALE_BR);
        return formatador.format(valor);
    }
    // calcular total dos valores
    public static String convertStatusPagamento(StatusPagamentoEnum status) {
        if (status == null)  return "";
        return switch (status) {
            case NAO_PAGO -> "Não Pago";
            case PAGO -> "Pago";
            case VENCIDO -> "Vencido";
        };
    }

    public static ByteArrayOutputStream gerarGastosPdf(List<Gasto> gastos){
        List<GastoDTO> lista = GastoMapper.toGastoDTOList(gastos);

        BigDecimal totalValor = lista.stream()
                .map(GastoDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();

        // Título
        com.lowagie.text.Font titulo = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 18, com.lowagie.text.Font.BOLD);
        Paragraph paragraph = new Paragraph("Relatório de Gastos", titulo);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        document.add(paragraph);

        // Tabela
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{4, 2, 2, 2});

        com.lowagie.text.Font headerFont = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 12, com.lowagie.text.Font.BOLD);

        addHeader(table, "Descrição", headerFont);
        addHeader(table, "Vencimento", headerFont);
        addHeader(table, "Valor", headerFont);
        addHeader(table, "Status", headerFont);

        com.lowagie.text.Font bodyFont = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 11);

        for (GastoDTO gasto : lista) {

            table.addCell(new PdfPCell(new Phrase(
                    gasto.getDescricao(),
                    bodyFont)));

            table.addCell(new PdfPCell(new Phrase(
                    gasto.getVencimento() != null
                            ? getDataFormatada(gasto.getVencimento(), false)
                            : "",
                    bodyFont)));

            table.addCell(new PdfPCell(new Phrase(
                    convertValor(gasto.getValor()),
                    bodyFont)));

            table.addCell(new PdfPCell(new Phrase(
                    gasto.getStatusPagamento() != null
                            ? convertStatusPagamento(gasto.getStatusPagamento())
                            : "",
                    bodyFont)));
        }

        PdfPCell totalTitulo = new PdfPCell(new Phrase("TOTAL", headerFont));
        totalTitulo.setColspan(2);
        table.addCell(totalTitulo);

        table.addCell(new PdfPCell(new Phrase(
                convertValor(totalValor),
                headerFont)));

        table.addCell(new PdfPCell(new Phrase("")));

        document.add(table);

        document.close();

        return outputStream;

    }
    public static ByteArrayOutputStream gerarGastosExcel(List<Gasto> gastos) throws IOException {
        List<GastoDTO> lista = GastoMapper.toGastoDTOList(gastos);

        InputStream template = new ClassPathResource("templates/relatorio_gastos.xlsx").getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(template);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // STYLES
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setAlignment(HorizontalAlignment.CENTER);
        borderStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        int rowNum = 2;
        BigDecimal totalValor = lista.stream().map(GastoDTO::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        for (GastoDTO gasto : lista) {

            Row row = sheet.createRow(rowNum++);
            // Descrição
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(gasto.getDescricao());
            cell0.setCellStyle(borderStyle);
            // Vencimento
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(gasto.getVencimento() != null ? getDataFormatada(gasto.getVencimento(),false) : "");
            cell1.setCellStyle(borderStyle);
            // Valor
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(convertValor(gasto.getValor()));
            cell2.setCellStyle(borderStyle);
            // Status do Pagamento
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(gasto.getStatusPagamento() != null ? convertStatusPagamento(gasto.getStatusPagamento()) : "");
            cell3.setCellStyle(borderStyle);
        }
        // Total
        Row row = sheet.createRow(rowNum);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("TOTAL");
        cell0.setCellStyle(borderStyle);
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("");
        cell1.setCellStyle(borderStyle);
        Cell cell2 = row.createCell(2);
        cell2.setCellValue(convertValor(totalValor));
        cell2.setCellStyle(borderStyle);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
    }

    private static void addHeader(PdfPTable table, String texto, com.lowagie.text.Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(8);

        table.addCell(cell);
    }

    public static String retornarMesAnteriorAno(){
        LocalDateTime data = LocalDateTime.now();
        return data.minusMonths(1).format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }
}

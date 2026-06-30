package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils.Utils.*;

@RestController
@RequestMapping("relatorio")
public class RelatorioController {

    private final IGastoApplication _gastoApplication;

    public RelatorioController(IGastoApplication gastoApplication) {
        this._gastoApplication = gastoApplication;
    }

    @GetMapping("/gastos/excel")
    public ResponseEntity<byte[]> gerarExcel(@ModelAttribute GastoDTO filter) {

        try {

            var gastos = _gastoApplication.findAllByFilter(filter);
            List<GastoDTO> lista = GastoMapper.toAgendamentoDTOList(gastos);

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

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=relatorio_gastos.xlsx"
                    )
                    .contentType(
                            MediaType.parseMediaType(
                                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                            )
                    )
                    .body(outputStream.toByteArray());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}


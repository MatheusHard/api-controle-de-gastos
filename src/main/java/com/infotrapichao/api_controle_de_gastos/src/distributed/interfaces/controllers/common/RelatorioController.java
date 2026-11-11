package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.lowagie.text.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
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
    public ResponseEntity<byte[]> gerarExcel(@ModelAttribute GastoRequestDTO filter) {

        try {
            GastoDTO dto = GastoMapper.toGastoDTO(filter);
            List<Gasto> gastos = _gastoApplication.findAllByFilter(dto, 0, 10000).getContent();
            ByteArrayOutputStream outputStream = gerarGastosExcel(gastos);

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

    @GetMapping("/gastos/pdf")
    public ResponseEntity<byte[]> gerarPdf(@ModelAttribute GastoRequestDTO filter) {

        try {
            GastoDTO dto = GastoMapper.toGastoDTO(filter);
            List<Gasto> gastos = _gastoApplication.findAllByFilter(dto, 0, 10000).getContent();
            ByteArrayOutputStream outputStream = gerarGastosPdf(gastos);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=relatorio_gastos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(outputStream.toByteArray());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
   }


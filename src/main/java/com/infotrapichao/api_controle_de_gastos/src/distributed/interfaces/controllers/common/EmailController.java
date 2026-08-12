package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.created.EmailCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response.EmailResponseDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.services.smtp.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.net.URI;

import static com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils.Utils.gerarGastosPdf;

@RestController
@RequestMapping("/email")
public class EmailController {

    final EmailService _emailService;
    final IGastoApplication _gastoApplication;


    public EmailController(EmailService emailService, IGastoApplication gastoApplication) {
        this._emailService = emailService;
        this._gastoApplication = gastoApplication;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponseDTO> sendEmail(@Validated @RequestBody EmailCreatedRequestDTO emailDTO) {

        try {
            //Pegar o arquivo Pdf
            GastoDTO dto = GastoMapper.toGastoDTO(emailDTO.getFilters());
            var gastos = _gastoApplication.findAllByFilter(dto);
            ByteArrayOutputStream outputStream = gerarGastosPdf(gastos);
            emailDTO.setFile(outputStream);
            //send email

            _emailService.sendHtmlEmail(emailDTO);
        }catch (Exception e){
            return ResponseEntity.ok(new EmailResponseDTO(false,"Erro -> "+ e.getMessage()));
        }
            return ResponseEntity.ok(new EmailResponseDTO(true,"E-mail enviado com sucesso."));
    }
}

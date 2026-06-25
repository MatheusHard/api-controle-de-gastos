package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.services.workers;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.EmailDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.services.smtp.EmailService;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WorkerSendFaturaVencida {

    private final IGastoApplication _gastoApplication;
    @Autowired
    private EmailService emailService;

    public WorkerSendFaturaVencida(IGastoApplication gastoApplication) {
        _gastoApplication = gastoApplication;
    }

    @Scheduled(cron = "0 21 20 * * *", zone = "America/Sao_Paulo") // 1º segundos; 2º minutos; 3º horas [Campo]
    public void executarTarefaDiaria() {
        System.out.println("Executando tarefa diária às 20:50...");
        this.execSendEmails();
        System.out.println("Fim tarefa diária...");
    }

    private void execSendEmails() {
        GastoDTO filter = new GastoDTO();
        filter.setVencimento(LocalDateTime.now()); // Pegar apenas faturas que vencem hoje
        filter.setDeletado(false);
        filter.setPago(false);
        var list = _gastoApplication.findAllByFilter(filter);
        for (Gasto fatura : list) {
            sendEmail(fatura);
        }
    }

    private void sendEmail(Gasto fatura) {
        emailService.sendHtmlEmail(this.generateEmailDTO(fatura));
    }

    private EmailDTO generateEmailDTO(Gasto fatura) {
        EmailDTO email = new EmailDTO();
        email.setNomeUsuario(fatura.getUser().getUsername());
        email.setDescricao(fatura.getDescricao());
        email.setAssunto("Fatura à Vencer");
        email.setDestinatario(fatura.getUser().getEmail());
        email.setRemetente("matheushard2013@gmail.com");
        email.setValor(fatura.getValor());
        email.setVencimento(fatura.getVencimento());

        return email;
    }
}

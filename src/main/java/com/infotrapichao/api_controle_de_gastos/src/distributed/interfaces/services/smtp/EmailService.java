package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.services.smtp;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils.Utils;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.created.EmailCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

import static com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils.Utils.retornarPeriodo;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Value("${spring.mail.empresa}")
    private String empresa;

    public void sendHtmlEmail(EmailDTO emailDTO) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAssunto());
            helper.setText(getCorpo(emailDTO), true);
            helper.setFrom(emailDTO.getRemetente(), empresa);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

    public void sendHtmlEmail(EmailCreatedRequestDTO emailDTO) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(emailDTO.getDestinatario());
        helper.setSubject(emailDTO.getAssunto());
        helper.setFrom(remetente, empresa);

        // Corpo HTML
        helper.setText(getCorpoComPdf(emailDTO), true);

        // Anexo
        if (emailDTO.getFile() != null) {
            ByteArrayResource arquivo = new ByteArrayResource(emailDTO.getFile().toByteArray());
            helper.addAttachment("Relatorio_Gastos.pdf", arquivo);
        }

        mailSender.send(mimeMessage);
    }

    private String getCorpo(EmailDTO emailDTO) {
        String nomeUser = emailDTO.getNomeUsuario();
        String descricao = emailDTO.getDescricao();
        String valor = emailDTO.getValor().toString();
        String dataVencimento = Utils.getDataFormatada(emailDTO.getVencimento(), false);

        return String.format(
                """
                        <!DOCTYPE html>
                        <html>
                        <head>
                          <meta charset="UTF-8">
                        </head>
                        <body style="font-family: Arial, sans-serif; font-size: 16px; color: #000;">
                          <br>
                              <p>Olá <strong>Sr.(a) %s</strong>,</p>
                              <p>Segue abaixo os detalhes da sua fatura:</p>
                              <p>📄 Fatura: <strong style="font-size: 18px;">%s</strong></p>
                              <p>💰 Valor: R$ %s</p>
                              <p>📅 Vencimento: %s</p>
                          <br>
                              <p>Por favor, verifique as informações até a data de vencimento para evitar encargos adicionais.</p>
                        </body>
                        </html>
                        """,
                nomeUser, descricao, valor, dataVencimento);
    }

    private String getCorpoComPdf(EmailCreatedRequestDTO emailDTO) {

        String nomeUser = emailDTO.getNomeUsuario();
        String periodo = retornarPeriodo(emailDTO.getFilters());

        return String.format(
                """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8">
                </head>
                <body style="font-family: Arial, sans-serif; font-size: 16px; color: #000;">
                  <br>
                  <p>Olá <strong>Sr.(a) %s</strong>,</p>
                  <p>Segue em anexo o relatório dos gastos.</p>
                  <p>
                    📅 Período:
                    <strong style="font-size: 18px;">%s</strong>
                  </p><br>
                  <p>
                    Caso tenha alguma dúvida, entre em contato.
                  </p>
    
                  </body>
                  </html>
                """,
                nomeUser,
                periodo
        );
    }}
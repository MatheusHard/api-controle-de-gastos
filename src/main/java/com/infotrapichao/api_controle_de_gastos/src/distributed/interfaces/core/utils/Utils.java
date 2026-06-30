package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
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
}

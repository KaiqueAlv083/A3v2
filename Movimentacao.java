package A3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {

    private LocalDateTime data;
    private String tipo;
    private double valor;
    private double saldoApos;

    public Movimentacao(String tipo, double valor, double saldoApos) {
        this.data = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.saldoApos = saldoApos;
    }

    public String formatar() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s de R$ %.2f — Saldo após: R$ %.2f",
                data.format(fmt), tipo, valor, saldoApos);
    }
}
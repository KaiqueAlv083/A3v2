package A3;

import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

    private double saldo = 0.0;
    private List<Movimentacao> extrato = new ArrayList<>();

    public double getSaldo() {
        return saldo;
    }

    public List<Movimentacao> getExtrato() {
        return extrato;
    }

    public void depositar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido.");

        saldo += valor;
        extrato.add(new Movimentacao("Depósito", valor, saldo));
    }

    public void sacar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido.");

        if (valor > saldo)
            throw new IllegalArgumentException("Saldo insuficiente.");

        saldo -= valor;
        extrato.add(new Movimentacao("Saque", valor, saldo));
    }
}
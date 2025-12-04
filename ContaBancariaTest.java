package test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import A3.ContaBancaria;
import A3.Movimentacao;

public class ContaBancariaTest {

    private ContaBancaria conta;

    @Before
    public void setUp() {
        conta = new ContaBancaria();
    }

    // ----------------------------
    // TESTE: Depósito válido
    // ----------------------------
    @Test
    public void testDepositoValido() {
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo(), 0.001);
    }

    // ----------------------------
    // TESTE: Depósito inválido
    // ----------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testDepositoInvalido() {
        conta.depositar(-50.0);
    }

    // ----------------------------
    // TESTE: Saque válido
    // ----------------------------
    @Test
    public void testSaqueValido() {
        conta.depositar(200.0);
        conta.sacar(50.0);
        assertEquals(150.0, conta.getSaldo(), 0.001);
    }

    // ----------------------------
    // TESTE: Saque maior que saldo
    // ----------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testSaqueSaldoInsuficiente() {
        conta.depositar(100.0);
        conta.sacar(150.0); // deve lançar exceção
    }

    // ----------------------------
    // TESTE: Saque inválido
    // ----------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testSaqueInvalido() {
        conta.sacar(-20.0);
    }

    // ----------------------------
    // TESTE: Registro de movimentações
    // ----------------------------
    @Test
    public void testRegistroMovimentacoes() {
        conta.depositar(50.0);
        conta.sacar(20.0);

        assertEquals(2, conta.getExtrato().size());
        assertTrue(conta.getExtrato().get(0) instanceof Movimentacao);
        assertTrue(conta.getExtrato().get(1) instanceof Movimentacao);
    }
}
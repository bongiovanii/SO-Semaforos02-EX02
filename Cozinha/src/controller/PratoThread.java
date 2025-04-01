package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PratoThread extends Thread {

	 private static final Random random = new Random();
	    private static final Semaphore semaforoEntrega = new Semaphore(1);
	    private int id;
	    private String nome;
	    private int tempoCozimento;

	    public PratoThread(int id) {
	        this.id = id;
	        if (id % 2 == 1) {
	            this.nome = "Sopa de Cebola";
	            this.tempoCozimento = random.nextInt(300) + 500;
	        } else {
	            this.nome = "Lasanha à Bolonhesa";
	            this.tempoCozimento = random.nextInt(600) + 600;
	        }
	    }

	    @Override
	    public void run() {
	        try {
	            System.out.println("Prato " + id + " - " + nome + " iniciando cozimento.");
	            int tempoDecorrido = 0;
	            while (tempoDecorrido < tempoCozimento) {
	                Thread.sleep(100);
	                tempoDecorrido += 100;
	                int percentual = (int) ((tempoDecorrido / (double) tempoCozimento) * 100);
	                System.out.println("Prato " + id + " - " + nome + " cozimento: " + percentual + "%");
	            }
	            System.out.println("Prato " + id + " - " + nome + " pronto!");
	            entregarPrato();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    private void entregarPrato() throws InterruptedException {
	        semaforoEntrega.acquire();
	        System.out.println("Prato " + id + " - " + nome + " sendo entregue.");
	        Thread.sleep(500);
	        System.out.println("Prato " + id + " - " + nome + " entregue com sucesso!");
	        semaforoEntrega.release();
	    }
}

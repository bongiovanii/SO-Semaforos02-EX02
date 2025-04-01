package view;

import controller.PratoThread;

public class Cozinha {

	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
            new PratoThread(i).start();
        }
	}

}

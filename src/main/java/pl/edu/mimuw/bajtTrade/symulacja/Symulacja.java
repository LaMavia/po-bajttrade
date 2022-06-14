package pl.edu.mimuw.bajtTrade.symulacja;

import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;

public class Symulacja {
  private Historia historia;
  private final int długość;

  public Symulacja(int długość_, OpisDnia dzień0) {
    długość = długość_;
    historia = new Historia();

    historia.zapiszDzień(dzień0.ustawDzień(0));
  }

  public void rozpocznij() {

  }
}

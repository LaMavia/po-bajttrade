package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;

public interface StrategiaZajęcia {
  public boolean czySięUczy(Robotnik robotnik, int numerDnia, Historia historia);
}

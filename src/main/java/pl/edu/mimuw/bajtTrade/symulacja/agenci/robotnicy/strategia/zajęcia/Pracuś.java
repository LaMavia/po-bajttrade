package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;

public class Pracuś implements StrategiaZajęcia {

  @Override
  public boolean czySięUczy(Robotnik robotnik, int numerDnia, Historia historia) {
    return false;
  }

}

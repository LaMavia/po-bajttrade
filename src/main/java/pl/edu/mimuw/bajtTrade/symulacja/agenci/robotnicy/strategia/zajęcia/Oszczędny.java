package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;

public class Oszczędny implements StrategiaZajęcia {
  private final double limitDiamentów;

  public Oszczędny(double limitDiamentów_) {
    limitDiamentów = limitDiamentów_;
  }

  @Override
  public boolean czySięUczy(Robotnik robotnik, int numerDnia, Historia historia) {
    return false;
  }
  
}

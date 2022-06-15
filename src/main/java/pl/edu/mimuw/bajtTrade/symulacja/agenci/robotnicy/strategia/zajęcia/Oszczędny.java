package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbIlościowy;

public class Oszczędny implements StrategiaZajęcia {
  private final double limitDiamentów;

  public Oszczędny(double limitDiamentów_) {
    limitDiamentów = limitDiamentów_;
  }

  @Override
  public boolean czySięUczy(Robotnik robotnik, Hashtable<TypyZasobów, Zasób> zasoby, int numerDnia, Historia historia) {
    return ((ZasóbIlościowy) zasoby.get(TypyZasobów.Diamenty)).ilość() > limitDiamentów;
  }

}

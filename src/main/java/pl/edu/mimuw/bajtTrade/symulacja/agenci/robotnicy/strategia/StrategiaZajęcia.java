package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public interface StrategiaZajęcia {
  public boolean czySięUczy(Robotnik robotnik, Hashtable<TypyZasobów, Zasób> zasoby, int numerDnia, Historia historia);
}

package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public class Okresowy implements StrategiaZajęcia {
  private int okresowośćNauki;

  public Okresowy(int okresowośćNauki) {
    this.okresowośćNauki = okresowośćNauki;
  }

  @Override
  public boolean czySięUczy(Robotnik robotnik, Hashtable<TypyZasobów, Zasób> zasoby, int numerDnia, Historia historia) {
    return numerDnia % okresowośćNauki == 0;
  }

}

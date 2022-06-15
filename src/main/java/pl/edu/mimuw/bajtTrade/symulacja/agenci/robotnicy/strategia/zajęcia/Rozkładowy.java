package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import java.util.Hashtable;
import java.util.Random;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public class Rozkładowy implements StrategiaZajęcia {

  public Rozkładowy() {
  }

  @Override
  public boolean czySięUczy(Robotnik robotnik, Hashtable<TypyZasobów, Zasób> zasoby, int numerDnia, Historia historia) {
    return new Random().nextDouble() <= 1. / (numerDnia + 3.);
  }

}

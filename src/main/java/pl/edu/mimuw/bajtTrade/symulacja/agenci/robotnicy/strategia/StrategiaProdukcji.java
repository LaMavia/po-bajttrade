package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia;

import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public interface StrategiaProdukcji {
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia, Map<TypyZasobów, Integer> premie);
}

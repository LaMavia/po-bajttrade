package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.produkcji;

import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Krótkowzroczny implements StrategiaProdukcji {

  @Override
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia, Map<TypyZasobów, Integer> premie) {
    return historia.dajDzień(numerDnia - 1).dajMaxCenęŚrednią().p();
  }

}

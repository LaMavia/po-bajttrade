package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.produkcji;

import java.util.Map;
import java.util.Random;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Losowy implements StrategiaProdukcji {

  @Override
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia,
      Map<TypyZasobów, Integer> premie) {
    TypyZasobów[] losowalneTypy = new TypyZasobów[] {};

    premie.keySet().toArray(losowalneTypy);

    return losowalneTypy[new Random().nextInt(losowalneTypy.length)];
  }

}

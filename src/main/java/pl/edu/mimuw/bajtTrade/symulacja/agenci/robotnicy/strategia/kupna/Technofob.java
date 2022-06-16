package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.kupna;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class Technofob implements StrategiaKupna {

  @Override
  public List<OfertaKupnaRobotnika> coKupić(Robotnik robotnik, Map<TypyZasobów, Zasób> zasoby,
      double liczbaWyprodukowanychPrzedmiotów) {
    List<OfertaKupnaRobotnika> oferty = new ArrayList<>();

    oferty.add(new OfertaKupnaRobotnika(robotnik, Produkt.jedzenie(100)));

    return oferty;
  }
  
}

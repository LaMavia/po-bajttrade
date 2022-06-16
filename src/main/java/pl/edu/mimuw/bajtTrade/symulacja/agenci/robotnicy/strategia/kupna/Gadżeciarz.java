package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.kupna;

import java.util.List;
import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ProduktPoziomowy;

public class Gadżeciarz extends Zmechanizowany {

  public Gadżeciarz(int liczbaNarzędzi) {
    super(liczbaNarzędzi);
  }

  @Override
  public List<OfertaKupnaRobotnika> coKupić(Robotnik robotnik, Map<TypyZasobów, Zasób> zasoby,
      double liczbaWyprodukowanychPrzedmiotów) {
    List<OfertaKupnaRobotnika> oferty = super.coKupić(robotnik, zasoby, liczbaWyprodukowanychPrzedmiotów);

    oferty.add(
        new OfertaKupnaRobotnika(robotnik, ProduktPoziomowy.programyKomputerowe(liczbaWyprodukowanychPrzedmiotów, 1)));

    return oferty;
  }

}

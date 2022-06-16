package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia;

import java.util.List;
import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public interface StrategiaKupna {
  public List<OfertaKupnaRobotnika> coKupić(Robotnik robotnik, Map<TypyZasobów, Zasób> zasoby,
      double liczbaWyprodukowanychPrzedmiotów);
}

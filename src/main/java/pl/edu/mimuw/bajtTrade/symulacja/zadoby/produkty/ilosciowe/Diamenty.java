package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ilosciowe;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class Diamenty extends Produkt {

  public Diamenty(double ilość) {
    super(TypyZasobów.Diamenty, ilość);
  }

  @Override
  public void zaaplikujDo(Robotnik robotnik, Historia historia) {
  }
}

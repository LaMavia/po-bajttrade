package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class ZasóbIlościowy extends Zasób {
  private int ilość = 0;

  public ZasóbIlościowy(Agent właściciel_, TypyZasobów typ_) {
    super(właściciel_, typ_);
  }

  @Override
  protected void dodajBezpieczny(Produkt p) {
    ilość += p.ilość();
  }

  @Override
  protected void użyjBezpieczny(Produkt p, Historia historia) {
    p.zaaplikujDo(właściciel, historia);
  }

  
}

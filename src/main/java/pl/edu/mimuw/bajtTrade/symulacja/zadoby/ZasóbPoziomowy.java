package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class ZasóbPoziomowy extends Zasób {

  public ZasóbPoziomowy(Agent właściciel_, TypyZasobów typ_) {
    super(właściciel_, typ_);
    //TODO Auto-generated constructor stub
  }

  @Override
  protected void dodajBezpieczny(Produkt p) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void użyjBezpieczny(Produkt p, Historia historia) {
    // TODO Auto-generated method stub
    return;
  }
  
}

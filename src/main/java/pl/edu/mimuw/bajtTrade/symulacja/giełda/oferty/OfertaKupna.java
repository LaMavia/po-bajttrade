package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class OfertaKupna extends Oferta {

  protected OfertaKupna(Agent wystawiający_, int ilość_, TypyZasobów typ_) {
    super(wystawiający_, ilość_, typ_);
    //TODO Auto-generated constructor stub
  }

  @Override
  public void wypełnij(Oferta ofertaKomplementacyjna) {
    int przemienione = ofertaKomplementacyjna.odejmij(ilość);

    odejmij(przemienione);
    wystawiający.daj(typ, ofertaKomplementacyjna.poziom());
  }
}

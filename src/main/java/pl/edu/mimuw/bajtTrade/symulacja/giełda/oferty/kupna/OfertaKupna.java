package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class OfertaKupna extends Oferta {

  protected OfertaKupna(Agent wystawiający_, Produkt produkt_) {
    super(wystawiający_, produkt_);
  }

  @Override
  public void wypełnij(Oferta ofertaKomplementacyjna) {
    int przemienione = ofertaKomplementacyjna.odejmij(produkt.ilość());

    odejmij(przemienione);
    wystawiający.daj(produkt);
  }
}

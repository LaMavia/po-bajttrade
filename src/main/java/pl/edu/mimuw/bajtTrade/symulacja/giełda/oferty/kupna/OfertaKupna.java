package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class OfertaKupna<W extends Agent, K extends Agent> extends Oferta<W, K> {

  protected OfertaKupna(W wystawiający_, Produkt produkt_) {
    super(wystawiający_, produkt_);
  }
}

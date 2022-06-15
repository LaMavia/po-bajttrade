package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class OfertaSprzedaży<W extends Agent, K extends Agent> extends Oferta<W, K> {

  protected OfertaSprzedaży(W wystawiający_, Produkt produkt_) {
    super(wystawiający_, produkt_);
    //TODO Auto-generated constructor stub
  }

  @Override
  public void wypełnij(Oferta<K, W> ofertaKomplementacyjna) {
    // TODO Auto-generated method stub
    
  }
  
}

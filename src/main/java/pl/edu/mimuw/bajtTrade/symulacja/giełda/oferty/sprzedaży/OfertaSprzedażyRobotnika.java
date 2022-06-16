package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class OfertaSprzedażyRobotnika extends OfertaSprzedaży<Robotnik, Spekulant> {

  public OfertaSprzedażyRobotnika(Robotnik wystawiający_, Produkt produkt_) {
    super(wystawiający_, produkt_);
    //TODO Auto-generated constructor stub
  }
  
  @Override
  public void wypełnij(Oferta<Spekulant, Robotnik> ofertaKomplementacyjna) {
    ofertaKomplementacyjna.wypełnij(this);
  }
}

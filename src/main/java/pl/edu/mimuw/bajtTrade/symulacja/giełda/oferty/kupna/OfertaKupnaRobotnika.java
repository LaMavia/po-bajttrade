package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class OfertaKupnaRobotnika extends OfertaKupna<Robotnik, Spekulant> {
  public OfertaKupnaRobotnika(Robotnik wystawiający_, Produkt produkt_) {
    super(wystawiający_, produkt_);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void wypełnij(Oferta<Spekulant, Robotnik> ofertaKomplementacyjna) {
    ofertaKomplementacyjna.wypełnij(this);
  }
}

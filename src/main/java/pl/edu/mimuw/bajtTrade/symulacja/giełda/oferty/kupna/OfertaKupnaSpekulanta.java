package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class OfertaKupnaSpekulanta extends OfertaKupna<Spekulant, Robotnik> {
  private final double cenaZaSztukę;

  protected OfertaKupnaSpekulanta(Spekulant wystawiający_, Produkt produkt_, double cenaZaSztukę_) {
    super(wystawiający_, produkt_);
    cenaZaSztukę = cenaZaSztukę_;
  }

  public double cenaZaSztukę() {
    return cenaZaSztukę;
  }

  @Override
  public int compareTo(Oferta<Spekulant, Robotnik> inna) {
    int c = super.compareTo(inna);

    if (c != 0 || !(inna instanceof OfertaKupnaSpekulanta)) {
      return c;
    }

    return (int) Math.signum(((OfertaKupnaSpekulanta) inna).cenaZaSztukę() - cenaZaSztukę());
  }

  @Override
  public void wypełnij(Oferta<Robotnik, Spekulant> ofertaKomplementacyjna) {
    super.wypełnij(ofertaKomplementacyjna);

    
  }
}

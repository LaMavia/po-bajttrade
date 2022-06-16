package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Ocenowana;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class OfertaSprzedażySpekulanta extends OfertaSprzedaży<Spekulant, Robotnik> implements Ocenowana {
  private final double cenaZaSztukę;

  public OfertaSprzedażySpekulanta(Spekulant wystawiający_, Produkt produkt_, double cenaZaSztukę_) {
    super(wystawiający_, produkt_);
    cenaZaSztukę = cenaZaSztukę_;
  }

  @Override
  public double cenaZaSztukę() {
    return cenaZaSztukę;
  }

  @Override
  public int compareTo(Oferta<Spekulant, Robotnik> inna) {
    int c = super.compareTo(inna);

    if (c != 0 || !(inna instanceof OfertaSprzedażySpekulanta)) {
      return c;
    }

    return (int) Math.signum(cenaZaSztukę() - ((OfertaSprzedażySpekulanta) inna).cenaZaSztukę());
  }
}

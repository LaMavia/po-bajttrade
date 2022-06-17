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

    c = (int) Math.signum(cenaZaSztukę() - ((OfertaSprzedażySpekulanta) inna).cenaZaSztukę());

    if (c != 0) {
      return c;
    }

    return wystawiający().id() - inna.wystawiający().id();
  }

  @Override
  public void wypełnij(Oferta<Robotnik, Spekulant> ofertaKomplementacyjna) {
    double ileChciałobySięSprzedać = Math.min(ilość(), ofertaKomplementacyjna.ilość());
    double naIleStać = Math.floor(ofertaKomplementacyjna.wystawiający().wynik() / cenaZaSztukę());

    double doWymiany = Math.min(ileChciałobySięSprzedać, naIleStać);
    double doZapłaty = doWymiany * cenaZaSztukę();

    wystawiający.nagrodź(doZapłaty);
    ofertaKomplementacyjna.wystawiający().nagrodź(-doZapłaty);
  }
}

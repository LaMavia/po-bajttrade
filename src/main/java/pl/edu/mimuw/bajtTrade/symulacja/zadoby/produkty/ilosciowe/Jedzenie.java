package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ilosciowe;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class Jedzenie extends Produkt {

  public Jedzenie(int ilość) {
    super(TypyZasobów.Jedzenie, ilość);
  }

  @Override
  public void zaaplikujDo(Robotnik robotnik, Historia historia) {
    if (ilość < 100) {
      robotnik.dodajDzieńBezJedzenia();

      switch (robotnik.dniBezJedzenia()) {
        case 1:
          robotnik.dajPremię(-100);
          break;
        case 2:
          robotnik.dajPremię(-300);
          break;
      }
    } else {
      robotnik.zresetujDniBezJedzenia();
    }

    ilość = Math.max(0, ilość - 100);
  }

}

package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class ProduktPoziomowy extends Produkt {
  protected int poziom = 1;

  public ProduktPoziomowy(TypyZasobów typ, int ilość, int poziom_) {
    super(typ, ilość);

    poziom = poziom_;
  }
}

package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class Oferta {
  protected Produkt produkt;
  protected final Agent wystawiający;

  protected Oferta(Agent wystawiający_, Produkt produkt_) {
    wystawiający = wystawiający_;
    produkt = produkt_;
  }

  /**
   * @brief Metoda wypełniająca ofertę. Oferta może zostać wypełniona częściowo
   *        lub całkowicie. W celu sprawdzenia statusu wypełnienia, proszę użyć
   *        metody {@link #czyWypełniona() czyWypełniona()}
   * @param ofertaKomplementacyjna oferta typu przeciwnego
   *                               (kupna-sprzedaży/sprzedaży-kupna).
   */
  public abstract void wypełnij(Oferta ofertaKomplementacyjna);

  public boolean czyWypełniona() {
    return (produkt.ilość() == 0);
  }

  /**
   * @brief Odejmuje od oferty `docelowaIlość` ilości produktów. Jeśli
   *        `docelowaIlość > ilość`, to odejmuje tylko `ilość`; `docelowaIlość`
   *        wpw.
   * @param docelowaIlość ilość, którą próbujemy odjąć z oferty (mniej, jeśli
   *                      `docelowaIlość > ilość`).
   * @return Zwraca ilość odjętą z oferty.
   */
  public int odejmij(int docelowaIlość) {
    int faktycznieOdjęte = Math.min(produkt.ilość(), docelowaIlość);

    produkt.ilość -= faktycznieOdjęte;

    return faktycznieOdjęte;
  }

  public Agent wystawiający() {
    return wystawiający;
  }

  public int poziom() {
    return 1;
  };
  public double cenaZaSztukę() {
    return 0.0;
  };
}

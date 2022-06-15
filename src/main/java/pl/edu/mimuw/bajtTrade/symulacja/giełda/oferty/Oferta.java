package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class Oferta<W extends Agent, K extends Agent> implements Comparable<Oferta<W, K>> {
  protected Produkt produkt;
  protected final W wystawiający;

  protected Oferta(W wystawiający_, Produkt produkt_) {
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
  public abstract void wypełnij(Oferta<K, W> ofertaKomplementacyjna);

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

  public W wystawiający() {
    return wystawiający;
  }

  public int poziom() {
    return 1;
  };

  @Override
  public int compareTo(Oferta<W, K> inna) {
    return produkt.compareTo(inna.produkt);
  }
}

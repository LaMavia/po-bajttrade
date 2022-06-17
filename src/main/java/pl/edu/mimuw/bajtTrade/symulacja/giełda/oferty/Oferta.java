package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class Oferta<W extends Agent, K extends Agent> implements Comparable<Oferta<W, K>> {
  protected Produkt produkt;
  protected final double początkowaIlość;
  protected final W wystawiający;

  protected Oferta(W wystawiający_, Produkt produkt_) {
    wystawiający = wystawiający_;
    produkt = produkt_;
    początkowaIlość = produkt_.ilość();
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
  public double odejmij(double docelowaIlość) {
    double faktycznieOdjęte = Math.min(produkt.ilość(), docelowaIlość);

    produkt.wydziel(faktycznieOdjęte);

    return faktycznieOdjęte;
  }

  public W wystawiający() {
    return wystawiający;
  }

  public int poziom() {
    return 1;
  };

  public TypyZasobów typ() {
    return produkt.typ();
  }

  public double ilość() {
    return produkt.ilość();
  }

  @Override
  public int compareTo(Oferta<W, K> inna) {
    int c = produkt.compareTo(inna.produkt);

    if (c != 0) return c;

    return wystawiający.id() - inna.wystawiający.id();
  }

  public double ilośćWypełnionych() {
    return początkowaIlość - ilość();
  }
}

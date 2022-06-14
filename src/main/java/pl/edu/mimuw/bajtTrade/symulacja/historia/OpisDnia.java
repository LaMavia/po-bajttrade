package pl.edu.mimuw.bajtTrade.symulacja.historia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class OpisDnia {
  private int dzień;
  private Hashtable<TypyZasobów, Double> cenyMin;
  private Hashtable<TypyZasobów, Double> cenyŚrednie;
  private Hashtable<TypyZasobów, Double> cenyMax;
  private Hashtable<TypyZasobów, Integer> wystawieniaSprzedaży;

  public OpisDnia(Hashtable<TypyZasobów, Double> ceny) {
    dzień = 0;
    cenyMin = ceny;
    cenyŚrednie = ceny;
    cenyMax = ceny;

    wystawieniaSprzedaży = new Hashtable<>();
    for (TypyZasobów typ : TypyZasobów.values()) {
      wystawieniaSprzedaży.put(typ, 0);
    }
  }

  public Double dajCenęŚrednią(TypyZasobów typ) {
    return this.cenyŚrednie.getOrDefault(typ, 0.);
  }

  public Double dajCenęMax(TypyZasobów typ) {
    return this.cenyMax.getOrDefault(typ, 0.);
  }

  public Double dajCenęMin(TypyZasobów typ) {
    return this.cenyMin.getOrDefault(typ, 0.);
  }

  public int ileWystawionoNaSprzedaż(TypyZasobów typ) {
    return this.wystawieniaSprzedaży.getOrDefault(typ, 0);
  }

  public OpisDnia ustawDzień(int d) {
    dzień = d;

    return this;
  }
}

package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import java.util.Iterator;
import java.util.List;

public class ZeznanieOfert {
  private final List<OfertaKupna> ofertyKupna;
  private final List<OfertaSprzedaży> ofertySprzedaży;

  public ZeznanieOfert(List<OfertaKupna> ofertyKupna_, List<OfertaSprzedaży> ofertySprzedaży_) {
    ofertyKupna = ofertyKupna_;
    ofertySprzedaży = ofertySprzedaży_;
  }

  public Iterator<OfertaKupna> ofertyKupna() {
    return ofertyKupna.iterator();
  }

  public Iterator<OfertaSprzedaży> ofertySprzedaży() {
    return ofertySprzedaży.iterator();
  }
}

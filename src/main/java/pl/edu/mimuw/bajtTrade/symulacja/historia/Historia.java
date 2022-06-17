package pl.edu.mimuw.bajtTrade.symulacja.historia;

import java.util.Hashtable;
import java.util.Map;

public class Historia {
  public class SkokWPrzyszłość extends RuntimeException {

    public SkokWPrzyszłość(int dzień) {
      super(String.format("Spróbowano zapytać o dzień %d, który jeszcze nie nastał. Obecny dzień: %d", dzień,
          obecnyDzień));
    }

  }

  private Map<Integer, OpisDnia> dni;
  private int obecnyDzień = 0;

  public Historia() {
    dni = new Hashtable<>();
  }

  public OpisDnia dajDzień(int numer) {
    if (numer <= 0) {
      return dni.get(0);
    }

    if (numer > obecnyDzień) {
      throw new SkokWPrzyszłość(numer);
    }

    return dni.get(numer);
  }

  public void zapiszDzień(OpisDnia opis) {
    this.dni.put(opis.dzień(), opis);

    if (opis.pełen())
      obecnyDzień++;
  }
}

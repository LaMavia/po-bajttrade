package pl.edu.mimuw.bajtTrade.symulacja.historia;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;

public class Historia {
  private List<OpisDnia> dni;
  private int obecnyDzień = 0;

  public Historia() {
    dni = new ArrayList<>();
  }

  public OpisDnia opiszDzień(Giełda giełda) {
    return null;
  }

  public OpisDnia dajDzień(int numer) {
    return null;
  }

  public List<OpisDnia> dajDni(int początek, int koniec) {
    return null;
  }

  public void zapiszDzień(OpisDnia opis) {
    this.dni.add(opis);
    
    obecnyDzień++;
  }
}

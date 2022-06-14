package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.OfertaSprzedaży;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Robotnik extends Agent {
  private Hashtable<TypyZasobów, Integer> produktywność;
  private int premia = 0;
  private int dniBezJedzenia = 0;
  private boolean żywy = true;

  public Robotnik(int id_, Hashtable<TypyZasobów, Integer> zasoby_,
      Hashtable<TypyZasobów, Integer> produktywność_) {
    super(id_, zasoby_);

    produktywność = produktywność_;
  }

  @Override
  public ZeznanieOfert rozegrajDzień(int numerDnia, Historia historia) {
    List<OfertaKupna> ofertyKupna = new ArrayList<>();
    List<OfertaSprzedaży> ofertySprzedaży = new ArrayList<>();

    if (żywy()) {
      
    }

    // wyczyść premię na koniec dnia
    premia = 0;

    // sprawdź, czy żyw, po karetkę nie dzwoń
    return new ZeznanieOfert(ofertyKupna, ofertySprzedaży);
  }

  public void zresetujDniBezJedzenia() {
    dniBezJedzenia = 0;
  }

  public void dodajDzieńBezJedzenia() {
    dniBezJedzenia++;

    if (dniBezJedzenia >= 3) {
      żywy = false;
    }
  }

  public int dniBezJedzenia() {
    return dniBezJedzenia;
  }

  public boolean żywy() {
    return żywy;
  }

  public void dajPremię(int premiaDoDodania) {
    premia += premiaDoDodania;
  }
}

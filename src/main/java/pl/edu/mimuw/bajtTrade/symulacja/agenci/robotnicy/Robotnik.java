package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedaży;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażyRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class Robotnik extends Agent {
  private Hashtable<TypyZasobów, Integer> produktywność;
  private int premia = 0;
  private int dniBezJedzenia = 0;
  private boolean żywy = true;
  private StrategiaZajęcia strategiaZajęcia;

  public Robotnik(int id_, Hashtable<TypyZasobów, Integer> zasoby_,
      Hashtable<TypyZasobów, Integer> produktywność_, StrategiaZajęcia strategiaZajęcia_) {
    super(id_, zasoby_);

    produktywność = produktywność_;
    strategiaZajęcia = strategiaZajęcia_;
  }

  @Override
  public ZeznanieOfertRobotnika rozegrajDzień(int numerDnia, Historia historia) {
    List<OfertaKupnaRobotnika> ofertyKupna = new ArrayList<>();
    List<OfertaSprzedażyRobotnika> ofertySprzedaży = new ArrayList<>();

    if (żywy()) {
      if (strategiaZajęcia.czySięUczy(this, zasoby, numerDnia, historia)) {
        zresetujDniBezJedzenia();

        // strategiaNauki.uczSie(...);
      } else {
        // for (Produkt p : strategiaProdukcji.produkuj(...)) {
        //   ofertySprzedaży.add(new OfertaSprzedaży(this, p));
        // }
        // 
        // ofertyKupna.addAll(strategiaKupna.coKupić());
      }
      /* if (strategiaZajęcia.czySieUczy()) {
        
      } else {
        
      } */
    }

    // wyczyść premię na koniec dnia
    premia = 0;

    // sprawdź, czy żyw, po karetkę nie dzwoń
    return new ZeznanieOfertRobotnika(this, ofertyKupna, ofertySprzedaży);
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

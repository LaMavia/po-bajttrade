package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Górnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Inżynier;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Kariera;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Programista;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Rolnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Rzemieślnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaNauki;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażyRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ProduktPoziomowy;

public class Robotnik extends Agent {
  private Hashtable<TypyZasobów, Integer> produktywność;
  private int dniBezJedzenia = 0;
  private boolean żywy = true;
  private StrategiaZajęcia strategiaZajęcia;
  private StrategiaNauki strategiaNauki;
  private StrategiaProdukcji strategiaProdukcji;
  private Hashtable<TypyZasobów, Kariera> kariery;
  private TypyZasobów obecnaKariera;

  public Robotnik(int id_, Hashtable<TypyZasobów, Integer> zasoby_,
      Hashtable<TypyZasobów, Integer> produktywność_, StrategiaZajęcia strategiaZajęcia_, TypyZasobów pierwszaKariera,
      StrategiaNauki strategiaNauki_, StrategiaProdukcji strategiaProdukcji_) {
    super(id_, zasoby_);

    produktywność = produktywność_;
    strategiaZajęcia = strategiaZajęcia_;
    strategiaProdukcji = strategiaProdukcji_;

    strategiaNauki = strategiaNauki_;

    kariery = new Hashtable<>();
    kariery.put(TypyZasobów.Jedzenie, new Rolnik());
    kariery.put(TypyZasobów.Diamenty, new Górnik());
    kariery.put(TypyZasobów.Ubrania, new Rzemieślnik());
    kariery.put(TypyZasobów.Narzędzia, new Inżynier());
    kariery.put(TypyZasobów.ProgramyKomputerowe, new Programista());

    obecnaKariera = pierwszaKariera;
  }

  private void zjedz(int ilość, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    // zużyj `ilość` jakiegoś jedzenia. Nadaje odpowiednie kary, jeśli głoduje.
    zasoby.get(TypyZasobów.Jedzenie).zużyj(ilość, this, premie, konfiguracja);
  }

  private void ubierzSię(int ilość, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    // zużywa `ilość` sztuk jakiś ubrań.
    // daje premię -kara_za_brak_ubrań%, jeśli `ilość > zasób.Ilość`.
    zasoby.get(TypyZasobów.Ubrania).zużyj(ilość, this, premie, konfiguracja);
  }

  private void wykorzystajNarzędzia(Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    // zużywa wszystkie narzędzia i daje premie.
    zasoby.get(TypyZasobów.Narzędzia).zużyj(this, premie, konfiguracja);
  }

  private List<Produkt> wykorzystajProgramy(Produkt produkt) {
    if (!(produkt instanceof ProduktPoziomowy)) {
      List<Produkt> wyjście = new ArrayList<>();
      wyjście.add(produkt);

      return wyjście;
    }

    return zasoby.get(TypyZasobów.ProgramyKomputerowe)
        .zużyj((prod, program) -> ((ProduktPoziomowy) prod.wydziel(program.ilość()))
            .ustawPoziom(program.poziom()), this, (ProduktPoziomowy) produkt);
  }

  @Override
  public ZeznanieOfertRobotnika rozegrajDzień(int numerDnia, Historia historia,
      KonfiguracjaSymulacji konfiguracjaSymulacji) {
    Map<TypyZasobów, Integer> premie = new Hashtable<>();
    List<OfertaKupnaRobotnika> ofertyKupna = new ArrayList<>();
    List<OfertaSprzedażyRobotnika> ofertySprzedaży = new ArrayList<>();

    if (żywy()) {
      if (strategiaZajęcia.czySięUczy(this, zasoby, numerDnia, historia)) {
        zresetujDniBezJedzenia();

        TypyZasobów nowaKariera = strategiaNauki.zadecydujOKarierze(this, kariery, historia, numerDnia);
        if (nowaKariera == obecnaKariera) {
          kariery.compute(obecnaKariera, (_t, k) -> {
            k.rozwiń();
            return k;
          });
        }

        obecnaKariera = nowaKariera;
      } else {
        zjedz(100, premie, konfiguracjaSymulacji);
        ubierzSię(100, premie, konfiguracjaSymulacji);
        wykorzystajNarzędzia(premie, konfiguracjaSymulacji);

        premie.compute(obecnaKariera, (k_, p) -> p + kariery.get(obecnaKariera).dajPremię());

        TypyZasobów typDoProdukcji = strategiaProdukcji.coProdukować(this, numerDnia, historia, produktywność);

        Produkt wyprodukowanyProdukt = produkuj(typDoProdukcji, premie.getOrDefault(typDoProdukcji, 0));

        if (wyprodukowanyProdukt.ilość() > 0 && Giełda.czyHandlowalny(typDoProdukcji)) {
          ofertySprzedaży.addAll(wykorzystajProgramy(wyprodukowanyProdukt).stream()
              .map((produkt) -> new OfertaSprzedażyRobotnika(this, produkt)).collect(Collectors.toList()));
        } else if (typDoProdukcji == TypyZasobów.Diamenty) {
          nagrodź(wyprodukowanyProdukt.ilość());
        }

        // for (Produkt p : strategiaProdukcji.produkuj(...)) {
        // ofertySprzedaży.add(new OfertaSprzedaży(this, p));
        // }
        //
        // ofertyKupna.addAll(strategiaKupna.coKupić());
      }
    }

    // wyczyść premię na koniec dnia

    // sprawdź, czy żyw, po karetkę nie dzwoń
    return new ZeznanieOfertRobotnika(this, ofertyKupna, ofertySprzedaży);
  }

  private Produkt produkuj(TypyZasobów typ, int premia) {
    int ilość = this.produktywność.get(typ) / 100 * (100 + premia);

    Produkt wyjście = null;

    if (ilość > 0) {
      switch (typ) {
        case Diamenty:
          wyjście = Produkt.diament(ilość);
          break;
        case Jedzenie:
          wyjście = Produkt.jedzenie(ilość);
          break;
        case Narzędzia:
          wyjście = ProduktPoziomowy.narzędzia(ilość, 1);
          break;
        case Ubrania:
          wyjście = ProduktPoziomowy.ubrania(ilość, 1);
          break;
        case ProgramyKomputerowe:
          wyjście = ProduktPoziomowy.programyKomputerowe(ilość, this.kariery.get(obecnaKariera).dajPoziomProgramu());
          break;
      }
    }

    return wyjście;
  };

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

  public TypyZasobów obecnaKariera() {
    return obecnaKariera;
  }
}

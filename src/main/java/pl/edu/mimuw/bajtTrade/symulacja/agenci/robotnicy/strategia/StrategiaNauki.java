package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Kariera;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public interface StrategiaNauki {
  public TypyZasobów zadecydujOKarierze(Robotnik robotnik, Hashtable<TypyZasobów, Kariera> kariery, Historia historia, int numerDnia);
}

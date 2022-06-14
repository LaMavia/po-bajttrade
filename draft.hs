data TypOferty = 
  | Kupna
  | Sprzedaży

data Oferta =
  OfertaRobotnika {  
    typ :: TypOferty;
    
  }
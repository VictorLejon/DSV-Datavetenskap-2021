//  Trad.h
//  labb4_tree


#ifndef TRAD_H
#define TRAD_H

#include <iostream>

template <typename T>
class Trad {
public:
  Trad() : rot(0) {};                    // default konstruktor
  Trad( T d ) { rot = new Nod<T>(d); };
  Trad( const Trad<T>& t ) { kopiera(t); };   // kopieringskonstruktor
  ~Trad() { delete rot; };
  
  bool tomt() const { return !rot; };
  T& varde() const
  {
    koll();
    return rot->data;
  };
  Trad<T>& v_barn() const
  {
    koll();
    return *rot->vanster;
  };
  Trad<T>& h_barn() const
  {
    koll();
    return *rot->hoger;
  };
  Trad<T>& operator= ( const Trad<T>& );
  bool operator== ( const Trad<T>& ) const;
  void skriv_ut() const;

  void satt_in(const T& );
  bool sok(const T& i);
  
private:
  
  template <typename Td> class Nod {
    friend class Trad;
    Td data;
    Trad *vanster, *hoger;
    Nod( Td d ) : data( d ), vanster( new Trad<T> ), hoger( new Trad<T> ) {};
    ~Nod()
    {
      delete vanster;
      delete hoger;
    };
  }; // end class Nod

  Nod<T> *rot;
  void koll() const
  {
    if ( tomt() )
      throw std::range_error( "Trad" );
  }

  void kopiera( const Trad<T>& t );
};

#endif /* Trad_h */

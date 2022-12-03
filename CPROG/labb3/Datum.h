// Victor Lejon
// Labb3, Datum.h – klassdefinition

#ifndef DATUM_H
#define DATUM_H

#include <array>
#include <iostream>

class Datum
{
  friend std::ostream &operator<<( std::ostream &, const Datum & );
  
  
  unsigned int year;
  unsigned int month;
  unsigned int day;
  
  // Deklaration av statisk privat medlem, "ant_dagar per månad"
  static const std::array< unsigned int, 13 > ANT_DAGAR_PER_MAANAD;

  static const std::array<std::string, 12> MAANADER;
  
public:
  Datum( int, int, int );
  Datum(); // Default konstruktor
  void set_date( int, int, int ); // set year, month, day
  
  
  static bool is_skott_aar( int ); // Är det skottår?
  bool end_of_month( int ) const; // Är dagen den sista i månaden?


  // Operators
  
  const Datum operator+(const int) const;
  const Datum& operator+=(const int); 

  // Prefix
  const Datum& operator++();

  // Postfix
  const Datum operator++(int);


  // Jämförelse operatorer
  bool operator==(const Datum&) const;
  bool operator!=(const Datum&) const;
  bool operator>(const Datum&) const;
  bool operator<(const Datum&) const;
  bool operator>=(const Datum&) const;
  bool operator<=(const Datum&) const;
  
private:
  void step_one_day(); // Öka datum med en dag
  friend Datum operator+(const int, Datum&);
};

#endif


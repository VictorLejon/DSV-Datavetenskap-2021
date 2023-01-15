//  Trad.cpp
//  labb4_tree

#include <iostream>
#include "Trad.h"

template <typename T>
void Trad<T>::kopiera( const Trad<T>& t )
{
  //std::cout << "*** Trad::kopiera\n";
  if (t.tomt())
    rot = 0;
  else
  {
    rot = new Nod( t.varde());
    v_barn().kopiera( t.v_barn());
    h_barn().kopiera( t.h_barn());
  }
}

template <typename T>
Trad<T>& Trad<T>::operator= (const Trad<T>& t)
{
  //std::cout << "*** Trad::operator=\n";
  if (rot != t.rot)
  {
    delete rot;
    kopiera(t);
  }
  return *this;
}

template <typename T>
bool Trad<T>::operator== (const Trad<T>& t) const
{
  //std::cout << "*** Trad::operator==\n";
  return (tomt() && t.tomt()) ||
          (!tomt() && !t.tomt() && varde() == t.varde() &&
           v_barn() == t.v_barn() && h_barn() == t.h_barn());
}

template <typename T>
void Trad<T>::skriv_ut() const
{
  // traversera igenom trädet rekursivt enligt principen "in-order"
  if (!tomt())
  {
    v_barn().skriv_ut();
    std::cout << varde() << ' ';
    h_barn().skriv_ut();
  }
}


template <typename T>
void Trad<T>::satt_in(const T& value)
{
  // If empty, set root
  if (tomt()){
    rot = new Nod<T>(value);
    return;
  }

  // if less than root
  if (value < varde()){
    rot->vanster->satt_in(value);
    return;
  }

  // if geq to root
  rot->hoger->satt_in(value);
}

template <typename T>
bool Trad<T>::sok(const T& i)
{
  
  // if empty return false
  if (tomt())
    return false;

  // if rot is value return true
  if (varde() == i)
    return true;

  // if less than rot search left
  if (i < varde())
    return rot->vanster->sok(i);

  // if greater or equal to search right
  return rot->hoger->sok(i);

  return false;
}

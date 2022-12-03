// Victor Lejon
// Labb3, Datum.cpp – definitioner av icke-triviala medlemsfunktioner

#include <iostream>
#include <string>
#include "Datum.h"

// Initialisera statisk medlem
const std::array< unsigned int, 13 > Datum::ANT_DAGAR_PER_MAANAD = { 0,31,28,31,30,31,30,31,31,30,31,30,31 };
// (första elementet används inte i denna lösning!)

const std::array< std::string , 12 > Datum::MAANADER = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };


// Default konstruktor
Datum::Datum()
{
  set_date(2000, 1, 1);
}

// Konstruktor
Datum::Datum( int year, int month, int day  )
{
  set_date( year, month, day);
}

void Datum::set_date( int yy, int mm, int dd )
{
  // Glöm inte att kontrollera inmatade värden, annars "exception"
  year = yy;
  month = mm;
  day = dd;
}

// Denna medlemsfunktion är tänkt att lägga till 1 dag till befintligt datum.
// Om sista dagen i månaden, så byt månad.
//     Om skottår, så ta hänsyn till att februari(månad 2) har 29 dagar.
// Om sista dagen på året, så byt år.
void Datum::step_one_day()
{
  // Dagen är inte sista dagen i månaden!
  if ( !end_of_month( day ) )
    ++day;
  else
    if ( month < 12 )
    {
      ++month;
      day = 1;
    }
    else
    {
      ++year;
      month = 1;
      day = 1;
    }
}

// Returnera true om yy(year) är skottår!
bool Datum::is_skott_aar( int yy )
{
  if ( yy % 400 == 0 ||
      ( yy % 100 != 0 && yy % 4 == 0 ) )
    return true;
  else
    return false;
}

// Är det sista dagen (dd) i månaden?
bool Datum::end_of_month( int dd ) const
{
  if ( month == 2 && is_skott_aar( year ) )
    return dd == 29;
  else
    return dd == ANT_DAGAR_PER_MAANAD[ month ];
}

// operator<<
std::ostream &operator<<( std::ostream &output, const Datum &d )
{
  // OBS. Glöm inte att modifiera vad som skrivs ut!
  output << d.day << ' ' << Datum::MAANADER[d.month-1] << ' ' << d.year;
  return output;
}

// operator +
Datum operator+(const int n, Datum& date)
{
  Datum temp(date);
  for (int i = 0; i < n; i++)
  {
    temp.step_one_day();
  }
  return temp;
}

// operator +
const Datum Datum::operator+(const int n) const
{
  Datum temp(*this);
  for (int i = 0; i < n; i++)
  {
      temp.step_one_day();
  }
  return temp;
}

// operator +=
const Datum& Datum::operator+=(const int n)
{
  for (int i = 0; i < n; i++)
  {
    this->step_one_day();
  }
  return *this;
}

// operator ++ (prefix)
const Datum& Datum::operator++()
{
  return *this += 1;
}

// operator ++ (postfix)
const Datum Datum::operator++(int)
{
  Datum temp(*this);
  *this += 1;
  return temp;
}

// operator ==
bool Datum::operator==(const Datum& other) const
{
  return (year == other.year && month == other.month && day == other.day);
}

// operator !=
bool Datum::operator!=(const Datum& other) const
{
  return (year != other.year || month != other.month || day != other.day);
}

// operator >
bool Datum::operator>(const Datum& other) const
{
  if (year > other.year)
    return true;

  if (month > other.month)
    return true;
  
  if (day > other.month)
    return true;

  return false;
}

// operator <
bool Datum::operator<(const Datum& other) const
{
  return !(*this > other);
}

// operator >=
bool Datum::operator>=(const Datum& other) const
{
  return (*this > other || *this == other);
}

// operator <=
bool Datum::operator<=(const Datum& other) const
{
  return (*this < other || *this == other);
}
#include "Pos.h"



// Default kosntruktion
Pos::Pos():_x(0), _y(0){
    
}

// Copy-konstruktor
Pos::Pos(const Pos& other):_x(other.get_x()), _y(other.get_y()){

} 

// Konstruktion
Pos::Pos(float x, float y):_x(x), _y(y){
    
}
        
void Pos::set(float x, float y){
    this->_x = x;
    this->_y = y;
}

float Pos::get_x() const{
    return this->_x;
}

float Pos::get_y() const{
    return this->_y;
}
        
// Operators
        
// Addition
const Pos Pos::operator+(const Pos& other) const{
    Pos temp(*this);
    temp._x += other.get_x();
    temp._y += other.get_y();
    
    return temp;
}

const Pos& Pos::operator+=(const Pos& other){
    this->_x += other.get_x();
    this->_y += other.get_y();
    return *this;
}


const Pos Pos::operator+(const float n) const{
    Pos temp(*this);
    temp._x += n;
    temp._y += n;
    return temp;
}

const Pos& Pos::operator+=(const float n){
    this->_x += n;
    this->_y += n;
    return *this;
}
        
// Subtraktion
const Pos Pos::operator-(const Pos& other) const{
    Pos temp(*this);
    temp._x -= other.get_x();
    temp._y -= other.get_y();
    return temp;
}

const Pos Pos::operator-=(const Pos& other){
    this->_x -= other.get_x();
    this->_y -= other.get_y();
    return *this;
}

const Pos Pos::operator-(const float n) const{
    Pos temp(*this);
    temp._x -= n;
    temp._y -= n;
    return temp;
}

const Pos Pos::operator-=(const float n){
    this->_x -= n;
    this->_y -= n;
    return *this;
}

// Multiplikation
const Pos Pos::operator*(const Pos& other) const{
    Pos temp(*this);
    temp._x *= other.get_x();
    temp._y *= other.get_y();
    return temp;
}

const Pos Pos::operator*=(const Pos& other){
    this->_x *= other.get_x();
    this->_y *= other.get_y();
    return *this;
}

const Pos Pos::operator*(const float n) const{
    Pos temp(*this);
    temp._x *= n;
    temp._y *= n;
    return temp;
}

const Pos Pos::operator*=(const float n){
    this->_x *= n;
    this->_y *= n;
    return *this;
}

        
// Unära

// prefix ++
const Pos& Pos::operator++(){
    return *this+=1;
} 

// postfix ++
const Pos& Pos::operator++(int){
    Pos temp(*this);
    *this += 1;
    return temp;
} 

// prefix --
const Pos& Pos::operator--(){
    return *this-=1;
}

// postfix --
const Pos& Pos::operator--(int){
    Pos temp(*this);
    *this -= 1;
    return temp;
} 





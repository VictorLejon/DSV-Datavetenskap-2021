#ifndef POS_H
#define POS_H

#include <iostream>
#include <iomanip>

class Pos
{
    float _x;
    float _y;
    
    friend std::ostream &operator<<(std::ostream&, const Pos&);
    
    public:
        Pos(); // Default konstruktion
        Pos(const Pos&); // Copy-konstruktor
        
        Pos(float, float); // Konstruktion
        
        void set(float, float);
        
        float get_x() const;
        float get_y() const;
        
        // Operators
        
        // Addition
        const Pos operator+(const Pos&) const;
        const Pos& operator+=(const Pos&);
        
        const Pos operator+(const float) const;
        const Pos& operator+=(const float);
        
        // Subtraktion
        const Pos operator-(const Pos&) const;
        const Pos operator-=(const Pos&); 
        
        const Pos operator-(const float) const;
        const Pos operator-=(const float); 
        
        // Multiplikation
        const Pos operator*(const Pos&) const;
        const Pos operator*=(const Pos&);
        
        const Pos operator*(const float) const;
        const Pos operator*=(const float);
        
        // Unära
        const Pos& operator++(); // prefix
        const Pos& operator++(int); // postfix
        
        const Pos& operator--(); // prefix
        const Pos& operator--(int); // postfix

    private:
        // För operationer i "bakvänd" ordning
        friend Pos operator+(const float, Pos&);
        friend Pos operator-(const float, Pos&);
        friend Pos operator*(const float, Pos&);
        
    
};
#endif
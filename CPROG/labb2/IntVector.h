// Victor Lejon
// Labb2, IntVector.h – klassdefinition
#ifndef INTVECTOR_H
#include <initializer_list>
#include <ostream>
#define INTVECTOR_H


class IntVector
{
    public:
        
        IntVector(); // Default-konstruktor
        //IntVector(const IntVector& other); // Copy-konstruktor
        IntVector(IntVector&& other); // Move-konstruktor
        ~IntVector(); // Destruktor

        IntVector(std::initializer_list<int> il); // Initializer

        const IntVector& operator=(const IntVector& other); // Tilldelning
        int& operator[](int index) const; // Indexering

        int size() const;
        void push_back(int n);
        
    private:
        int length;
        int* iptr;
        friend std::ostream& operator<<(std::ostream& os, const IntVector& obj);

        
};

#endif
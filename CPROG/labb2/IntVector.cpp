// Victor Lejon
// Labb2, IntVector.cpp – definitioner av icke-triviala medlemsfunktioner
#include "IntVector.h"
using namespace std;

// Tom konstruktion
IntVector::IntVector():length(0), iptr(0){}

// Copy-konstruktor
IntVector::IntVector(const IntVector& other): length(other.length), iptr(new int[length])
{
    for (int i = 0; i < length; i++)
    {
        iptr[i] = other.iptr[i];
    }
    
}

// Move-konstruktor
IntVector::IntVector(IntVector&& other): length(other.length), iptr(other.iptr)
{
    other.iptr = nullptr;
    other.length = 0;
}

//Initializer
IntVector::IntVector(std::initializer_list<int> il): length(il.size()), iptr(new int[length])
{
    int index = 0;
    for(auto i: il)
    {
        iptr[index++] = i;
    }
}


// Tilldelning
const IntVector& IntVector::operator=(const IntVector& other)
{
    if (this != &other)
    {
        delete [] iptr;
        length = other.length;
        iptr = new int[length];
        for (int i = 0; i < length; i++)
        {
            iptr[i] = other.iptr[i];
        }
    }
    return *this;
} 

// Destruktor
IntVector::~IntVector()
{
    delete [] iptr;
} 


// Indexering
int& IntVector::operator[](int index) const
{
    return iptr[index];
}

int IntVector::size() const
{
    return length;
}

void IntVector::push_back(int n)
{

    // Create temp list
    IntVector temp(*this);
        
    // Create new list
    delete [] iptr;
    length++;
    iptr = new int[length];
    
    // Copy to new
    for (int i = 0; i < temp.size(); i++)
    {
        iptr[i] = temp.iptr[i];
    }

    // Add new address
    iptr[length-1] = n;
    
}

std::ostream& operator<<(std::ostream& os, const IntVector& obj)
{
    for (int i = 0; i < obj.size(); i++) 
        os << obj.iptr[i] << " ";
    return os;
}
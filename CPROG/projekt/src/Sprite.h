// Spelmotor
#ifndef SPRITE_H
#define SPRITE_H

#include <string>
#include <iostream>
#include <functional>
#include <map>
#include <cmath>
#include "SDL2/SDL_image.h"
#include "../include/Constants.h"

// Position struct
struct Pos{
    int _x, _y;
};

// Velocity struct
struct Vel{
    int xVel = 0; // 0 som standard värde
    int yVel = 0; 
};

// Size struct
struct Size{
    int width;
    int height;
};

class Sprite{


    public:
        std::string get_type(){return type;};
        std::string get_image_path(){return img_path;};
        Pos get_position(){return position;};
        Vel get_velocity(){return velocity;};
        Size get_size(){return size;};

        SDL_Texture* get_texture(){return tx;};

        void set_movement(Vel);
        void set_position(Pos);
        void set_size(Size);
        void set_tx(SDL_Texture*);

        // pure virtual metod, beteende hos sprites bestäms i subklasserna
        virtual void update() = 0;

        // KEYBOARD

        // Binds a key to a function, when mapping a member function it is needed to wrap it in a lambda call
        void keybind(std::string, std::function<void()>);

        // Run key event
        void run_key_event(std::string);


        // KOLLISION

        // Kollar om kollision har skett, returnera boolean körs vid gameengine update
        bool checkSpriteCollision(Sprite*);

        // Binder en kollisions funktion
        void collide(std::string, std::function<void()>);

        // Kör kollisions funktionen för sprite, om bunden
        void run_collide(std::string);

        // Kollision med vägg
        int checkBorderCollision(int, int) const; // Kollar om sprite är på kanten, returnerar 1, 2, -1, -2, 0 beroende på kanten som vidrörs


    protected:
        // Tillåt bara subklasser att konstruera
        Sprite(std::string type, std::string img_path, int width, int height, int _x, int _y):type(type), img_path(img_path), position({_x, _y}), size({width, height}){
            
        }
    
        void move(); // Moves sprite according to velocity, private for cases where staticly placed sprites are wanted. Therefore not needed to be called in update

    private:

        std::map<std::string, std::function<void()>> collision_functions;  // map med funktioner och vilken kollision de är mappade till
        std::map<std::string, std::function<void()>> keybind_functions; // map med funktioner och vilken tangent de är mappade till

        std::string type; // Namn på sprite
        std::string img_path; // Relativ path till bilden

        Size size; // Storlek av sprite,

        Pos position; // Position av sprite
        Vel velocity; // Velocity av sprite

        SDL_Texture* tx;

        Sprite(const Sprite* other) = delete; // Förbjud kopiering/tilldelning
        const Sprite& operator=(const Sprite* other) = delete;
};

#endif
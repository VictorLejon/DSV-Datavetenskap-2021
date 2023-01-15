// Spelmotor
#ifndef GAME_H
#define GAME_H

using namespace std;
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#include <SDL2/SDL_image.h>

#include "../include/Constants.h"
#include "TextureLoader.h"
#include "Sprite.h"

class GameEngine{

    public:
        GameEngine() {};
        ~GameEngine();

        int get_width(){return win_width;};
        int get_height(){return win_height;};

        // Methods for running the game engine
        void start(const char*, int, int, bool);
        void stop();
        bool running() { return isRunning; };
        void event_handler();
        void update();
        void render();
        
        void set_fps(int);
        
        // Sprite handling
        void add_sprite(Sprite* sprite);
        void remove_sprite(Sprite* sprite);

    private:

        int win_width;
        int win_height;

        void handle_keydown(SDL_Keycode&); // Triggered by keydown event

        bool isRunning;

        // Sprites
        std::vector<Sprite*> sprites;

        // SDL
        SDL_Window* win;
        SDL_Renderer *ren;

        // FPS
        Uint32 nextTick;
        int delay;
        int tickInterval = 33; // Standard fps = 30
};

#endif
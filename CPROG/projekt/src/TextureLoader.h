// Spelmotor
#ifndef TEXTURE_L_H
#define TEXTURE_L_H

#include "SDL2/SDL_image.h"
#include "../include/Constants.h"
#include <string>

// Class for loading texture to sprites, used so renderer is not needed in sprite
class TextureLoader{

    public:
        static SDL_Texture* load_texture(std::string img_path, SDL_Renderer* ren);

};

#endif
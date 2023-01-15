// Spelmotor
#include "TextureLoader.h"

SDL_Texture* TextureLoader::load_texture(std::string img_path, SDL_Renderer* ren){
    SDL_Surface* img = SDL_LoadBMP( (constants::gResPath + "images/" + img_path).c_str() );
    SDL_Texture* tx = SDL_CreateTextureFromSurface(ren, img);
    SDL_FreeSurface(img);
    return tx;
}
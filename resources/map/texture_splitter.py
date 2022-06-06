#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os
from PIL import Image
import numpy as np

W, H = 32, 32

def split(path):
    if not os.path.exists(path):
        print(f"File '{path}' not found")
        return
    
    dir_ = os.path.dirname(path)
    base = os.path.basename(path)
    base, ext = os.path.splitext(base)
    
    try:
        img = Image.open(path)
    
    except:
        print(f"Could not load '{path}'. Maybe its not an image")
        return
    
    a = np.array(img)
    w, h = a.shape[1]//W, a.shape[0]//H
    n = 0
    
    for y in range(h):
        for x in range(w):
            try:
                i = Image.fromarray(a[y*H:y*H+H, x*W:x*W+W])
                i.save(os.path.join(dir_, f"{base}_{x+y*w}{ext}"))
            
            except:
                print(f"Could not create texture {x+y*w}")
            
            else:
                n += 1
    
    img.close()
    
    print(f"Succesfully created {n} textures")
    
    if a.shape[1] % 32 or a.shape[0] % 32:
        print(f"The texture's shape was not a multiple of 32x32 ({a.shape[1]}x{a.shape[0]}). Only fully visible tiles have been extracted.")

if __name__ == "__main__":
    path = input("Path to tileset texture: ")
    
    split(path)
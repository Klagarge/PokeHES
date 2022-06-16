#!/usr/bin/env python
# -*- coding: utf-8 -*-
from PIL import Image
import numpy as np
import os

W, H = 32, 32

if __name__ == "__main__":
    path = input("Path: ")
    
    img = Image.open(path).convert()
    a = np.array(img)
    b = np.zeros((a.shape[1], a.shape[0], *a.shape[2:]), dtype=a.dtype)
    
    w, h = a.shape[1]//W, a.shape[0]//H
    
    for y in range(h):
        for x in range(w):
            b[x*W:x*W+W,y*H:y*H+H] = a[y*H:y*H+H,x*W:x*W+W]

    img.close()
    img = Image.fromarray(b)
    dirname, basename = os.path.dirname(path), os.path.basename(path)
    basename, ext = os.path.splitext(basename)
    img.save(os.path.join(dirname, basename+"_flipped"+ext))

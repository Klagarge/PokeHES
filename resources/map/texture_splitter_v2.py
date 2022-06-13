#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os
from PIL import Image
import numpy as np
import pygame

W, H = 32, 32

def pilToPygame(pilImage):
    return pygame.image.fromstring(
        pilImage.tobytes(), pilImage.size, pilImage.mode).convert_alpha()

def rect(p1, p2):
    x = min(p1[0], p2[0])
    y = min(p1[1], p2[1])
    w = max(p1[0], p2[0])-x + 1
    h = max(p1[1], p2[1])-y + 1
    
    return x,y,w,h

class Rect:
    def __init__(self, ox1, oy1, ox2, oy2):
        self.ox1, self.oy1 = min(ox1, ox2), min(oy1, oy2)
        self.ox2, self.oy2 = max(ox1, ox2), max(oy1, oy2)
        self.x, self.y, self.w, self.h = None, None, self.ox2-self.ox1+1, self.oy2-self.oy1+1
        self.hover = False
    
    def render(self, w, sx, sy, mouse, original=False):
        self.hover = False
        x, y = self.x, self.y
        if original:
            x, y = self.ox1, self.oy1
        
        color = (255,200,200)
        if x*W <= mouse[0] < x*W+self.w*W \
            and y*H <= mouse[1] < y*H+self.h*H:
            color = (0,255,0)
            self.hover = True
        
        pygame.draw.rect(w, color, [x*W-sx, y*H-sy, self.w*W, self.h*H], 2)
    
    @property
    def area(self):
        return self.w*self.h

class Editor:
    WIDTH = 600
    HEIGHT = 600
    SCROLL_SPEED = 10
    FPS = 30
    
    def __init__(self, path):
        self.running = False
        
        if not os.path.exists(path):
            print(f"File '{path}' not found")
            return
        
        self.path = path
        self.dir_ = os.path.dirname(path)
        self.base = os.path.basename(path)
        self.base, self.ext = os.path.splitext(self.base)
        
        try:
            img = Image.open(path)
        
        except:
            print(f"Could not load '{path}'. Maybe its not an image")
            return
        
        self.WIDTH = min(img.width, self.WIDTH)
        self.HEIGHT = min(img.height, self.HEIGHT)
        
        pygame.init()
        self.win = pygame.display.set_mode([self.WIDTH, self.HEIGHT], pygame.SRCALPHA)
        self.over = pygame.Surface([self.WIDTH, self.HEIGHT], pygame.SRCALPHA)
        self.clock = pygame.time.Clock()
        pygame.display.set_caption(f"Texture Splitter - {self.base}{self.ext}")
        
        self.img = pilToPygame(img)
        self.a = np.array(img)
        self.w, self.h = self.a.shape[1]//W, self.a.shape[0]//H
        self.w2, self.h2 = 0, 0
        
        img.close()
        
        if self.a.shape[1] % 32 or self.a.shape[0] % 32:
            print(f"The texture's shape is not a multiple of 32x32 ({self.a.shape[1]}x{self.a.shape[0]}).")
        
        self.running = True
        self.rects = []
        self.sel = (False, None)
        self.scroll_x, self.scroll_y = 0, 0
        self.moving = False
    
    def mainloop(self):
        while self.running:
            self.handle_events()
            self.render()
            self.clock.tick(self.FPS)
    
    def render(self):
        mouse = pygame.mouse.get_pos()
        mouse = (mouse[0]+self.scroll_x, mouse[1]+self.scroll_y)

        self.win.fill(0)
        self.over.fill((0,0,0,0))
        
        self.win.blit(self.img, (-self.scroll_x, -self.scroll_y))
        
        for r in self.rects:
            r.render(self.win, self.scroll_x, self.scroll_y, mouse, True)
        
        pygame.draw.rect(self.over, (255,255,255,50), [self.mx*W-self.scroll_x, self.my*H-self.scroll_y, W, H])
        
        if self.sel[0]:
            x,y,w,h = rect(self.sel[1], (self.mx, self.my))
            pygame.draw.rect(self.over, (255,255,255), [x*W-self.scroll_x, y*H-self.scroll_y, w*W, h*H], 1)

        self.win.blit(self.over, (0, 0))
        
        pygame.display.flip()
    
    def handle_events(self):
        events = pygame.event.get()
        mouse = pygame.mouse.get_pos()
        self.mx, self.my = (mouse[0]+self.scroll_x)//W, (mouse[1]+self.scroll_y)//H
        
        for event in events:
            if event.type == pygame.QUIT \
                or (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE):
                
                self.running = False
            
            elif event.type == pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    if not self.sel[0]:
                        self.sel = (True, (self.mx, self.my))
                
                elif event.button in [4, 5]:
                    speed = self.SCROLL_SPEED if event.button == 5 else -self.SCROLL_SPEED
                    if pygame.key.get_pressed()[pygame.K_LSHIFT]:
                        self.scroll_x += speed
                        
                    else:
                        self.scroll_y += speed
                    
                    self.clamp_scroll()
                
                elif event.button == 3:
                    self.rects = list(filter(lambda r: not r.hover, self.rects))
                
                elif event.button == 2:
                    self.moving = True
                
            elif event.type == pygame.MOUSEBUTTONUP:
                if event.button == 1:
                    if self.sel[0]:
                        self.rects.append(Rect(*self.sel[1], self.mx, self.my))
                        self.sel = (False, None)
                        self.organize()
                
                elif event.button == 2:
                    self.moving = False
            
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_s and event.mod & pygame.KMOD_CTRL:
                    self.save()
            
            elif event.type == pygame.MOUSEMOTION:
                if self.moving:
                    self.scroll_x -= event.rel[0]
                    self.scroll_y -= event.rel[1]
                    self.clamp_scroll()
    
    def clamp_scroll(self):
        self.scroll_x = min(self.a.shape[1]-self.WIDTH, self.scroll_x)
        self.scroll_x = max(0, self.scroll_x)
        self.scroll_y = min(self.a.shape[0]-self.HEIGHT, self.scroll_y)
        self.scroll_y = max(0, self.scroll_y)
    
    def organize(self):
        for rect in self.rects:
            rect.x = None
            rect.y = None
        
        self.rects = sorted(self.rects, key=lambda r: r.area, reverse=True)
        
        w, h = 0, 0
        a = np.zeros([h, w])
        
        for r in self.rects:
            placed = False
            best = (None, None)
            for y in range(h):
                for x in range(w):
                    if a[y:y+r.h, x:x+r.w].any():
                        continue
                    
                    d_area = max(x+r.w, w)*max(y+r.h, h)-w*h

                    if d_area > 0:
                        if best[0] is None or d_area < best[0]:
                            best = (d_area, (x,y))
                        
                        continue
                    
                    placed = True
                    best = (0, (x,y))
                    break
                            
                if placed:
                    break
            
            if best[0] is None:
                if h+r.h > w+r.w:
                    p = (w, 0)
                else:
                    p = (0, h)
                
                best = (r.w*r.h, p)
            
            if best[0] > 0:
                a2 = np.zeros([max(h,best[1][1]+r.h), max(w,best[1][0]+r.w)])
                a2[:h, :w] = a
                a = a2
                h, w = a.shape
            
            r.x, r.y = best[1]
            a[r.y:r.y+r.h, r.x:r.x+r.w] = 1
        
        self.w2, self.h2 = w, h

    def save(self):
        img2 = np.zeros([self.h2*H, self.w2*W, *self.a.shape[2:]], dtype=self.a.dtype)
        for r in self.rects:
            img2[r.y*H:(r.y+r.h)*H, r.x*W:(r.x+r.w)*W] = \
                self.a[r.oy1*H:(r.oy1+r.h)*H, r.ox1*W:(r.ox1+r.w)*W]

        path = os.path.join(self.dir_, f"{self.base}_modified{self.ext}")
        try:
            img2 = Image.fromarray(img2)
            img2.save(path)
        
        except:
            print("Could not save modified texture")
        
        else:
            print(f"Succesfully saved modified texture as {path}")

if __name__ == "__main__":
    path = input("Path to tileset texture: ")
    
    editor = Editor(path)
    editor.mainloop()
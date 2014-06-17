# -*- coding: utf-8 -*-
import os
def renameSuras():
    dir_ = "/home/loai/Desktop/mp3"
    # get files sorted by name
    files = [f for f in os.listdir(dir_) if os.path.isfile(os.path.join(dir_, f))]
    files.sort()
    # get suras names
    suras = open("/media/Data/Media/Audio/suras.txt")
    count = 1
    for f, line in zip(files, suras.readlines()):
        os.rename(os.path.join(dir_, f), os.path.join(dir_, str(count) + " " + line[:len(line) - 1] + ".mp3"))
        count += 1
def renameSurasOffset(start):
    dir_ = "/home/loai/Desktop/mp3"
    # get files sorted by name
    files = [f for f in os.listdir(dir_) if os.path.isfile(os.path.join(dir_, f))]
    files.sort()
    # get suras names
    suras = open("/media/Data/Media/Audio/suras.txt").readlines()
    suras = suras[start-1:]
    count = start
    for f, line in zip(files, suras):
        os.rename(os.path.join(dir_, f), os.path.join(dir_, str(count) + " " + line[:len(line) - 1] + ".mp3"))
        count += 1    
renameSurasOffset(59)

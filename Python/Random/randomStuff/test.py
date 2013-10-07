# -*- coding: utf-8 -*-
import os
def renameSuras():
    dir_ = "/media/Data/Media/Audio/Baset"
    # get files sorted by name
    files = [f for f in os.listdir(dir_) if os.path.isfile(os.path.join(dir_, f))]
    files.sort()
    # get suras names
    suras = open("/media/Data/Media/Audio/suras.txt")
    count = 1
    for f, line in zip(files, suras.readlines()):
        os.rename(os.path.join(dir_, f), os.path.join(dir_, str(count) +" "+ line[:len(line) - 1] + ".mp3"))
        count += 1
renameSuras()

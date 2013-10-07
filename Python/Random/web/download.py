import urllib
# Sheikh Hatem Awny sharh Nozhat Al Nazar

for i in range(8, 10):
    print "in file "+str(i)
    urllib.urlretrieve("http://download.media.islamway.net/lessons/hsharif/15-Nozhat/0" + str(i) + ".mp3", "/home/loai/Desktop/mp3/" + str(i) + ".mp3")


for i in range(10, 30):
    print "in file "+str(i)
    urllib.urlretrieve("http://download.media.islamway.net/lessons/hsharif/15-Nozhat/" + str(i) + ".mp3", "/home/loai/Desktop/mp3/" + str(i) + ".mp3")

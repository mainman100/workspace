import urllib
import urllib2
# Sheikh Hatem Awny sharh Nozhat Al Nazar

def hosary_1():
    for i in range(8, 10):
        print "in file " + str(i)
        urllib.urlretrieve("http://download.media.islamway.net/lessons/hsharif/15-Nozhat/0" + str(i) + ".mp3", "/home/loai/Desktop/mp3/" + str(i) + ".mp3")

def hosary_2():
    for i in range(10, 30):
        print "in file " + str(i)
        urllib.urlretrieve("http://download.media.islamway.net/lessons/hsharif/15-Nozhat/" + str(i) + ".mp3", "/home/loai/Desktop/mp3/" + str(i) + ".mp3")
 

def mohamed_abdel_aziz():
    for i in range(59, 78):
        print "downloading file {}".format(i)
        url = "https://ia700608.us.archive.org/10/items/Quran0001/"
        file_name = str(i).zfill(2)
        urllib.urlretrieve(url + file_name + ".mp3", "/home/loai/Desktop/mp3/" + file_name + ".mp3")
        
mohamed_abdel_aziz()
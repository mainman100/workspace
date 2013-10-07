from HTMLParser import HTMLParser
import urllib

class MyHTMLParser(HTMLParser):
    def handle_starttag(self, tag, attrs):
        pass
        attrs = dict(attrs)
        if tag == "a":
            print attrs["href"]
    def handle_endtag(self, tag):
        pass
    def handle_data(self, data):
        pass

def downloadAlteflWalBahr():
    downloadAlteflWalBahr.lastPercentage = -1
    def showProgress(count, blocksize, totalSize):
        currentPercentage = count * blocksize * 100 / totalSize
        if currentPercentage != downloadAlteflWalBahr.lastPercentage:
            print "{} %".format(currentPercentage)
            downloadAlteflWalBahr.lastPercentage = currentPercentage
    host = "http://www.nnw1.net/media/"
    url = "http://www.nnw1.net/media/?songs=art&art=100"
    href_prefix = "./go/?download=song"
    class AnasheedHTMLParser(HTMLParser):
        def __init__(self, *args, **kwargs):
            self.count = 0
            HTMLParser.__init__(self, *args, **kwargs)
        def handle_starttag(self, tag, attrs):
            attrs = dict(attrs)
            if tag == "a" and "href" in attrs and attrs["href"].startswith(href_prefix):
                self.count += 1
                print host + attrs["href"]
                urllib.urlretrieve(host + attrs["href"], "nasheed_{}.mp3".format(self.count), showProgress)
        def handle_endtag(self, tag):
            pass
        def handle_data(self, data):
            pass
    file = urllib.urlopen(url)
    read = file.read().decode("windows-1252")
    file.close()
    myHtmlParser = AnasheedHTMLParser().feed(read)
if __name__ == '__main__':
    downloadAlteflWalBahr()
   # url = "http://www.nnw1.net/media/go/?download=song&id=844"
    

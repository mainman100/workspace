from bs4 import BeautifulSoup
import urllib2
import urllib
import re, csv

def BeautifulSoupTutorial():
    html_doc = """
    <html><head><title>The Dormouse's story</title></head>
    
    <p class="title"><b>The Dormouse's story</b></p>
    
    <p class="story">Once upon a time there were three little sisters; and their names were
    <a href="http://example.com/elsie" class="sister" id="link1">Elsie</a>,
    <a href="http://example.com/lacie" class="sister" id="link2">Lacie</a> and
    <a href="http://example.com/tillie" class="sister" id="link3">Tillie</a>;
    and they lived at the bottom of a well.</p>
    
    <p class="story">...</p>
    
    """
    
    soup = BeautifulSoup(html_doc)
    print(soup.prettify())
    for link in soup.find_all('a'):
        print(link['href'])
    print(soup.get_text())
    
    
# BeautifulSoupTutorial()
def test1():
    url = "http://www.spafinder.com/all-spas/"
    file_ = open("temp.csv", "wb")
    file_.write("Name;Address\n")
    # get parameters
    i = 0
    while(i <= 10):
        """
        data = {"N":"0", "keywords":"laser", "locid":"-1", "No":str(i)}
        url_values = urllib.urlencode(data)
        # normally we will do this to send get data, but this site is special case
        full_url = url + "?" + url_values
        """
        full_url = url + "N=0&keywords=laser&locid=-1&No=" + str(i)
        f = urllib2.urlopen(full_url)
        soup = BeautifulSoup(f.read())
        divs = soup.find_all("div", class_="content")
        links = [div.find_all("a", class_="name")[0].string for div in divs]
        addresses = [" ".join(div.find_all("address")[0].string.split()) for div in divs]
        rows = ['"{}";"{}"\n'.format(link, address) for (link, address) in zip(links, addresses)]
        file_.writelines(rows)
        i += 20
    f.close()

test1()
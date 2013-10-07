import cookielib
from bs4 import BeautifulSoup
import urllib2
import urllib
import re, csv
from HTMLParser import HTMLParser
import __main__
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
    
    
def scraping_1():
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
def scraping_2():
    """
    get yahoo open questions
    """
    pass
    domain = "http://answers.yahoo.com"
    link = "/dir/index?link=open"
    while(True):
        url = domain + link
        html_data = urllib2.urlopen(url).read()
        soup = BeautifulSoup(html_data)
        questions = [li.h3.a.string for li in soup.find("ul", class_="questions").find_all("li") if li.h3]
        print "\n".join(questions)
        try:
            link = soup.find("div", class_="pagination").find("li", class_="next").a["href"]
        except AttributeError:  # there is no next link
            break    
def scraping_3():
    url = "http://www.studentenwerk-muenchen.de/en/accommodation/housing-referral-service/offers/"
    html_data = urllib2.urlopen(url).read()
    soup = BeautifulSoup(html_data)
    links = soup.table.tbody.find_all("a")
    domain = "http://www.studentenwerk-muenchen.de/"
    for link in links:
        url = domain + link.get("href")
        html_data = urllib2.urlopen(url).read()
        if not "female" in html_data:
            print link.string 
            
def get_Quran_suras_names():
    url = "http://ar.islamway.net/recitations/chapters"
    html_data = urllib2.urlopen(url).read()
    soup = BeautifulSoup(html_data)
    suras_links = soup.find_all("a", "large-text")
    print suras_links
def download_quran_baset():
    root = "http://download.quran.islamway.net/quran3/74/"
    for i in range(1, 115):
        name = str(i).zfill(3) + ".mp3"
        print name
        url = root + name
        urllib.urlretrieve(url, "/media/Data/Media/Audio/Baset/" + name)

    
def web_login():
    url = 'https://login.facebook.com/login.php'
    values = {'email' : 'loai1991@gmail.com',
              'pass' : '******', }
    
    data = urllib.urlencode(values)
    # cookies = cookielib.CookieJar()
    cookies = cookielib.FileCookieJar()
    opener = urllib2.build_opener(
        urllib2.HTTPRedirectHandler(),
        urllib2.HTTPHandler(debuglevel=0),
        urllib2.HTTPSHandler(debuglevel=0),
        urllib2.HTTPCookieProcessor(cookies))
    
    response = opener.open(url, data)
    the_page = response.read()
    f = open("/home/loai/Desktop/web.html", "w")
    f.write(the_page)
    f.close()
class MyHTMLParser(HTMLParser):
    def __init__(self):
        self.depth = 0
    def handle_starttag(self, tag, attrs):
        self.depth += 1
        attrs = dict(attrs)
    def handle_data(self, data):
        HTMLParser.handle_data(self, data)
    def handle_endtag(self, tag):
        self.depth -= 1
        HTMLParser.handle_endtag(self, tag)
        
if __name__ == "__main__":
    download_quran_baset()

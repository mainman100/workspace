import  csv
from bs4 import BeautifulSoup
def extract_and_save():
    f = open("data.csv")
    encoding = "mac-roman"
    reader = csv.reader(f)
    negative = open("negative.txt", "w")
    positive = open("positive.txt", "w")
    for row in reader: 
        soup = BeautifulSoup(row[2].decode("mac-roman"))
        all_ = soup.find_all("lxa_possible")
        strings = "\n".join([f.string.encode("utf-8") for f in all_])
        if len(strings) > 0:
            strings += "\n"
        if row[3] == "Negative":
            negative.write(strings)
        elif row[3] == "Positive":
            positive.write(strings)
    positive.close()
    negative.close()

if __name__ == "__main__":
    extract_and_save()
    

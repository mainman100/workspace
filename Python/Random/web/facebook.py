# -*- coding: utf-8 -*-
import urllib2, json

def download_awny_posts_and_comments():
    file_ = open("/home/loai/Desktop/awny.txt", "w")
    #you need to generate this every hour
    token = "CAACEdEose0cBABWjX8o250TODLPduE4PZBf2Gt5h5QfSWlZA5LU0ouiRZBksjnWLKWum2gQ55i1zwbR9RQSS36FOBywPw9fSzHmPgEyj75C8qqXngsLBPkbya1EJIF47HXfnEOnfdXX7mQe0dW9nPPz9voztopBzhJ9NeJaJhzhfsZB08URckJeFIh80r54gZBrVq1bxpFgZDZD"
    page_id = "137264138952"
    url = "https://graph.facebook.com/" + page_id + "/posts?limit=50&access_token=" + token
    print url
    count = 1
    posts = set()
    while True:
        f = urllib2.urlopen(url)   
        data = f.read()
        json_response = json.loads(data)
        if not "data" in json_response:
            break
        for data in json_response["data"]:
            if "message" not in data:
                break
            if data["message"] in posts:
                file_.close()
                flag = False
                break
            posts.add(data["message"])
            file_.write(str(count) + "\n\n" + data["message"] + "\n\n")
            if "comments" in data:
                comments = data["comments"]["data"]
                for comment in comments:
                    if comment["from"]["name"] == "الشريف حاتم بن عارف العوني":
                        file_.write("تعليق: " + "\n" + comment["message"]+"\n\n")
            file_.write("\n\n" + "-"*100 + "\n\n")
            print count
            count += 1
        try:
            url = json_response["paging"]["next"]
        except:
            file_.close()
            break
    file_.close()
# -*- coding: utf-8 -*-
#import scrapy
import requests
from bs4 import BeautifulSoup
import re
print('{0}-{1:{3}}-{2}'.format('2020',2,'26','02d'))
url='https://fang.5i5j.com/bj/loupan/'
r=requests.get(url)
print(r.status_code)
#soup=BeautifulSoup(r.text,'html.parser')
print(re.search(r'\d.[a-z]{4}','3sdgedf 4kljlkjlkj').span())
m=re.search(r'\d.[a-z]{4}','3sdgedf 4kljlkjlkj')
#print(m.string)
#print(type(m))
#zjbds
#matdh fangfa he shuxing
#1.string
#2 .pos
#3 .endpos
#4 .re
print(m.pos)
print(m.endpos)
print(m.re)
print(type(m.re))
#print(m.string)

#fangfa
print(m.group(0))
print(m.start())
print(m.end())
print(m.span())
list=re.findall(r'\d.[a-z]{4}?','3sdgedf 4kljlkjlkj')

for l in list:
    print(l)

#coding=utf-8
 
import requests
from bs4 import BeautifulSoup
import bs4
import re
import sys
reload(sys)
sys.setdefaultencoding('utf8')


 
url = 'http://www.mzitu.com/26685'
header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2107.204 Safari/537.36'}

html = requests.get(url,headers = header)
print(html.status_code)

soup = BeautifulSoup(html.text,'html.parser')
#re.findall(r'','')
#最大页数在span标签中的第10个
pic_max = soup.find_all('span')[3].text
print(pic_max)
#找标题
title = soup.find('h2',class_='main-title').text

#输出每个图片页面的地址
for i in range(1,int(pic_max) + 1):
    href = url+'/'+str(i)
    html = requests.get(href,headers = header)
    mess = BeautifulSoup(html.text,"html.parser")
 
 
    #图片地址在img标签alt属性和标题一样的地方
    pic_url = mess.find('img',alt = title)
 
    html = requests.get(pic_url['src'],headers = header)
 
    #获取图片的名字方便命名
    file_name = pic_url['src'].split(r'/')[-1]
 
    #图片不是文本文件，以二进制格式写入，所以是html.content
    f = open(file_name,'wb')
    f.write(html.content)
    f.close()


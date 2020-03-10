# -*- coding: utf-8 -*-
import scrapy


class BaiduSpider(scrapy.Spider):
    name = 'baidu'
    allowed_domains = ['http://www.baidu.com']
    start_urls = ['http://http://www.baidu.com/']

    def parse(self, response):
        pass

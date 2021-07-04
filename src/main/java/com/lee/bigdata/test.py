import requests as rq
import json


def funcpost():
    url = 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/'  # 需要请求的URL地址
    # data = {'id': 123} # POST请求需要提交的数据
    # data = json.dumps(data)  # 有的时候data需要时json类型的
    # headers = {'content-type': application/json}  # 一种请求头，需要携带

    res = rq.get(url=url)  # 发起请求
    res.json()


funcpost()

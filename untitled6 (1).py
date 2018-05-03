#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Mon Apr  2 19:37:59 2018

@author: deepak
"""
from sklearn.preprocessing import MinMaxScaler

from flask import jsonify, make_response, request, current_app
import numpy as np
from flask import Flask
import keras
from keras.models import model_from_json

app = Flask(__name__)

@app.route('/direct',methods=['GET','OPTIONS'])
def index():
    try:
       
        c = np.load('test3.npy')
        c.reshape(1,60,1)
        c=c.reshape(60)
        c
        json_file = open('model.json', 'r')
        loaded_model_json = json_file.read()
        json_file.close()
        loaded_model = model_from_json(loaded_model_json)
        loaded_model.load_weights("model.h5") 
        k=loaded_model.predict(c.reshape(1,60,1))[0][0]
        new_stock=[]
        for i in range(1,len(c)):
            new_stock.append(c[i])
        new_stock.append(float(k))
        new_stock=np.array(new_stock)
        np.save('test3.npy', new_stock.reshape(1,60,1)) 
        return jsonify({'result':str(k),'results':'sucess'})
    except Exception,e:
        return e
# load weights into new model
    
    
if __name__ == '__main__':
    app.run(debug=True,port=8011)
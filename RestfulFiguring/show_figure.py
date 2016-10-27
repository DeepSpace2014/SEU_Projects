# -*- coding: utf-8 -*-
from flask import Flask, jsonify, request
from flask.helpers import send_from_directory
from flask.templating import render_template
from flask_restful import Resource, Api
import process_data
import os
import glob
import sys
app = Flask(__name__)
api = Api(app)
NUM_PER_PAGE = 15

basePath = "data"
path = ''


@app.after_request
def after_request(response):
    response.headers.add('Access-Control-Allow-Origin', '*')
    response.headers.add('Access-Control-Allow-Headers', 'Content-Type,Authorization')
    response.headers.add('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE')
    return response


@app.route('/search_car')
def search_car():
    date = request.args.get('date')
    car_list = []
    data = {}
    if date is not None:
        date_split = date.split("/")
        date_str = ''
        for s in date_split:
            date_str = s + date_str
        print(date_str)
        car_list = [ x for x in os.listdir(os.path.join(basePath, date_str)) if x.endswith(".txt")]
        data['date'] = date_str
        data['car_list'] = map(lambda x: x[:-4], car_list)

        return render_template('search_car.html', data=data)
    return render_template('search_car.html')

@app.route('/search_number')
def search_by_car_number():
    car_number = request.args.get('car_number')
    if car_number is not None:
        files = glob.glob('data/*/*.txt')
        print(files)
        car_list = [x for x in files if car_number.encode('gb2312') in x.encode('gb2312')]
        ls = []
        for car in car_list:
            sp = car.split('/')
            date = sp[-2]
            ls.append(date)
        ls.sort()
        print(files)
        print(car_list)
        print(ls)
        print(car_number)
        d = {}
        d['date'] = ls
        d['car_number'] = car_number
        return render_template("search_number.html", data=d)
    return render_template("search_number.html")

@app.route('/show_figure')
def show_figure():
    date = request.args.get('date')
    car = request.args.get('car')
    global path
    path = os.path.join(basePath, os.path.join(date, car + '.txt'))
    # return send_from_directory("static", 'show_figure.html')
    return render_template('show_figure.html')


class FigureData(Resource):
    def get(self):
        print(path)
        return process_data.process(path)


api.add_resource(FigureData, '/')
if __name__ == '__main__':
    app.run(host="0.0.0.0")

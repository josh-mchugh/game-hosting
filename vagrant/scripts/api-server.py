#!/usr/bin/python

from flask import Flask, jsonify
from minecraft_api import minecraft_api
from metric_api import metric_api

app = Flask(__name__)
app.config["DEBUG"] = True

app.register_blueprint(minecraft_api)
app.register_blueprint(metric_api)

@app.route('/health', methods=['GET'])
def health():
    return jsonify({'status': 'up'})

app.run(host='0.0.0.0', port=8080, threaded=True)


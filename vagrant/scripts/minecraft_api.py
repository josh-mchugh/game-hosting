#!/usr/bin/python

from flask import Blueprint, jsonify, Response, request
from minecraft import status, start, stop, tell
from subprocess import PIPE
import time
import select
import psutil

minecraft_api = Blueprint('minecraft_api', __name__)

@minecraft_api.route('/minecraft/status', methods=['GET'])
def minecraft_status():
    return jsonify({'status': 'up' if status() else 'down'})

@minecraft_api.route('/minecraft/start', methods=['GET'])
def minecraft_start():
    return jsonify({'status': start()})

@minecraft_api.route('/minecraft/stop', methods=['GET'])
def minecraft_stop():
    return jsonify({'status': stop()})

@minecraft_api.route('/minecraft/command/tell', methods=['POST'])
def minecraft_tell():
    data = request.get_json()
    return jsonify({'status': tell(data['msg'])})

@minecraft_api.route('/minecraft/logs')
def stream():
    def eventStream():

        f = psutil.Popen(['tail','-F', "/home/vagrant/server/logs/latest.log"], stdout=PIPE, stderr=PIPE)
        p = select.poll()
        p.register(f.stdout)

        try:
            while True:
                if p.poll(1):
                    yield "id: {}\nevent: {}\ndata: {}\n\n".format(time.time(), "event-log", f.stdout.readline().strip())
        except GeneratorExit:
            print "terminating processes for pid: {}".format(f.pid())
            f.terminate()
            f.wait()

    return Response(eventStream(), mimetype="text/event-stream")

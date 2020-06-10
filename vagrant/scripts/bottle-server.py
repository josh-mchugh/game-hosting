#!/usr/bin/python

import json
from bottle import request, Bottle, abort, route, run, template
from gevent.pywsgi import WSGIServer
from geventwebsocket import WebSocketError
from geventwebsocket.handler import WebSocketHandler
from minecraft import start, stop, serverStatus, serverMsg

app = Bottle()

@app.route('/hello/<name>')
def index(name):
    return template('<b>Hello {{name}}</b>!', name=name)

@app.route('/websocket')
def handle_websocket():
    wsock = request.environ.get('wsgi.websocket')
    if not wsock:
        abort(400, 'Expected WebSocket request.')

    while True:
        try:
            message = json.loads(wsock.receive())
            command = message["command"]
            wsock.send("Your message was: %r" % json.dumps(message))
            switch(command) {}
            if  command == "start":
                start(wsock)
            elif command == "stop":
                stop(wsock)
                wsock.close()
            elif command == "status":
                serverStatus(wsock)
            elif command == "message":
                serverMessage = message["data"]
                serverMsg(wsock, serverMessage)
                
        except WebSocketError:
            break

server = WSGIServer(("0.0.0.0", 8080), app, handler_class=WebSocketHandler)
server.serve_forever()
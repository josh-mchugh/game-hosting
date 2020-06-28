#!/usr/bin/python

from flask import Blueprint, jsonify
import psutil

metric_api = Blueprint('metric_api', __name__)

@metric_api.route('/metric', methods=['GET'])
def metrics():
    return jsonify({
        "memory": memory(),
        "cpu": cpu(),
        "disk": disk()
    })

@metric_api.route('/metric/memory', methods=['GET'])
def metrics_memory():
    return jsonify(memory())

@metric_api.route('/metric/cpu', methods=['GET'])
def metrics_cpu():
    return jsonify(cpu())

@metric_api.route('/metric/disk', methods=['GET'])
def metrics_disk():
    return jsonify(disk())

def memory():
    memory = psutil.virtual_memory()
    return ({
        "total": memory.total,
        "used": memory.used,
        "free": memory.free,
        "percent": memory.percent
    })

def cpu():
    return ({
        "count": psutil.cpu_count(),
        "percent": psutil.Process().cpu_percent()
    })

def disk():
    disk = psutil.disk_usage('/')
    return ({
        "total": disk.total,
        "used": disk.used,
        "free": disk.free,
        "percent": disk.percent
    })
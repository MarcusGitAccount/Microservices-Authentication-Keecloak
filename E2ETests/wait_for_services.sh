#!/bin/bash

host="localhost:8081/actuator/health"

echo "Issuing calls to $host"

until [[ $(curl -s -o /dev/null -w "%{http_code}" http://$host) == "200" ]]; do
  echo "Service is unavailable - sleeping"
  sleep 2
done

echo "Service is up"

#!/bin/bash

set -e

host="h2"
port="8181"
cmd="$@"

>&2 echo "!!!!!!!! Check h2 for available !!!!!!!!"

until curl http://"$host":"$port"; do
  >&2 echo "h2 is unavailable - sleeping"
  sleep 1
done

>&2 echo "h2 is up - executing command"

exec $cmd
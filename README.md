# siddu
Use with Docker http://www.docker.io

To build an image with docker is pretty simple:

cd rethinkdb
docker build -t="RestFrameWorkUtility" .
Then to run that image and attach to it at the same time:

docker run -i -t RestFrameWorkUtility
Or to run it in the background

docker run -d RestFrameWorkUtility

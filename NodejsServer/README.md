# NodeJS container Docker

## Running on Windows (Temporal solution) ##
- First build the container:
```
docker build -t nodejs_socket_container .
```
- Then, run it:
```
docker run -it -p 3000:3000 --volume ${PWD}/app:/app --name=nodejs_socket_container --rm nodejs_socket_container
```

## Running on Linux/Mac
There are two scripts created for building and running the container. 
- First build the container:
```
./scripts/build
```
Once the container is builded, it not require any new build for code changes.
- Then, run it:
```
./scripts/run
```

## Reference:
- [How to use Docker with Node.js a step-by-step tutorial] (https://geshan.com.np/blog/2020/11/nodejs-with-docker/)
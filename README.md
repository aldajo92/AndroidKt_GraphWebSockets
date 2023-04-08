# Graph visualization: Android, Compose, MPAndroidChart + Docker, NodeJS, WEBSockets

This is an example of a client-server connection created using websockets with the socket.io library. The connection is established between an Android app and a Node.js server. On the Android side, the app integrates Compose, the new UI library for Android, along with MPAndroidChart, a powerful library for rendering charts. The app also uses the socket.io library to listen for events emitted from the server. On the server side, the Node.js app runs as a Docker container and establishes the socket connection using the socket.io and express libraries.

# How to run the Docker container in Windows?
You must install docker in your os. Once docker is installed open the following commands from terminal:

- Open powershell and locate the terminal in the NodejsServer folder:
    ```
    cd /NodejsServer
    ```
- Build the container:
    ```
    docker build -t nodejs_socket_container .
    ```
- Run the container:
    ```
    docker run -it -p 3000:3000 --volume ${PWD}/app:/app --name=nodejs_socket_container --rm nodejs_socket_container
    ```

# How to run the Docker container in Linux/Mac?
You must install docker in your os. Once docker is installed open the following commands from terminal:

- Open your terminal and locate in the NodejsServer folder:
    ```
    cd /NodejsServer
    ```
- Build the container using the scripts created:
    ```
    ./scripts/build
    ```
- Run the container:
    ```
    ./scripts/run
    ```

# Author
Alejandro Daniel José Gómez Flórez (aldajo92)

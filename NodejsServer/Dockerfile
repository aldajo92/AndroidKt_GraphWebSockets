FROM node:17.0.1

# COPY ./app /app

RUN npm install -g nodemon

COPY ./autostart.sh /
RUN chmod +x /autostart.sh
CMD ["sh", "autostart.sh"]

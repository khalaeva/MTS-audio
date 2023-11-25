FROM node:lts-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
EXPOSE 8081
COPY . .
VOLUME [ "app/data" ]
ENTRYPOINT ["npm", "run", "serve"]
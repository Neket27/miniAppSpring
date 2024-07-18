FROM node:16
LABEL authors="neket"
WORKDIR /app
COPY frontend/react-app/package*.json ./
RUN npm install
COPY . .
EXPOSE 5173
CMD ["npm", "run", "dev"]

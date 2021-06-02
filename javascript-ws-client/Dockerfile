# pulling the node image with alpine(lite weight OS)
FROM node:16

#Adding a working directory inside the container
WORKDIR /usr/jeyam

#Copying the package.json and all dependencies files into the container
COPY ./ ./

#Running the NPM install command to retrieve the express and related dependencies in package.json file
RUN npm install

#Run these commands during the container startup
CMD ["node","index.js"]
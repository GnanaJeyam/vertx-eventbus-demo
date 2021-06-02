# pulling the python image
FROM python:3.9.5

#Adding a working directory inside the container
WORKDIR /usr/jeyam

#Copying the package.json and all dependencies files into the container
COPY ./ ./

# #Running the NPM install command to retrieve the express and related dependencies in package.json file
# RUN npm install

#Run these commands during the container startup
CMD ["python","Client.py"]
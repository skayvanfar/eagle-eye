echo "Building with travis commit of $BUILD_NAME ..."
# The creation of the Docker image is carried out using the Spotify Docker plugin
mvn clean package docker:build

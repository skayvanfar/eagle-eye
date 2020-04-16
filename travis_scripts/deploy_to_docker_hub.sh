echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push skayvanfar/eagle-eye-authentication-service:$BUILD_NAME
docker push skayvanfar/eagle-eye-licensing-service:$BUILD_NAME
docker push skayvanfar/eagle-eye-organization-service:$BUILD_NAME
docker push skayvanfar/eagle-eye-confsvr:$BUILD_NAME
docker push skayvanfar/eagle-eye-eurekasvr:$BUILD_NAME
docker push skayvanfar/eagle-eye-zuulsvr:$BUILD_NAME

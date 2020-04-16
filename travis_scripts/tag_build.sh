echo "Tagging build with $BUILD_NAME"
# Target endpoint for the GitHub release API
export TARGET_URL="https://api.github.com/repos/skayvanfar/eagle-eye/releases?access_token=$GITHUB_TOKEN"

body="{
  \"tag_name\": \"$BUILD_NAME\",
  \"target_commitish\": \"master\",
  \"name\": \"$BUILD_NAME\",
  \"body\": \"Release of version $BUILD_NAME\",
  \"draft\": true,
  \"prerelease\": true
}"

# Uses curl to invoke the service used to kick off a build
curl -k -X POST \
  -H "Content-Type: application/json" \
  -d "$body" \
  $TARGET_URL

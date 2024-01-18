def call(String dockerHubUsername, String imageName) {
    // Install buildx
    sh 'docker buildx create'

    // Build the Docker image with buildx
    sh "docker buildx build --platform linux/amd64,linux/arm64 --build-arg REACT_APP_RAPID_API_KEY=d245199e8emshd101669bdbafcf8p12d91ejsn53648793d8f6 -t ${imageName} ."

    // Tag the Docker image
    sh "docker tag ${imageName} ${dockerHubUsername}/${imageName}:latest"

    // Push the Docker image
    withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'docker']) {
        sh "docker push ${dockerHubUsername}/${imageName}:latest"
    }
}

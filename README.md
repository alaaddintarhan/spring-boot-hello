docker run --name jenkins-docker --restart=on-failure --detach  --privileged --network jenkins --network-alias docker  --env DOCKER_TLS_CERTDIR=/certs  --volume jenkins-docker-certs:/certs/client  --volume jenkins-data:/var/jenkins_home  --publish 2376:2376 docker:dind


docker run --name attin-jenkins --restart=on-failure --detach --network jenkins --network minikube  --env DOCKER_HOST=tcp://docker:2376  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 --volume jenkins-data:/var/jenkins_home --volume jenkins-docker-certs:/certs/client:ro --publish 8080:8080 --publish 50000:50000 attin-jenkins


docker run --hostname=7d0a33c5f8ac --user=jenkins --env=DOCKER_HOST=tcp://docker:2376 --env=DOCKER_CERT_PATH=/certs/client --env=DOCKER_TLS_VERIFY=1 --env=PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=LANG=C.UTF-8 --env=JENKINS_HOME=/var/jenkins_home --env=JENKINS_SLAVE_AGENT_PORT=50000 --env=REF=/usr/share/jenkins/ref --env=JENKINS_VERSION=2.452.3 --env=JENKINS_UC=https://updates.jenkins.io --env=JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental --env=JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals --env=COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log --env=JAVA_HOME=/opt/java/openjdk --volume=jenkins-data:/var/jenkins_home --volume=jenkins-docker-certs:/certs/client:ro --volume=/var/jenkins_home --network=jenkins --workdir=/var/jenkins_home -p 50000:50000 -p 8080:8080 --restart=on-failure:0 --label='org.opencontainers.image.description=The Jenkins Continuous Integration and Delivery server' --label='org.opencontainers.image.licenses=MIT' --label='org.opencontainers.image.revision=9fba54713675c711138675b4aaaff6268c14d31a' --label='org.opencontainers.image.source=https://github.com/jenkinsci/docker' --label='org.opencontainers.image.title=Official Jenkins Docker image' --label='org.opencontainers.image.url=https://www.jenkins.io/' --label='org.opencontainers.image.vendor=Jenkins project' --label='org.opencontainers.image.version=2.452.3' --runtime=runc -d attin-jenkins



docker run --hostname=0a9bfe6d4c82 --env=DOCKER_TLS_CERTDIR=/certs --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=DOCKER_VERSION=27.0.3 --env=DOCKER_BUILDX_VERSION=0.16.0 --env=DOCKER_COMPOSE_VERSION=2.28.1 --env=DIND_COMMIT=65cfcc28ab37cb75e1560e4b4738719c07c6618e --volume=jenkins-docker-certs:/certs/client --volume=jenkins-data:/var/jenkins_home --volume=/var/lib/docker --network=jenkins --privileged -p 2376:2376 --restart=on-failure:0 --runtime=runc -d docker:dind



docker run --hostname=minikube --env=container=docker --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --volume=/lib/modules:/lib/modules:ro --volume=minikube:/var --network=minikube --privileged --workdir=/ -p 127.0.0.1:22 -p 127.0.0.1:2376 -p 127.0.0.1:32443 -p 127.0.0.1:5000 -p 127.0.0.1:8443 --restart=no --label='created_by.minikube.sigs.k8s.io=true' --label='mode.minikube.sigs.k8s.io=minikube' --label='name.minikube.sigs.k8s.io=minikube' --label='role.minikube.sigs.k8s.io=' --runtime=runc --memory="4194304000" -t -d gcr.io/k8s-minikube/kicbase:v0.0.44@sha256:eb04641328b06c5c4a14f4348470e1046bbcf9c2cbc551486e343d3a49db557e

git tag <tag_name>
git push --tag origin master
git describe --tags --abbrev=0
git checkout tags/<tag_name> 
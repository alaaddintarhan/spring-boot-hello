pipeline {
	environment {
		registry = 'alaaddintarhan/hello'
		registryCredential = 'dockerhub_id'
		kubeConfigCredential = 'kubeconfig'
		gitRepoUrl='https://github.com/alaaddintarhan/spring-boot-hello.git'
		gitRepoBranch='master'
		dockerImage = ''
		dockerImageTag="${gitRepoBranch}"+"-${BUILD_NUMBER}"
	}
	agent any
	stages {
		stage('Cloning our Git') {
			steps {
				checkout([$class: 'GitSCM',
                          branches: [[name: "$gitRepoBranch"]],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [],
                          gitTool: 'Default',
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: "$gitRepoUrl"]]
                        ])
			}
		}
		stage('Building our image') {
			steps{
				script {
					dockerImage = docker.build registry + ":$dockerImageTag"
				}
			}
		}
		stage('Push image to DockerHub') {
			steps{
				script {
					docker.withRegistry( '', registryCredential ) {
						dockerImage.push()
					}
				}
			}
		}
		 stage("Deploy to Kubernetes") {
             steps {
                    script {
                         withKubeConfig([credentialsId: kubeConfigCredential]) {
                             sh "helm upgrade hello ./helm --set image.repository=$registry --set image.tag=$dockerImageTag"
                         }
                     }
           }
         }
	/*	stage('Applying helm charts') {
            steps {
                echo "Running Helm"
    			sh 'helm upgrade hello ./helm --set imageTag=$BUILD_NUMBER'
            }
        }
	 stage('Cleaning up') {
			steps{
				sh "docker rmi $registry:$dockerImageTag"
			}
		}
		stage('Deploy') {
            steps {
                echo 'Deploying the application on Docker'
                sh "docker run -d -p 8888:8888 $registry:$BUILD_NUMBER"
            }
        }*/
        // untag
	}
}